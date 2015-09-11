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
package org.sparkpowered.spongechat.commands;

import java.util.UUID;

import org.sparkpowered.spongechat.channels.Channel;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.source.ConsoleSource;
import org.spongepowered.api.util.command.spec.CommandExecutor;

/**
 * <p>
 * This class handle only the commands per channels (e.g. <b>/g {message here}</b>)
 * </p>
 *
 * <p>
 * This class don't contains any API for register commands or something of type
 * </p>
 *
 * @category Command Handlering
 *
 */
public class ChatCommand implements CommandExecutor
{

	private UUID random = null;
	private Channel channel = null;

	/**
	 * <p>
	 * Constructor to set the channel for executor of command.
	 * </p>
	 *
	 * @param channel The channel of command.
	 */
	public ChatCommand(final Channel channel)
	{
		this.channel = channel;
		random = UUID.randomUUID();
	}

	/**
	 *
	 * @return The channel of command.
	 */
	public Channel getChannel()
	{
		return channel;
	}

	/**
	 *
	 * @return The Universal Unique Identification (UUID) of this ChatCommand instance.
	 */
	public UUID getIdentification()
	{
		return random;
	}

	/**
	 * <p>
	 * Handle the command of channel, to send message/etc.
	 * </p>
	 */
	@Override
	public CommandResult execute(final CommandSource sender, final CommandContext contesto) throws CommandException
	{
		if (!(sender instanceof ConsoleSource))
		{

		}
		else
		{
			sender.sendMessage(Texts.of("§4[Spongechat] §cThis command is only-game supported.").builder().build());
			return CommandResult.empty();
		}

		return CommandResult.success();
	}

}
