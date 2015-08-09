package me.kaward.spongepowered.spongechat.providers;

import java.util.Set;

import javax.annotation.Nullable;

import me.kaward.spongepowered.spongechat.Spongechannel;

import org.spongepowered.api.entity.player.Player;

public interface IPlayerManager
{

	public Set<Spongechannel> getActiveChannels(Player player);

	public void setFocus(Player player, Spongechannel channel);

	public void setPlayerTotalMessages(Player player, Integer total);

	public void setPlayerAfk(Player player, Boolean afk);

	public boolean isAfk(Player player);

	public int activeChannels(Player player);

	@Nullable
	public int getPlayerTotalMessages(Player player);

	@Nullable
	public Spongechannel getFocusedChannel(Player player);

}
