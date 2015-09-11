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
package org.sparkpowered.spongechat.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

public class Configuration implements Serializable, Cloneable
{

	private static final long serialVersionUID = -5373342148735836183L;
	private List<String> parser = new ArrayList<String>();
	private final HashMap<String, Object> builder = new HashMap<String, Object>();
	private File file = null;
	private static final String desktop = System.getProperty("user.home") + (System.getProperty("os.name").toLowerCase().contains("win") ? File.separator + "Desktop" : "");
	private final ConfigurationSection cs = new ConfigurationSection();;

	public static void main(final String[] args) throws IOException
	{
		final File file = new File(desktop, "tests.yml");
		if (!file.exists())
		{
			file.createNewFile();
		}

		final Configuration c = new Configuration(file).parse();

		final Logger log = Logger.getLogger("Configuration");
		log.info("Digite uma key para verificar o parser:");

		final Scanner s = new Scanner(System.in);
		final String key = s.nextLine();
		log.info("Resultado [" + key + ": " + c.builder.get(key) + "]\n");

		log.info("Agora, digite algo para MUDAR:");
		final String keychange = s.nextLine();

		log.info("Entendi.. Digite o novo valor:");
		String valuechange = s.nextLine();
		if (valuechange.isEmpty())
		{
			valuechange = null;
		}
		c.set(keychange, valuechange);

		log.info("Digite algo para salvar o arquivo.");
		s.nextLine();

		s.close();
		c.save();
		c.parse();
	}

	public Configuration(final File file)
	{
		this.file = file;
	}

	public Configuration parse() throws IOException
	{
		builder.clear();
		parser.clear();

		final FileInputStream input = new FileInputStream(file);
		final InputStreamReader stream = new InputStreamReader(input, Charset.forName("UTF-8"));
		final BufferedReader reader = new BufferedReader(stream);

		String str = "";
		while ((str = reader.readLine()) != null)
		{
			parser.add(str);
		}

		reader.close();

		@SuppressWarnings("unused")
		final Logger log = Logger.getLogger("Configuration");

		cs.setNewSectionTransform(parser);
		parser = cs.formSection().formResult();
		for (final String parser0 : parser)
		{

			if (!parser0.startsWith("#") && !parser0.isEmpty())
			{
				final String k = parser0.split(":")[0];
				String v = parser0.split(k)[1];

				if (v.startsWith(" "))
				{
					v = v.replaceFirst(" ", "");
				}

				if (v.startsWith(": "))
				{
					v = v.replaceFirst(": ", "");
				}

				if (v.startsWith(" : "))
				{
					v = v.replaceFirst(" : ", "");
				}
				builder.put(k, v);
			}
		}

		return this;
	}

	public void save() throws IOException
	{
		final Logger log = Logger.getLogger("Configuration");
		final FileOutputStream fos = new FileOutputStream(file);
		final OutputStreamWriter os = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
		final Set<String> preventive = new HashSet<String>();
		final BufferedWriter writer = new BufferedWriter(os);
		String content = "";

		for (final String map : parser)
		{
			log.info("parsing: " + map);
			if (map.startsWith("#"))
			{
				content = map + "\n";
			}
			else
			{
				final String k = map.split(":")[0];
				log.info("---> key = " + k);

				String v = map.split(k + ":").length == 1 ? v = "" : map.split(k + ":")[1]; // Fix For Sections

				if (v.startsWith(" "))
				{
					v = v.replaceFirst(" ", "");
				}

				if (v.startsWith(": "))
				{
					v = v.replaceFirst(": ", "");
				}

				if (v.startsWith(" : "))
				{
					v = v.replaceFirst(" : ", "");
				}

				if (builder.containsKey(k))
				{
					if (!preventive.contains((k + ": " + v)))
					{
						content += (k + ": " + v) + "\n";
						preventive.add((k + ": " + v));
					}
				}
			}
		}

		content = cs.sectionTransform(content).getResultOfTransform(); // By Jonathan
		writer.write(content);
		writer.flush();
		writer.close();
	}

	public void set(final String key, final Object value)
	{
		for (final char c : key.toCharArray())
		{
			if (!Limits.ACCEPTED_KEY_SIMBOLS.contains(String.valueOf(c)) && !Limits.ALPHABET.contains(String.valueOf(c)) && !Limits.NUMBERS.contains(String.valueOf(c)))
			{
				try
				{
					throw new KeycharErrorException(c);
				}
				catch (final KeycharErrorException e)
				{
					e.printStackTrace();
					break;
				}
			}
		}

		builder.put(key, value);

		for (int i = 0; i < parser.size(); i++)
		{
			if (parser.get(i).startsWith(key + ":"))
			{
				parser.set(i, key + ": " + value);
			}
		}

		parser.add(key + ": " + value);
	}

	public String getString(final String key)
	{
		return String.valueOf(builder.get(key));
	}

	public Integer getInt(final String key)
	{
		return Integer.parseInt(String.valueOf(builder.get(key)));
	}

	public Boolean getBoolean(final String key)
	{
		return Boolean.parseBoolean(String.valueOf(builder.get(key)));
	}

	public Float getFloat(final String key)
	{
		return Float.parseFloat(String.valueOf(builder.get(key)));
	}

}
