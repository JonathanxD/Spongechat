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
package org.sparkpowered.spongechat.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.sparkpowered.spongechat.permissions.PermissionEntry;

public class Strings
{

	public static void main(final String[] args)
	{
		System.out.println(bracket("There is a tag", "{}"));
	}

	public static String math(final String string)
	{
		return string;
	}

	public static boolean codes(final char c)
	{
		final boolean colorNumbers = (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9');
		final boolean colorLetters = (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f');
		final boolean styleLetters = (c == 'l' || c == 'i' || c == 'n' || c == 'b' || c == 'k');
		return (colorNumbers || colorLetters || styleLetters);
	}

	public static String removeCodes(final String string)
	{
		for (int i = 0; i < string.length(); i++)
		{
			final char c = string.charAt(i);
			if (c == '&' || c == '§')
			{
				if ((i + 1) < string.length())
				{
					final char c2 = string.charAt(i + 1);
					string.replaceFirst(c + "" + c2, "");
				}
				else
				{
					string.replaceFirst("" + c, "");
				}
			}
		}

		return string;
	}

	public static String bracket(final String string, final String brackets)
	{
		return (brackets.substring(0, 1) + string + brackets.substring(1, 2)).replace(" ", "_").toLowerCase();
	}

	public static String joinPermissions(final Collection<PermissionEntry> entries, final String separator)
	{
		final Set<String> strings = new HashSet<String>();

		for (final PermissionEntry pe : entries)
		{
			strings.add(pe.getEntry());
		}

		return Strings.join(strings, separator);
	}

	public static String joinPermissions(final PermissionEntry[] entries, final String separator)
	{
		return Strings.joinPermissions(Arrays.asList(entries), separator);
	}

	public static String join(final String[] pieces, final String separator)
	{
		return join(Arrays.asList(pieces), separator);
	}

	public static String join(final Collection<String> pieces, final String separator)
	{
		final StringBuilder buffer = new StringBuilder();
		for (final Iterator<String> iter = pieces.iterator(); iter.hasNext();)
		{
			buffer.append(iter.next());
			if (iter.hasNext())
			{
				buffer.append(separator);
			}
		}

		return buffer.toString();
	}

	public static String timenow()
	{
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		final Date date = Calendar.getInstance().getTime();
		return format.format(date);
	}

}
