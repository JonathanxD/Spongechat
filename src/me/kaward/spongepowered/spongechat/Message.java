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
 **/
package me.kaward.spongepowered.spongechat;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import me.kaward.spongepowered.spongechat.events.ChannelMessageEvent;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;

@SuppressWarnings("unused")
public class Message
{

	private static final long serialVersionUID = -2817680756335125068L;
	private UUID messageId = UUID.randomUUID();
	private UUID sentBy = null;
	private Spongechannel channel = null;
	private String unformattedMessage = "";
	private Text text = null;
	private String timeFormat = "";

	public Message(Player sentBy, Spongechannel channel, String message)
	{
		this.sentBy = sentBy.getUniqueId();
		this.channel = channel;
		this.unformattedMessage = message;
	}

	public Message(Player sentBy, Spongechannel channel, Text text)
	{
		this.sentBy = sentBy.getUniqueId();
		this.channel = channel;
		this.unformattedMessage = Texts.toPlain(text).replace("§", "&");
	}

	@SuppressWarnings("deprecation")
	public void send()
	{
		Spongechat sponge = Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
		Player sender = sponge.getGame().getServer().getPlayer(sentBy).get();
		Set<UUID> receivers = new HashSet<UUID>();
		for (Player p : sponge.getGame().getServer().getOnlinePlayers())
		{
			if (p.getUniqueId().equals(sender.getUniqueId()) == false)
			{
				if (receivers.contains(p.getUniqueId()) == false)
				{
					if (!getChannel().isCrossworld() && Distance.build(sender.getLocation(), p.getLocation()) <= channel.getDistance())
					{
						receivers.add(p.getUniqueId());
						continue;
					}
					else
					{
						receivers.add(p.getUniqueId());
						continue;
					}
				}
			}
		}

		ChannelMessageEvent event = new ChannelMessageEvent(sentBy, channel, this, Texts.of(unformattedMessage).builder().build(), receivers);
		Spongechat.sponge.getEventManager().post(event);
		if (event.isCancelled())
		{
			return;
		}

		// ---> Falta carregar o 'Format', por em quanto fica esse padrão;
		String format = "§e[{n}] §r{group_prefixes} {chat_prefix} §f{nick}: §e{msg}";

		for (UUID unique : event.getReceivers())
		{
			Player p = sponge.getGame().getServer().getPlayer(unique).get();
			p.sendMessage(Texts.of(Texts.replaceCodes(format, '&')).builder().build());
		}
	}

	public void setText(Text text)
	{
		this.text = text;
	}

	public Text getText()
	{
		return text;
	}

	public UUID getMessageId()
	{
		return messageId;
	}

	public void setMessageId(UUID messageId)
	{
		this.messageId = messageId;
	}

	public UUID getSentBy()
	{
		return sentBy;
	}

	public void setSentBy(UUID sentBy)
	{
		this.sentBy = sentBy;
	}

	public Spongechannel getChannel()
	{
		return channel;
	}

	public void setChannel(Spongechannel channel)
	{
		this.channel = channel;
	}

	public String getUnformattedMessage()
	{
		return unformattedMessage;
	}

	public void setUnformattedMessage(String unformattedMessage)
	{
		this.unformattedMessage = unformattedMessage;
	}

	public String getTimeFormat()
	{
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat)
	{
		this.timeFormat = timeFormat;
	}

}
