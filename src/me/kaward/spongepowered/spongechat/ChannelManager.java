package me.kaward.spongepowered.spongechat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import me.kaward.spongepowered.spongechat.providers.IChannelManager;

import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;
import org.spongepowered.api.util.command.spec.CommandSpec;

public class ChannelManager implements IChannelManager, Serializable
{

	private static final long serialVersionUID = -7802471047038937496L;
	protected transient Map<Spongechannel, CommandSpec> commands = new HashMap<Spongechannel, CommandSpec>();
	protected Map<Spongechannel, UUID> playersInChannel = new HashMap<Spongechannel, UUID>();
	protected Set<Spongechannel> mutedChannels = new HashSet<Spongechannel>();
	protected Set<Spongechannel> channels = new HashSet<Spongechannel>();

	protected ChannelManager()
	{
	}

	@Override
	public void setMuted(Spongechannel channel, boolean muted)
	{
		if (mutedChannels.contains(channel))
		{
			if (!muted)
			{
				mutedChannels.remove(channel);
			}
		}
		else
		{
			if (muted)
			{
				mutedChannels.add(channel);
			}
		}
	}

	@Override
	public void registerSpongechannelCommand(Spongechannel channel, CommandSpec command, String... names)
	{
		Spongechat sponge = Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
		sponge.getGame().getCommandDispatcher().register(sponge, command, names);
		commands.put(channel, command);
	}

	@Override
	public void registerSpongechannelCommand(Spongechannel channel)
	{
		CommandSpec spec = CommandSpec.builder().permission("spongechat.channel." + channel.getNickname().toLowerCase()).description(Texts.of("Speak in " + channel.getChannelName() + " Channel.")).build();
		registerSpongechannelCommand(channel, spec, channel.getNickname());
	}

	@SuppressWarnings("unused")
	private CommandExecutor getExecutor(final Spongechannel channel)
	{
		return new CommandExecutor()
		{
			@Override
			public CommandResult execute(CommandSource source, CommandContext context) throws CommandException
			{
				return null;
			}
		};
	}
}
