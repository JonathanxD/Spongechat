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
package org.sparkpowered.spongechat.logs;

/**
 *
 * <p>
 * This class is only used to store a log name and data of an item, as a instance.
 * </p>
 *
 * @category Log Handlering
 * @see org.sparkpowered.spongechat.logs.LogItem The Item Class
 *
 */
public class LogData
{

	private String name = "";
	private Object data = null;

	public static LogData from(final String name, final Object data)
	{
		return new LogData(name, data);
	}

	public LogData(final String name, final Object data)
	{
		this.name = name;
		this.data = data;
	}

	/**
	 * <p>
	 * Its a keyword, representer of data object.
	 * </p>
	 *
	 * @return Keyword of data object.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 *
	 * @return The data object.
	 */
	public Object getData()
	{
		return data;
	}

}
