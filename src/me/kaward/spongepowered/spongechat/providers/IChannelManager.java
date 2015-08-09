package me.kaward.spongepowered.spongechat.providers;

import me.kaward.spongepowered.spongechat.Spongechannel;

import org.spongepowered.api.util.command.spec.CommandSpec;

public interface IChannelManager
{

	public void setMuted(Spongechannel channel, boolean muted);

	public void registerSpongechannelCommand(Spongechannel channel, CommandSpec command, String... names);

	public void registerSpongechannelCommand(Spongechannel channel);

}
