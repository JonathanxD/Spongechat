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
package me.kaward.spongechat.providers;

import java.util.Collection;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import org.spongepowered.api.entity.player.Player;

import me.kaward.spongechat.Channel;

public interface IPlayerManager
{

	public Collection<Channel> getActiveChannels(Player player);

	public void mutePlayer(Player player, Channel channel, boolean mute);

	public void mutePlayer(Player player, boolean mute);

	public void setFocus(Player player, Channel channel);

	public void setPlayerTotalMessages(Player player, Integer total);

	public void setPlayerAfk(Player player, boolean afk);

	public boolean isAfk(Player player);

	public boolean isMuted(Player player, Channel channel);

	public Collection<Entry<Channel, Boolean>> isMuted(Player player, Collection<Channel> channels);

	public int activeChannels(Player player);

	@Nullable
	public int getPlayerTotalMessages(Player player);

	@Nullable
	public Channel getFocusedChannel(Player player);

}
