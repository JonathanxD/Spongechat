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
package org.sparkpowered.spongechat;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.sparkpowered.spongechat.logs.ChannelLogs;
import org.sparkpowered.spongechat.providers.IChannelManager;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.spec.CommandSpec;

import com.google.common.base.Preconditions;

/**
 * <p>
 * This class is used to handle channels with API. Here, you can mute and unmute channels, register channel's commands, etc.
 * </p>
 *
 * @category Channel Handlering
 *
 */
public class ChannelManager implements IChannelManager
{

	@Nonnull
	private static Map<Channel, CommandSpec> commands = new HashMap<Channel, CommandSpec>();

	@Nonnull
	private static Map<Channel, UUID> recipients = new HashMap<Channel, UUID>();

	@Nonnull
	private static Collection<Channel> mutes = new HashSet<Channel>();

	protected ChannelManager()
	{
	}

	@Override
	public void setMuted(@Nonnull final Channel channel, final boolean muted)
	{
		Preconditions.checkNotNull(channel, "Channel can't be null.");
		if (mutes.contains(channel))
		{
			if (!muted)
			{
				mutes.remove(channel);
			}
		}
		else
		{
			if (muted)
			{
				mutes.add(channel);
			}
		}
	}

	@Override
	public void registerCommand(@Nonnull final Channel channel, @Nonnull final CommandSpec command, final String... names)
	{
		Preconditions.checkNotNull(channel, "Channel can't be null.");
		Preconditions.checkNotNull(command, "Command can't be null.");
		final Spongechat sponge = Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
		sponge.getGame().getCommandDispatcher().register(sponge, command, names);
		commands.put(channel, command);
	}

	@Override
	public void registerCommand(@Nonnull final Channel channel)
	{
		Preconditions.checkNotNull(channel, "Channel can't be null.");
		final CommandSpec spec = CommandSpec.builder().permission("spongechat.channel." + channel.getNickname().toLowerCase()).description(Texts.of("Speak in " + channel.getChannelName() + " Channel.")).build();
		registerCommand(channel, spec, channel.getNickname());
	}

	@Override
	public void delete(@Nonnull final Channel channel)
	{
		Preconditions.checkNotNull(channel, "Channel can't be null.");
		// TODO: desregistrar o comando
		// TODO: remover todo cache salvo na memório com referência ao canal excluído
		// TODO: remover da lista de canais
		// TODO: adicionar ao log a exclusão
	}

	@Override
	public void setMuted(@Nonnull final Collection<Channel> channels, final boolean muted)
	{
		Preconditions.checkNotNull(channels, "Channel's collection can't be null.");
		for (final Channel c : channels)
		{
			setMuted(c, muted);
		}
	}

	@Override
	public boolean isMuted(@Nonnull final Channel channel)
	{
		Preconditions.checkNotNull(channel, "Channel can't be null.");
		return mutes.contains(channel);
	}

	@Override
	public ChannelLogs getLogs(@Nonnull final Channel channel)
	{
		Preconditions.checkNotNull(channel, "Channel can't be null.");
		return null;
	}

	@Override
	public Collection<Entry<Channel, Boolean>> isMuted(@Nonnull final Collection<Channel> channels)
	{
		Preconditions.checkNotNull(channels, "Channel's collection can't be null.");
		final Map<Channel, Boolean> map = new HashMap<Channel, Boolean>();
		for (final Channel c : channels)
		{
			final boolean m = isMuted(c);
			map.put(c, m);
		}

		return map.entrySet();
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void clear(@Nonnull final Player player)
	{
		Preconditions.checkNotNull(player, "Player can't be null.");
	}

}
