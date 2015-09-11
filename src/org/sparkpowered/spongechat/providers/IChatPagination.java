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
package org.sparkpowered.spongechat.providers;

import org.sparkpowered.spongechat.messages.PublicMessage;
import org.spongepowered.api.entity.player.Player;

/**
 *
 * <p>
 * This class provides interface of Pagination Service.
 * </p>
 *
 * @category Pagination Services
 * @see org.sparkpowered.spongechat.pages.ChatPagination Pagination Handlering
 *
 */
public interface IChatPagination
{

	/**
	 * <p>
	 * Create an page with informations of an message and send to a expected player to be opened.
	 * </p>
	 *
	 * @param player The player to send and open the page.
	 * @param message Instance of Message to get and use their informations.
	 */
	public void createInfo(Player player, PublicMessage message);

}
