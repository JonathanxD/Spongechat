/**
 * 	Spongechat, a new powered chat system for SpongePowered Minecraft API.
 * 	Copyright (C) 2015 Kaward <https://github.com/Kaward/>
 * 	Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/>
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

	public static LogData from(String name, Object data)
	{
		return new LogData(name, data);
	}

	public LogData(String name, Object data)
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
