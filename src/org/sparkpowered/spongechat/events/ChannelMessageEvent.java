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
package org.sparkpowered.spongechat.events;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.channels.Channel;
import org.sparkpowered.spongechat.messages.PublicMessage;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.AbstractEvent;
import org.spongepowered.api.event.Cancellable;

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
	private String text = null;
	private PublicMessage message = null;
	private Boolean cancelled = false;

	public ChannelMessageEvent(final UUID playerUniqueId, final Channel channel, final PublicMessage message, final String text, final Set<UUID> receivers)
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
	public void setMessage(final String text)
	{
		this.text = text;
	}

	/**
	 *
	 * @return Returns the Text instance of message.
	 */
	public String getPlayerMessage()
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
	public PublicMessage getMessage()
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
	public void setCancelled(final boolean cancelled)
	{
		this.cancelled = cancelled;
	}

}
