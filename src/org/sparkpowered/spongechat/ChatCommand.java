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

import java.util.UUID;

import org.sparkpowered.spongechat.events.ChannelMessageEvent;
import org.sparkpowered.spongechat.lang.Language;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.action.TextActions;
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
	public ChatCommand(Channel channel)
	{
		this.channel = channel;
		this.random = UUID.randomUUID();
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
	public CommandResult execute(CommandSource sender, CommandContext contesto) throws CommandException
	{
		if (!(sender instanceof ConsoleSource))
		{
			if (contesto.hasAny("message"))
			{
				String playerMessage = String.valueOf(contesto.<String> getOne("message"));
				PlayerManager pm = SpongechatAPI.getPlayerManager();
				ChannelManager cm = SpongechatAPI.getChannelManager();

				if (pm.isMuted((Player) sender, getChannel()))
				{
					String s = Language.YOU_ARE_MUTED_FROM_THIS_CHANNEL;
					s.replace("@channel", getChannel().getChannelName());
					s.replace("@channelnick", getChannel().getNickname());
					sender.sendMessage(Texts.of(s).builder().build());
					return CommandResult.empty();
				}

				if (cm.isMuted(getChannel()))
				{
					String s = Language.CHANNEL_IS_MUTED;
					s.replace("@channel", getChannel().getChannelName());
					s.replace("@channelnick", getChannel().getNickname());
					sender.sendMessage(Texts.of(s).builder().build());
					return CommandResult.empty();
				}

				// TODO: checar delay no canal

				Message message = new Message((Player) sender, getChannel(), playerMessage);
				String format = Formats.DEFAULT;
				Text builder = Texts.of(format).builder().onClick(TextActions.runCommand("spongechat -psgi " + message.getMessageId().toString())).build();
				message.setText(builder);

				ChannelMessageEvent handler = message.call();

				if (!(handler.isCancelled()))
				{
					handler.getMessage().send();
				}
			}
			else
			{
				sender.sendMessage(Texts.of("§4[" + getChannel().getNickname().toUpperCase() + "] §cYou don't writed the message."));
			}
		}
		else
		{
			sender.sendMessage(Texts.of("§4[Spongechat] §cThis command is only-game supported.").builder().build());
			return CommandResult.empty();
		}

		return CommandResult.success();
	}

}
