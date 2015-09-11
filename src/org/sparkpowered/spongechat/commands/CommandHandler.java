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

import org.sparkpowered.spongechat.context.Context;
import org.sparkpowered.spongechat.context.ContextList;
import org.sparkpowered.spongechat.context.pack.ContextFactory;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.source.ConsoleSource;
import org.spongepowered.api.util.command.spec.CommandExecutor;

/**
 *
 * @author Pitter Thog (Kaward) <https://github.com/Kaward/>
 * @category Handler
 *
 */
public class CommandHandler
{

	public CommandHandler()
	{
	}

	public CommandExecutor SpongechatCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(final CommandSource cs, final CommandContext cc) throws CommandException
		{
			cs.sendMessage(Texts.of("Available commands for Spongechat (You can click on command to get description and get how-use):"));
			return HelpCommandExecutor.execute(cs, cc);
		}
	};

	public CommandExecutor HelpCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(final CommandSource cs, final CommandContext cc) throws CommandException
		{
			int page = 1;

			if (cc.hasAny("page"))
			{
				try
				{
					page = Integer.parseInt(cc.<String> getOne("page").get());
				}
				catch (final NumberFormatException ex1)
				{
					cs.sendMessage(Texts.of("§cThe \"page\" must be a number").builder().build());
					return CommandResult.empty();
				}
			}

			cs.sendMessage(Texts.of(String.format("§6[Spongechat] §eCommands of Spongechat [Page %s of 5]:", page)).builder().build());
			if (page == 1)
			{
			}

			return CommandResult.success();
		}
	};

	public CommandExecutor CreateChannelCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(final CommandSource cs, final CommandContext cc) throws CommandException
		{
			if (cs instanceof ConsoleSource)
			{
				cs.sendMessage(Texts.of("§6[Spongechat] §eThis command is only-game supported."));
			}
			else
			{
				if (!ContextList.cand((Player) cs))
				{
					cs.sendMessage(Texts.of("§4[Spongechat] §cYou already are in one context, send §4@exit§c on the chat to quit."));
				}
				else
				{
					cs.sendMessage(Texts.of("§6[Spongechat] §eYou entered on the Context Mode for create an channel (in this mode, you don't need to use commands, only chat)"));
					ContextFactory.setContext(((Player) cs).getUniqueId(), Context.CHANNEL_CREATED_CONTEXT);
				}
			}

			return CommandResult.builder().affectedBlocks(0).affectedItems(0).build();
		}
	};

	public CommandExecutor RemoveChannelCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(final CommandSource cs, final CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	public CommandExecutor MutePlayerCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(final CommandSource cs, final CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	public CommandExecutor UnmutePlayerCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(final CommandSource cs, final CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	public CommandExecutor MuteChannelCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(final CommandSource cs, final CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	public CommandExecutor UnmuteChannelCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(final CommandSource cs, final CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

}
