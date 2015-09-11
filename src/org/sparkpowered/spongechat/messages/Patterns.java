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
package org.sparkpowered.spongechat.messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patterns
{

	private static final String ex1 = ".*[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}:[0-9]{1,5}.*";
	private static final String ex2 = ".*[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.*";
	private static final String ex3 = ".*[a-zA-Z]+\\.(com|net|ru|ua|by|info|us|uk|so|org|su|tv|kz|br|biz|vh).*";
	private static final String ex4 = ".*[a-zA-Z]+\\.(com|net|ru|ua|by|info|us|uk|so|org|su|tv|kz|br|biz|vh):[0-9]{1,5}.*";
	private static final Pattern[] patterns = new Pattern[] { compile(ex1), compile(ex2), compile(ex3), compile(ex4) };

	public static boolean match(final String text)
	{
		for (final Pattern pattern : patterns)
		{
			final Matcher m = pattern.matcher(text);
			if (m.matches())
			{
				return true;
			}
		}

		return false;
	}

	private static final Pattern compile(final String regex)
	{
		return Pattern.compile(regex);
	}
}
