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
package me.kaward.spongechat.providers;

import org.spongepowered.api.entity.player.Player;

import me.kaward.spongechat.Message;

/**
 *
 * <p>
 * This class provides interface of Pagination Service.
 * </p>
 *
 * @category Pagination Services
 * @see me.kaward.spongechat.pages.ChatPagination Pagination Handlering
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
	public void createInfo(Player player, Message message);

}
