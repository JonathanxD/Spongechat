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
package me.kaward.spongepowered.spongechat.providers;

import java.util.Set;

import javax.annotation.Nullable;

import me.kaward.spongepowered.spongechat.Spongechannel;

import org.spongepowered.api.entity.player.Player;

public interface IPlayerManager
{

	public Set<Spongechannel> getActiveChannels(Player player);

	public void mutePlayer(Player player, Spongechannel channel, boolean mute);

	public void mutePlayer(Player player, boolean mute);

	public void setFocus(Player player, Spongechannel channel);

	public void setPlayerTotalMessages(Player player, Integer total);

	public void setPlayerAfk(Player player, boolean afk);

	public boolean isAfk(Player player);

	public int activeChannels(Player player);

	@Nullable
	public int getPlayerTotalMessages(Player player);

	@Nullable
	public Spongechannel getFocusedChannel(Player player);

}
