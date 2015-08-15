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

import java.util.LinkedList;
import java.util.Queue;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerChatEvent;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;

import me.kaward.spongepowered.spongechat.events.ChannelMessageEvent;

/**
 * This class manage the events, and your handlers.
 *
 * @author Pitter Thog (Kaward) <https://github.com/Kaward/>
 * @category Handler
 *
 */
public class EventManager
{

	public static Queue<OrderedMessage> queue = new LinkedList<OrderedMessage>();

	/**
	 * Register the player in the system (automatically on join), to use it after.
	 *
	 * @param event the instance of Join event.
	 */
	@Subscribe(order = Order.LAST)
	public void handleJoinEvent(PlayerJoinEvent event)
	{
		Player player = event.getEntity();
		SpongechatAPI.getPlayerManager().setFocus(player, Channel.DEFAULT_CHANNEL);
	}

	/**
	 * Prepare the event to real perform message on the chat.
	 */
	@Subscribe(order = Order.FIRST)
	public void handlePlayerChatEventFirst(PlayerChatEvent event)
	{
		Player player = event.getEntity();
		Text text = event.getMessage();
		Message message = new Message(player, SpongechatAPI.getPlayerManager().getFocusedChannel(player), text);

		ChannelMessageEvent preparedEvent = new ChannelMessageEvent(message.getSentBy(), message.getChannel(), message, Texts.of(message.getUnformattedMessage()).builder().build(), message.getReceivers());
		Spongechat.sponge.getEventManager().post(preparedEvent);

		if (preparedEvent.isCancelled())
		{
			event.setCancelled(true);
		}
		else
		{
			OrderedMessage order = new OrderedMessage(player.getUniqueId(), preparedEvent.getMessage());
			queue.offer(order);
		}
	}

	/**
	 * After the preparation (handler0), this action is called to real perform message on the chat.
	 *
	 * @see me.kaward.spongepowered.spongechat.EventManager The method handle0(PlayerChatEvent ...)
	 */
	@Subscribe(order = Order.LAST, ignoreCancelled = true)
	public void handlePlayerChatEventLast(PlayerChatEvent event)
	{
		if (event.isCancelled())
		{
			if (!queue.isEmpty())
			{
				queue.remove();
			}
		}
		else
		{
			OrderedMessage order = queue.poll();
			if (order != null)
			{
				order.getMessage().send();
				event.setCancelled(true);
			}
		}
	}

}
