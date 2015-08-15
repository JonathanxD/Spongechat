/**
 *  Spongechat — A new Powered Chat System for SpongePowered Minecraft API.
 *  Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/> and your contributors;
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.kaward.spongepowered.spongechat;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerChatEvent;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;
import org.spongepowered.api.text.Text;

/**
 * This class manage the events, and your handlers.
 * 
 * @author Pitter Thog (Kaward) <https://github.com/Kaward/>
 * @category Handler
 *
 */
public class EventManager
{

	/**
	 * Register the player in the system (automatically on join), to use it
	 * after.
	 *
	 * @param event the instance of Join event.
	 */
	@Subscribe(order = Order.LAST)
	public void handle(PlayerJoinEvent event)
	{
		Player player = event.getEntity();
		SpongechatAPI.getPlayerManager().setFocus(player, Channel.DEFAULT_CHANNEL);
	}

	/**
	 * Send the message to all players, and log it.
	 * 
	 * @param event the instance of Chat event (the order used is LAST because it is the last action — send the message)
	 */
	@Subscribe(order = Order.LAST)
	public void handle(PlayerChatEvent event)
	{
		Player player = event.getEntity(); // Get the source (player)
		Text text = event.getMessage(); // Get the formatted message text

		Message message = new Message(player, SpongechatAPI.getPlayerManager().getFocusedChannel(player), text); // Prepare the instance of final message
		message.send(); // Send the message
		event.setCancelled(true); // The really chat event can't be executed.
	}

}
