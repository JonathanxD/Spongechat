package me.kaward.spongepowered.spongechat.providers;

import java.util.logging.Logger;

import me.kaward.spongepowered.spongechat.CommandHandler;

import org.spongepowered.api.Game;
import org.spongepowered.api.util.command.spec.CommandSpec;

public interface ISpongechat
{

	public Game getGame();

	public CommandHandler getCommands();

	public CommandSpec getHelpCommand();

	public CommandSpec getCreateChannelCommand();

	public CommandSpec getRemoveChannelCommand();

	public CommandSpec getMutePlayerCommand();

	public CommandSpec getUnmutePlayerCommand();

	public CommandSpec getMuteChannelCommand();

	public CommandSpec getUnmuteChannelCommand();

	public CommandSpec getSpongechatCommand();

	public Logger getLogger();

}
