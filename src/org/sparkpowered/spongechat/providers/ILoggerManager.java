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
package org.sparkpowered.spongechat.providers;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.sparkpowered.spongechat.logs.LogData;
import org.sparkpowered.spongechat.logs.LogItem;

/**
 *
 * <p>
 * This class contains the interface of provider of Log Handlering.
 * </p>
 *
 * @category Log Handlering
 * @see me.kaward.spongechat.logs.LoggerManager Log Handlering
 */
public interface ILoggerManager
{

	/**
	 * <p>
	 * Get an Item of the log. You need to specific the group of log (e.g "channels", "players") and the uuid of the element.
	 * </p>
	 *
	 * @param group The group of log
	 * @param uuid The UUID of element
	 * @return The an instance of log item containing logs (names and datas)
	 */
	public LogItem item(String group, UUID uuid);

	/**
	 * <p>
	 * Creates a new log item containing logs (names and datas) in a specific group.
	 * </p>
	 *
	 * @param group The specific group to be added the log
	 * @param data An array of log datas.
	 * @return Returns the added log item.
	 */
	public LogItem add(String group, LogData... data);

	/**
	 * <p>
	 * Get the list of logs.
	 * </p>
	 *
	 * @return Returns an list of all existent logs in all groups.
	 */
	public Set<UUID> listLogs();

	/**
	 * <p>
	 * Get the list of groups.
	 * </p>
	 *
	 * @return Returns an list of all existent groups.
	 */
	public Set<String> listGroups();

	/**
	 * <p>
	 * Get the list of logs of an expected group.
	 * </p>
	 *
	 * @param group The expected group.
	 * @return Returns the list of logs
	 */
	public List<UUID> getLogsOf(String group);

	/**
	 * <p>
	 * Remove and delete an log item from the system (can't be undo).
	 * </p>
	 *
	 * @param item The item to be removed.
	 */
	public void remove(LogItem item);

}
