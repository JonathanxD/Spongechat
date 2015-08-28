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
package org.sparkpowered.spongechat.events;

import java.util.LinkedList;
import java.util.Queue;

import org.sparkpowered.spongechat.Spongechat;
import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.channels.Channels;
import org.sparkpowered.spongechat.messages.Message;
import org.sparkpowered.spongechat.messages.OrderedMessage;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerChatEvent;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;

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
	 * Retorna a fila de mensagens em ordem para serem realmente executadas.
	 * Cuidado ao utilizar esta API, pode causar problemas e erros na ordem do chat.\n
	 *
	 * Returns the queue of messages in correct order to real execute.\n
	 * Caution when you use this API, because it can cause errors in the order of chat messages.
	 */
	public static Queue<OrderedMessage> queue = new LinkedList<OrderedMessage>();

	/**
	 * Register the player in the system (automatically on join), to use it after.
	 *
	 * @param event the instance of Join event.
	 */
	@Subscribe(order = Order.FIRST)
	public void handleJoinEvent(final PlayerJoinEvent event)
	{
		final Player player = event.getEntity();
		SpongechatAPI.getPlayerManager().setFocus(player, Channels.DEFAULT_CHANNEL);
	}

	/**
	 * Prepare the event to real perform message on the chat.
	 */
	@Subscribe(order = Order.FIRST)
	public void handlePlayerChatEventFirst(final PlayerChatEvent event)
	{
		final Player player = event.getEntity();
		final Text text = event.getMessage();
		final Message message = new Message(player, SpongechatAPI.getPlayerManager().getFocusedChannel(player), text);

		final ChannelMessageEvent preparedEvent = new ChannelMessageEvent(message.getSender(), message.getChannel(), message, Texts.of(message.getUnformattedMessage()).builder().build(), message.getReceivers());
		Spongechat.sponge.getEventManager().post(preparedEvent);

		if (preparedEvent.isCancelled())
		{
			event.setCancelled(true);
		}
		else
		{
			final OrderedMessage order = new OrderedMessage(player.getUniqueId(), preparedEvent);
			queue.offer(order);
		}
	}

	/**
	 * After the preparation (handler0), this action is called to real perform message on the chat.
	 *
	 * @see org.sparkpowered.spongechat.events.EventManager The method handle0(PlayerChatEvent ...)
	 */
	@Subscribe(order = Order.LAST, ignoreCancelled = true)
	public void handlePlayerChatEventLast(final PlayerChatEvent event)
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
			final OrderedMessage order = queue.poll();
			if (order != null)
			{
				order.getEvent().getMessage().send();
				event.setCancelled(true);
			}
		}
	}

}
