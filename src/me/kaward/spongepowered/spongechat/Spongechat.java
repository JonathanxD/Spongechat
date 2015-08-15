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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.logging.Logger;

import me.kaward.spongepowered.spongechat.providers.IChannelManager;
import me.kaward.spongepowered.spongechat.providers.IPlayerManager;
import me.kaward.spongepowered.spongechat.providers.ISpongechat;

import org.spongepowered.api.Game;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.ProviderExistsException;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.args.GenericArguments;
import org.spongepowered.api.util.command.spec.CommandSpec;

import com.google.inject.Inject;

@Plugin(id = "Spongechat", name = "Spongechat", version = "1.0-A")
public class Spongechat implements ISpongechat
{

	public static Game sponge = null;

	// Manipulador de execução de comandos
	protected CommandHandler commands = new CommandHandler();

	// The Sponge Command ("/spongechat help")
	protected CommandSpec HelpCommand = CommandSpec.builder().description(Texts.of("Command to get Help of Spongechat.")).permission("spongechat.cmd.help").executor(commands.HelpCommandExecutor).arguments(GenericArguments.integer(Texts.of("page"))).build();

	// The Sponge Command
	// ({/spongechat createchannel "nick" "name" "colors" "math" "crossworld"
	// "distance" "delay" "cost" "format"})
	protected CommandSpec CreateChannelCommand = CommandSpec.builder().description(Texts.of("Command to create a existent Spongechannel.")).permission("spongechat.cmd.create").executor(commands.CreateChannelCommandExecutor).build();

	// The Sponge Command ("/spongechat removechannel")
	protected CommandSpec RemoveChannelCommand = CommandSpec.builder().description(Texts.of("Command to remove a existent Spongechannel.")).permission("spongechat.cmd.remove").executor(commands.RemoveChannelCommandExecutor).arguments(GenericArguments.onlyOne(GenericArguments.string(Texts.of("channelname")))).build();

	// The Sponge Command ("/spongechat muteplayer")
	protected CommandSpec MutePlayerCommand = CommandSpec.builder().description(Texts.of("Command to mute a player in one Spongechannel (or all Spongechannels)")).permission("spongechat.cmd.mute").executor(commands.MutePlayerCommandExecutor).arguments(GenericArguments.onlyOne(GenericArguments.string(Texts.of("player"))), GenericArguments.optional(GenericArguments.string(Texts.of("channelname")))).build();

	// The Sponge Command ("/spongechat unmuteplayer")
	protected CommandSpec UnmutePlayerCommand = CommandSpec.builder().description(Texts.of("Command to unmute a player in one Spongechannel (or all Spongechannels)")).permission("spongechat.cmd.unmute").executor(commands.UnmutePlayerCommandExecutor).arguments(GenericArguments.onlyOne(GenericArguments.string(Texts.of("player"))), GenericArguments.optional(GenericArguments.string(Texts.of("channelname")))).build();;

	// The Sponge Command ("/spongechat mutechannel")
	protected CommandSpec MuteChannelCommand = CommandSpec.builder().description(Texts.of("Command to mute one Spongechannel (or all Spongechannels)")).permission("spongechat.cmd.mute.channel").executor(commands.MuteChannelCommandExecutor).arguments(GenericArguments.onlyOne(GenericArguments.string(Texts.of("channelname")))).build();

	// The Sponge Command ("/spongechat unmutechannel")
	protected CommandSpec UnmuteChannelCommand = CommandSpec.builder().description(Texts.of("Command to unmute one Spongechannel (or all Spongechannels)")).permission("spongechat.cmd.unmute.channel").executor(commands.UnmuteChannelCommandExecutor).arguments(GenericArguments.onlyOne(GenericArguments.string(Texts.of("channelname")))).build();;

	// The Sponge Command ("/spongechat")
	protected CommandSpec SpongechatCommand = CommandSpec.builder().description(Texts.of("Command to handle Spongechat.")).permission("spongechat.cmd.help").executor(commands.SpongechatCommandExecutor).child(HelpCommand, "help", "-h", "ajuda", "commands", "comandos", "-c").child(CreateChannelCommand, "createchannel", "cc", "-cc", "criarcanal", "createspongechannel").child(RemoveChannelCommand, "removechannel", "rc", "-rc", "removercanal", "removespongechannel").child(MutePlayerCommand, "muteplayer", "mp", "-mp", "mutarjogador", "-mj", "mj").child(UnmutePlayerCommand, "unmuteplayer", "ump", "-ump", "desmutarjogador", "-dsj", "dsj").child(MuteChannelCommand, "mutechannel", "mc", "-mc", "mutarcanal", "-mtc", "mtc").child(UnmuteChannelCommand, "unmutechannel", "umtc", "desmutarcanal", "-dsmc", "dsmc").build();

	@Inject
	private Logger logger = null;

	@Subscribe(order = Order.FIRST)
	public void onInit(PreInitializationEvent event) throws IOException, SQLException, ProviderExistsException, ClassNotFoundException
	{
		Spongechat.sponge = event.getGame();
		logger.info("Starting the Spongechat, please, be patient.");

		logger.info("Checking the configuration file (config.yml)");
		File config = new File("Spongechat" + File.separator + "config.yml");
		if (!config.exists())
		{
			config.mkdirs();
			InputStream link = (getClass().getResourceAsStream("config.yml"));
			Files.copy(link, config.getAbsoluteFile().toPath());
		}

		Spongechat.sponge.getServiceManager().setProvider(this, ISpongechat.class, this);
		Spongechat.sponge.getServiceManager().setProvider(this, IChannelManager.class, new ChannelManager());
		Spongechat.sponge.getServiceManager().setProvider(this, IPlayerManager.class, new PlayerManager());
		SpongechatAPI.getChannelLoader().loadChannels(true);

		Spongechat.sponge.getCommandDispatcher().register(this, SpongechatCommand, "spongechat", "spc");
	}

	@Subscribe
	public void onStop(ServerStoppingEvent event)
	{

	}

	@Override
	public Game getGame()
	{
		return Spongechat.sponge;
	}

	@Override
	public CommandHandler getCommands()
	{
		return commands;
	}

	@Override
	public CommandSpec getHelpCommand()
	{
		return HelpCommand;
	}

	@Override
	public CommandSpec getCreateChannelCommand()
	{
		return CreateChannelCommand;
	}

	@Override
	public CommandSpec getRemoveChannelCommand()
	{
		return RemoveChannelCommand;
	}

	@Override
	public CommandSpec getMutePlayerCommand()
	{
		return MutePlayerCommand;
	}

	@Override
	public CommandSpec getUnmutePlayerCommand()
	{
		return UnmutePlayerCommand;
	}

	@Override
	public CommandSpec getMuteChannelCommand()
	{
		return MuteChannelCommand;
	}

	@Override
	public CommandSpec getUnmuteChannelCommand()
	{
		return UnmuteChannelCommand;
	}

	@Override
	public CommandSpec getSpongechatCommand()
	{
		return SpongechatCommand;
	}

	@Override
	public Logger getLogger()
	{
		return logger;
	}

}
