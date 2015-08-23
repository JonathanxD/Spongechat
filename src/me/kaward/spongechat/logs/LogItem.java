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
package me.kaward.spongechat.logs;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

/**
 *
 * <p>
 * This class is used to store the all data and names into the log.
 * </p>
 *
 * @category Log Handlering
 * @see me.kaward.spongechat.logs.LogData When name/data is stored
 *
 */
public interface LogItem
{

	/**
	 *
	 * @return The Universal Unique Identification (UUID) of this log.
	 * @see java.util.UUID The UUID system
	 */
	public UUID id();

	/**
	 * <p>
	 * Use the <T> to get the object type expected.
	 * </p>
	 *
	 * @param name The keyword to get the log by your keyword name.
	 * @return Returns the object represented by the keyword name.
	 */
	@Nullable
	public <T> T get(String name);

	/**
	 *
	 * @return The list of datas in this log.
	 */
	public List<LogData> data();

}
