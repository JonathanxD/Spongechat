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
package me.kaward.spongepowered.spongechat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import me.kaward.spongepowered.spongechat.providers.IPlayerManager;

import org.spongepowered.api.entity.player.Player;

public class PlayerManager implements IPlayerManager
{

	protected Map<UUID, Set<Spongechannel>> channels = new HashMap<UUID, Set<Spongechannel>>();
	protected Map<UUID, Set<Spongechannel>> mutes = new HashMap<UUID, Set<Spongechannel>>();
	protected Map<UUID, Spongechannel> focus = new HashMap<UUID, Spongechannel>();
	protected Map<UUID, Integer> count = new HashMap<UUID, Integer>();
	protected Set<UUID> afk = new HashSet<UUID>();

	protected PlayerManager()
	{
	}

	@Override
	public void setFocus(Player player, Spongechannel channel)
	{
		focus.put(player.getUniqueId(), channel);
	}

	@Override
	public void setPlayerTotalMessages(Player player, Integer total)
	{
		count.put(player.getUniqueId(), total);
	}

	@Nullable
	@Override
	public int getPlayerTotalMessages(Player player)
	{
		return (count.containsKey(player.getUniqueId()) ? count.get(player.getUniqueId()) : 0);
	}

	@Nullable
	@Override
	public Spongechannel getFocusedChannel(Player player)
	{
		return focus.get(player.getUniqueId());
	}

	@Override
	public boolean isAfk(Player player)
	{
		return afk.contains(player.getUniqueId());
	}

	public boolean isAfk(UUID uuid)
	{
		return afk.contains(uuid);
	}

	@Override
	public int activeChannels(Player player)
	{
		return (channels.containsKey(player.getUniqueId()) ? channels.get(player.getUniqueId()).size() : 0);
	}

	@Override
	public Set<Spongechannel> getActiveChannels(Player player)
	{
		return channels.get(player.getUniqueId());
	}

	@Override
	public void setPlayerAfk(Player player, boolean isafk)
	{
		if (isafk)
		{
			afk.add(player.getUniqueId());
		}
		else
		{
			afk.remove(player.getUniqueId());
		}
	}

	@Override
	public void mutePlayer(Player player, Spongechannel channel, boolean mute)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mutePlayer(Player player, boolean mute)
	{
		if (mute)
		{
			mutes.put(player.getUniqueId(), Channel.ALL_CHANNELS);
		}
	}

}
