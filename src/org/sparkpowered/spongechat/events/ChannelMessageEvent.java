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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.channels.Channel;
import org.sparkpowered.spongechat.messages.Message;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.AbstractEvent;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.text.Text;

/**
 *
 * <p>
 * This is a abstract event factored with AbstractEvent from SpongePowered API.
 * </p>
 *
 * <p>
 * This event is called when player try to send a message, it can be handlered and cancelled if necessary by another plugins.
 * </p>
 *
 * @category Event Factory
 *
 */
public class ChannelMessageEvent extends AbstractEvent implements Cancellable
{

	private Set<UUID> receivers = new HashSet<UUID>();
	private UUID playerUniqueId = null;
	private Channel channel = null;
	private Text text = null;
	private Message message = null;
	private Boolean cancelled = false;

	public ChannelMessageEvent(UUID playerUniqueId, Channel channel, Message message, Text text, Set<UUID> receivers)
	{
		this.receivers = receivers;
		this.playerUniqueId = playerUniqueId;
		this.channel = channel;
		this.text = text;
		this.message = message;
	}

	/**
	 *
	 * @return The list of players who receive the message.
	 */
	public Collection<UUID> getReceivers()
	{
		return receivers;
	}

	/**
	 * <p>
	 * Modify the text of the message.
	 * </p>
	 *
	 * @param text The new text value.
	 */
	public void setText(Text text)
	{
		this.text = text;
	}

	/**
	 *
	 * @return Returns the Text instance of message.
	 */
	public Text getText()
	{
		return text;
	}

	/**
	 *
	 * @return The sender of messaghe.
	 */
	public Player getPlayer()
	{
		return (Player) SpongechatAPI.getProvider().getGame().getServer().getPlayer(playerUniqueId);
	}

	/**
	 *
	 * @return The Message instance
	 */
	public Message getMessage()
	{
		return message;
	}

	/**
	 *
	 * @return The channel of message be sended.
	 */
	public Channel getChannel()
	{
		return channel;
	}

	/**
	 *
	 * @return If the event is cancelled (and message not be send) returns <b>true</b> and if not cancelled returns <b>false</b>
	 *
	 */
	@Override
	public boolean isCancelled()
	{
		return cancelled;
	}

	/**
	 * <p>
	 * The the event as cancelled (the message not be send).
	 * </p>
	 * 
	 * @param cancelled <b>true</b> for cancel and <b>false</b> to set as not cancelled.
	 */
	@Override
	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}

}
