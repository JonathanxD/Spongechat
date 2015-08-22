package me.kaward.spongechat;

import java.util.UUID;

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

import me.kaward.spongechat.events.ChannelMessageEvent;
import me.kaward.spongechat.lang.Language;

public class ChatCommand implements CommandExecutor
{

	private UUID random = null;
	private Channel channel = null;

	public ChatCommand(Channel channel)
	{
		this.channel = channel;
		this.random = UUID.randomUUID();
	}

	public Channel getChannel()
	{
		return channel;
	}

	public UUID getIdentification()
	{
		return random;
	}

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
