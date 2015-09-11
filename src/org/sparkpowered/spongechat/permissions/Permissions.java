/**
 * 	This file is part from Spongechat.
 *
 *  Spongechat — A new powered engine for server conversations.
 *  Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/> and your contributors;
 *  Copyright (C) 2015 contributors
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sparkpowered.spongechat.permissions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import org.sparkpowered.spongechat.utils.Strings;

public class Permissions
{

	private final Map<PermissionEntry, List<String>> comments = new HashMap<PermissionEntry, List<String>>();
	private final Map<Set<String>, Set<PermissionEntry>> agroup = new HashMap<Set<String>, Set<PermissionEntry>>();
	private final Set<PermissionEntry> permissions = new HashSet<PermissionEntry>();
	private InputStreamReader input = null;

	public static void main(final String[] args) throws PermissionCompilerException, IOException
	{
		final Permissions script = new Permissions(new File("src/resources/permissions.txt"));
		script.compile();
		script.source(new File("C:\\Users\\Joao Pedro\\Desktop\\src.txt"));
	}

	public Permissions(final InputStreamReader source)
	{
		input = source;
	}

	public Permissions(final FileInputStream source)
	{
		input = new InputStreamReader(source, Charset.forName("UTF-8"));
	}

	public Permissions(final File source)
	{
		try
		{
			final FileInputStream fis = new FileInputStream(source);
			input = new InputStreamReader(fis, Charset.forName("UTF-8"));
		}
		catch (final FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private void source0(final File out) throws IOException
	{
		final StringBuilder builder = new StringBuilder();
		for (final Entry<Set<String>, Set<PermissionEntry>> e : agroup.entrySet())
		{
			final String entries = Strings.joinPermissions(e.getValue(), ", ");
			for (final PermissionEntry pe : e.getValue())
			{
				if (comments.containsKey(pe))
				{
					for (final String c : comments.get(pe))
					{
						builder.append("$ " + c + "\n");
					}
				}
			}

			builder.append("# " + entries + "\n");
			for (final String s : e.getKey())
			{
				builder.append("  - " + s + "\n");
			}
			builder.append("\n");
		}

		final String source = builder.toString();
		final FileOutputStream fos = new FileOutputStream(out);
		final OutputStreamWriter os = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
		os.write(source);
		os.flush();
		os.close();
	}

	public void source(final File out) throws IOException
	{
		source0(out);
	}

	public void compile() throws PermissionCompilerException
	{
		comments.clear();
		permissions.clear();
		agroup.clear();

		final List<String> script = new ArrayList<String>();
		final BufferedReader reader = new BufferedReader(input);
		String s = null;
		try
		{
			while ((s = reader.readLine()) != null)
			{
				if (!s.isEmpty())
				{
					script.add(s);
				}
			}
		}
		catch (final IOException e)
		{
			throw new PermissionCompilerException((input != null ? input : null), "Failed to compile permission's script (Failed to read the Script File)");
		}

		try
		{
			final List<String> comments0 = new ArrayList<String>();
			for (int i = 0; i < script.size(); i++)
			{
				final String line = script.get(i);
				if (line.startsWith("$ ") || line.startsWith("$"))
				{
					comments0.add(line.replace("$", "").trim());
				}
				else if (line.startsWith("# "))
				{
					final List<String> lineHeader = new ArrayList<String>();
					final String[] lineHeader0 = line.split("# ")[1].split(", ");
					for (final String lh1 : lineHeader0)
					{
						if (lh1 != null & !lh1.isEmpty())
						{
							lineHeader.add(lh1);
						}
					}

					if (!lineHeader.isEmpty())
					{
						if ((i + 1) < script.size())
						{
							final Set<String> permissions0 = new HashSet<String>();
							while (((i + 1) < script.size()) && (script.get((i + 1)).startsWith("  - ")))
							{
								i++;
								final String nextLine = script.get(i).replace("  - ", "");
								permissions0.add(nextLine);
							}

							if (!permissions0.isEmpty())
							{
								for (final String h : lineHeader)
								{
									final PermissionEntry pe = new PermissionEntry(h, permissions0);
									addPermission(pe);
									final List<String> clone = new ArrayList<String>();
									clone.addAll(comments0);
									setComments(pe, clone);
									comments0.clear();
								}
							}
						}
					}

				}
			}
		}
		catch (final IndexOutOfBoundsException ex1)
		{
			throw new PermissionCompilerException((input != null ? input : null), "Failed to compile permission's script (The script aren't correct, failed to identify the context)");
		}
	}

	public void setComments(final PermissionEntry permission, final List<String> comments)
	{
		if (!comments.isEmpty() && comments.size() != 0)
		{
			this.comments.put(permission, comments);
		}
	}

	@Nullable
	public List<String> getComments(final PermissionEntry permission)
	{
		return comments.get(permission);
	}

	public void addPermission(final PermissionEntry permission)
	{
		permissions.add(permission);
		agroup(permission, permission.getGranted());
	}

	public void delPermission(final PermissionEntry permission)
	{
		permissions.remove(permission);
	}

	@Nullable
	public PermissionEntry getPermission(final String name)
	{
		for (final PermissionEntry perm : permissions)
		{
			if (perm.getEntry().equalsIgnoreCase(name))
			{
				return perm;
			}
		}

		return null;
	}

	private void agroup(final PermissionEntry entry, final Set<String> permissions)
	{
		final Set<PermissionEntry> set = agroup.containsKey(permissions) ? agroup.get(permissions) : new HashSet<PermissionEntry>();
		set.add(entry);
		agroup.put(permissions, set);
	}

}
