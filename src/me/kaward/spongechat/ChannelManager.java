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
package me.kaward.spongechat;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;
import org.spongepowered.api.util.command.spec.CommandSpec;

import me.kaward.spongechat.logs.ChannelLogs;
import me.kaward.spongechat.providers.IChannelManager;

/**
 * [ENGLISGH] Here you get API to manage (mute, unmute, register channel commands and more) channels.
 *
 * [PORTUGUESE] Aqui você encontra API para gerenciar (mutar, desmutar, registrar comandos de canais e mais) de canais.
 *
 * @author Pitter Thog (Kaward) <https://github.com/Kaward/>
 * @category Channel Management and Services
 *
 */
public class ChannelManager implements IChannelManager
{

	private static Map<Channel, CommandSpec> commands = new HashMap<Channel, CommandSpec>();
	private static Map<Channel, UUID> recipients = new HashMap<Channel, UUID>();
	private static Collection<Channel> mutes = new HashSet<Channel>();

	protected ChannelManager()
	{
	}

	@Override
	public void setMuted(Channel channel, boolean muted)
	{
		assert(channel != null) : "Channel can't be null.";
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
	public void registerCommand(Channel channel, CommandSpec command, String... names)
	{
		assert(channel != null) : "Channel can't be null.";
		assert(command != null) : "Command can't be null.";
		Spongechat sponge = Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
		sponge.getGame().getCommandDispatcher().register(sponge, command, names);
		commands.put(channel, command);
	}

	@Override
	public void registerCommand(Channel channel)
	{
		assert(channel != null) : "Channel can't be null.";
		CommandSpec spec = CommandSpec.builder().permission("spongechat.channel." + channel.getNickname().toLowerCase()).description(Texts.of("Speak in " + channel.getChannelName() + " Channel.")).build();
		registerCommand(channel, spec, channel.getNickname());
	}

	@SuppressWarnings("unused")
	private CommandExecutor getExecutor(final Channel channel)
	{
		assert(channel != null) : "Channel can't be null.";
		return new CommandExecutor()
		{
			@Override
			public CommandResult execute(CommandSource source, CommandContext context) throws CommandException
			{
				return null;
			}
		};
	}

	@Override
	public void delete(Channel channel)
	{
		assert(channel != null) : "Channel can't be null.";
		// TODO: desregistrar o comando
		// TODO: remover todo cache salvo na memório com referência ao canal excluído
		// TODO: remover da lista de canais
		// TODO: adicionar ao log a exclusão
	}

	@Override
	public void setMuted(Collection<Channel> channels, boolean muted)
	{
		assert(channels != null) : "Channel's collection can't be null.";
		for (Channel c : channels)
		{
			setMuted(c, muted);
		}
	}

	@Override
	public void clear(Channel channel)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isMuted(Channel channel)
	{
		return mutes.contains(channel);
	}

	@Override
	public ChannelLogs getLogs()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Entry<Channel, Boolean>> isMuted(Collection<Channel> channels)
	{
		assert(channels != null) : "The channel's collection can't be null.";
		Map<Channel, Boolean> map = new HashMap<Channel, Boolean>();
		for (Channel c : channels)
		{
			boolean m = isMuted(c);
			map.put(c, m);
		}

		return map.entrySet();
	}
}
