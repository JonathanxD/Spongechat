/**
 * 	This file is part from Spongechat.
 *
 *  Spongechat � A new powered engine for server conversations.
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
package org.sparkpowered.spongechat.json;

/*
Copyright (c) 2002 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/**
 * The HTTPTokener extends the JSONTokener to provide additional methods
 * for the parsing of HTTP headers.
 * 
 * @author JSON.org
 * @version 2014-05-03
 */
public class HTTPTokener extends JSONTokener
{

	/**
	 * Construct an HTTPTokener from a string.
	 * 
	 * @param string A source string.
	 */
	public HTTPTokener(final String string)
	{
		super(string);
	}

	/**
	 * Get the next token or string. This is used in parsing HTTP headers.
	 * 
	 * @throws JSONException
	 * @return A String.
	 */
	public String nextToken() throws JSONException
	{
		char c;
		char q;
		final StringBuilder sb = new StringBuilder();
		do
		{
			c = next();
		}
		while (Character.isWhitespace(c));
		if (c == '"' || c == '\'')
		{
			q = c;
			for (;;)
			{
				c = next();
				if (c < ' ')
				{
					throw syntaxError("Unterminated string.");
				}
				if (c == q)
				{
					return sb.toString();
				}
				sb.append(c);
			}
		}
		for (;;)
		{
			if (c == 0 || Character.isWhitespace(c))
			{
				return sb.toString();
			}
			sb.append(c);
			c = next();
		}
	}
}
