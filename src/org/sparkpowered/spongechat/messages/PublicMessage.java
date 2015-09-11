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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.channels.Channel;
import org.sparkpowered.spongechat.events.ChannelMessageEvent;
import org.sparkpowered.spongechat.formats.Tag;
import org.sparkpowered.spongechat.formats.Template;
import org.sparkpowered.spongechat.utils.Locations;
import org.sparkpowered.spongechat.utils.Strings;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.util.command.spec.CommandSpec;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.google.common.base.Optional;

/**
 *
 * @category Message Management and Service
 *
 */
public class PublicMessage implements Message
{

	private final Set<UUID> receivers = new HashSet<UUID>();
	private final UUID messageId = UUID.randomUUID();
	private UUID sentBy = null;
	private Channel channel = null;
	private String text = null;
	private String formatted = "";
	private String sentOn = "";
	private Location<World> location = null;
	private boolean sended = false;

	public PublicMessage(final Player sentBy, final Channel channel, final String message)
	{
		this.sentBy = sentBy.getUniqueId();
		this.channel = channel;
		text = message;
		location = sentBy.getLocation();
	}

	@Override
	public void send()
	{
		final Player sender = SpongechatAPI.getProvider().getGame().getServer().getPlayer(sentBy).get();
		final Set<UUID> receivers = new HashSet<UUID>();
		for (final Player p : SpongechatAPI.getProvider().getGame().getServer().getOnlinePlayers())
		{
			if (p.getUniqueId().equals(sender.getUniqueId()) == false)
			{
				if (receivers.contains(p.getUniqueId()) == false)
				{
					if (!getChannel().isCrossworld() && Locations.distance(sender.getLocation(), p.getLocation()) <= channel.getDistance())
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

		final Text t = createText();
		sentOn = Strings.timenow();

		for (final UUID unique : getReceivers())
		{
			final Player receiver = SpongechatAPI.getProvider().getGame().getServer().getPlayer(unique).get();
			receiver.sendMessage(t);
		}

		setSended(true);
	}

	@Override
	public void setSended(final boolean sended)
	{
		this.sended = sended;
	}

	@Override
	public boolean isSended()
	{
		return sended;
	}

	public ChannelMessageEvent callEvent()
	{
		final ChannelMessageEvent event = new ChannelMessageEvent(getSender(), getChannel(), this, getMessageText(), getReceivers());
		SpongechatAPI.getProvider().getGame().getEventManager().post(event);
		return event;
	}

	@Override
	public void setMessageText(final String text)
	{
		this.text = text;
	}

	public Set<UUID> getReceivers()
	{
		return receivers;
	}

	@Override
	public UUID getSender()
	{
		return sentBy;
	}

	@Override
	public void setSender(final UUID sentBy)
	{
		this.sentBy = sentBy;
	}

	public Channel getChannel()
	{
		return channel;
	}

	public void setChannel(final Channel channel)
	{
		this.channel = channel;
	}

	@Override
	public List<Precondition> preconditions()
	{
		final Optional<Player> optional = SpongechatAPI.getProvider().getGame().getServer().getPlayer(getSender());
		final List<Precondition> set = new ArrayList<Precondition>();
		if (!optional.isPresent())
		{
			set.add(Precondition.PLAYER_NOT_PRESENT);
			set.add(Precondition.FAILED_WITH_REASON);
			return set;
		}

		final Player sender = optional.get();
		if (!SpongechatAPI.getPlayerManager().getActiveChannels(sender).contains(getChannel()))
		{
			set.add(Precondition.PLAYER_NOT_IN_CHANNEL);
			set.add(Precondition.FAILED_WITH_REASON);
			return set;
		}

		final Optional<Entry<Channel, CommandSpec>> optional2 = Optional.fromNullable(SpongechatAPI.getChannelManager().getChannel(getChannel().getName(), false));
		if (!optional2.isPresent())
		{
			set.add(Precondition.CHANNEL_NOT_PRESENT);
			set.add(Precondition.FAILED_WITH_REASON);
			return set;
		}

		final Channel channel = optional2.get().getKey();

		if (SpongechatAPI.getChannelManager().isMuted(channel))
		{
			set.add(Precondition.CHANNEL_MUTED);
			set.add(Precondition.FAILED_WITH_REASON);
			return set;
		}

		if (SpongechatAPI.getPlayerManager().isMuted(sender, channel))
		{
			set.add(Precondition.PLAYER_MUTED);
			set.add(Precondition.FAILED_WITH_REASON);
			return set;
		}

		if (SpongechatAPI.getDelayManager().hasActive(sender, channel))
		{
			set.add(Precondition.PLAYER_HAS_DELAY_ACTIVE);
			set.add(Precondition.FAILED_WITH_REASON);
			return set;
		}

		final String format = getChannel().getFormat().content();

		for (final Tag tag : Template.getTags())
		{
			final String forReplace = Strings.bracket(tag.keyword(), tag.brackets());
			format.replace(forReplace, tag.content());
		}

		String message = getMessageText();
		message = formatColors(sender, message);

		if (Patterns.match(Strings.removeCodes(message)))
		{
			set.add(Precondition.MESSAGE_BLOCKED);
			set.add(Precondition.FAILED_WITH_REASON);
			return set;
		}

		format.replace(Strings.bracket("message", "{}"), message);
		format.replace(Strings.bracket("player", "{}"), sender.getName());
		format.replace(Strings.bracket("displayname", "{}"), Texts.toPlain(sender.getDisplayNameData().displayName().get()));
		format.replace(Strings.bracket("nick", "{}"), channel.getNickname());
		format.replace(Strings.bracket("channel", "{}"), channel.getName());

		setMessageText(message);
		setFormatted(format);
		set.add(Precondition.PREPARED_TO_SEND);
		return set;
	}

	private void setFormatted(final String format)
	{
		formatted = format;
	}

	private String formatColors(final Player sender, final String message)
	{

		for (int i = 0; i < message.length(); i++)
		{
			final char c = message.charAt(i);
			if (c == '§' || c == '&')
			{
				if ((i + 1) < message.length())
				{
					final char c2 = message.charAt(i + 1);
					if (Strings.codes(c2))
					{
						if (sender.hasPermission("spongechat." + getChannel().getNickname() + ".codes." + String.valueOf(c2).toLowerCase()))
						{
							if (c == '&')
							{
								message.replaceFirst("&" + c2, "§" + c2);
							}
						}
						else
						{
							if (c == '§')
							{
								message.replaceFirst("§" + c2, "");
							}
							else if (c == '&')
							{
								message.replaceFirst("&" + c2, "");
							}
						}
					}
					else
					{
						if (c == '§')
						{
							message.replaceFirst("§", "");
						}
						else if (c == '&')
						{
							message.replaceFirst("&", "");
						}
					}
				}
			}
		}

		return message;
	}

	public Text createText()
	{
		return Texts.of(formatted).builder().onClick(TextActions.runCommand("spongechat -getmsginfo " + messageId)).build();
	}

	@Override
	public UUID getIdentification()
	{
		return messageId;
	}

	@Override
	public String getMessageText()
	{
		return text;
	}

	@Override
	public String getSentDate()
	{
		return sentOn;
	}

	@Override
	public Location<World> getSendLocation()
	{
		return location;
	}

}
