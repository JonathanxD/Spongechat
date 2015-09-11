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

import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.messages.PrivateMessage;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.AbstractEvent;
import org.spongepowered.api.event.Cancellable;

public class PrivateMessageEvent extends AbstractEvent implements Cancellable
{

	private Player sender = null;
	private Player receiver = null;
	private PrivateMessage message = null;
	private boolean cancelled;

	public PrivateMessageEvent(final PrivateMessage message)
	{
		this.message = message;
		sender = SpongechatAPI.getProvider().getGame().getServer().getPlayer(message.getSender()).orNull();
		receiver = message.getTarget();
	}

	public Player getSender()
	{
		return sender;
	}

	public Player getTarget()
	{
		return receiver;
	}

	public PrivateMessage getPrivateMessage()
	{
		return message;
	}

	@Override
	public boolean isCancelled()
	{
		return cancelled;
	}

	@Override
	public void setCancelled(final boolean cancel)
	{
		cancelled = cancel;
	}

}
