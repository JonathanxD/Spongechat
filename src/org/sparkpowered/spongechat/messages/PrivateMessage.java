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
package org.sparkpowered.spongechat.messages;

import java.util.List;
import java.util.UUID;

import org.sparkpowered.spongechat.SpongechatAPI;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class PrivateMessage implements Message
{

	private UUID tellId = null;
	private Player sender = null;
	private Player target = null;
	private String text = "";
	private final String sent = "";

	@SuppressWarnings("unused")
	private Text formatted = null;

	private Location<World> pos = null;
	private boolean sended = false;

	public PrivateMessage(final Player sender, final Player target, final String text)
	{
		tellId = UUID.randomUUID();
		sended = false;
		formatted = null;
		this.sender = sender;
		this.target = target;
		this.text = text;
	}

	public void setTarget(final UUID player)
	{
		target = SpongechatAPI.getProvider().getGame().getServer().getPlayer(player).orNull();
	}

	public void setTarget(final Player player)
	{
		target = player;
	}

	public Player getTarget()
	{
		return target;
	}

	@Override
	public UUID getIdentification()
	{
		return tellId;
	}

	@Override
	public String getMessageText()
	{
		return text;
	}

	@Override
	public String getSentDate()
	{
		return sent;
	}

	@Override
	public Location<World> getSendLocation()
	{
		return pos;
	}

	@Override
	public List<Precondition> preconditions()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID getSender()
	{
		return sender.getUniqueId();
	}

	@Override
	public void setSender(final UUID player)
	{
		sender = SpongechatAPI.getProvider().getGame().getServer().getPlayer(player).orNull();
	}

	@Override
	public void setSended(final boolean isSended)
	{
		sended = isSended;
	}

	@Override
	public boolean isSended()
	{
		return sended;
	}

	@Override
	public void setMessageText(final String message)
	{
		text = message;
	}

	@Override
	public void send()
	{
		pos = sender.getLocation();
	}

}
