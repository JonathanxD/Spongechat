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
package org.sparkpowered.spongechat.logs;

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
 * @see org.sparkpowered.spongechat.logs.LogData When name/data is stored
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
