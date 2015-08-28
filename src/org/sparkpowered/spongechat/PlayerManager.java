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
package org.sparkpowered.spongechat;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import org.sparkpowered.spongechat.providers.IPlayerManager;
import org.spongepowered.api.entity.player.Player;

/**
 * This class contains API for players
 *
 * @author Pitter Thog (Kaward) <https://github.com/Kaward/>
 * @category Handler
 *
 */
public class PlayerManager implements IPlayerManager
{

	private static Map<UUID, Collection<Channel>> channels = new HashMap<UUID, Collection<Channel>>();
	private static Map<UUID, Collection<Channel>> mutes = new HashMap<UUID, Collection<Channel>>();
	private static Map<UUID, Channel> focus = new HashMap<UUID, Channel>();
	private static Map<UUID, Integer> count = new HashMap<UUID, Integer>();
	private static Collection<UUID> afk = new HashSet<UUID>();

	protected PlayerManager()
	{
	}

	@Override
	public void setFocus(Player player, Channel channel)
	{
		assert(player != null) : "Player can't be null.";
		assert(channel != null) : "Channel can't be null.";
		focus.put(player.getUniqueId(), channel);
	}

	@Override
	public void setPlayerTotalMessages(Player player, Integer total)
	{
		assert(player != null) : "Player can't be null.";
		assert(total >= 0) : "Number can't be < 0";
		count.put(player.getUniqueId(), total);
	}

	@Nullable
	@Override
	public int getPlayerTotalMessages(Player player)
	{
		assert(player != null) : "Player can't be null.";
		return (count.containsKey(player.getUniqueId()) ? count.get(player.getUniqueId()) : 0);
	}

	@Nullable
	@Override
	public Channel getFocusedChannel(Player player)
	{
		assert(player != null) : "Player can't be null.";
		return focus.get(player.getUniqueId());
	}

	@Override
	public boolean isAfk(Player player)
	{
		assert(player != null) : "Player can't be null.";
		return afk.contains(player.getUniqueId());
	}

	public boolean isAfk(UUID uuid)
	{
		assert(uuid != null) : "UUID can't be null.";
		return afk.contains(uuid);
	}

	@Override
	public int activeChannels(Player player)
	{
		assert(player != null) : "Player can't be null.";
		return (channels.containsKey(player.getUniqueId()) ? channels.get(player.getUniqueId()).size() : 0);
	}

	@Override
	public Collection<Channel> getActiveChannels(Player player)
	{
		assert(player != null) : "Player can't be null.";

		if (channels.containsKey(player.getUniqueId()))
		{
			return channels.get(player.getUniqueId());
		}
		else
		{
			Set<Channel> c = new HashSet<Channel>();
			channels.put(player.getUniqueId(), c);
			return channels.get(player.getUniqueId());
		}
	}

	@Override
	public void setPlayerAfk(Player player, boolean isafk)
	{
		assert(player != null) : "Player can't be null.";

		if (isafk)
		{
			afk.add(player.getUniqueId());
		}
		else
		{
			afk.remove(player.getUniqueId());
		}
	}

	/**
	 * Mute or unmute an player (in-an-selected-channel)
	 *
	 * @param player The player to be muted. (Only UUID is used)
	 * @param mute <code>true</code> for mute, and <code>false</code> to unmute
	 * @param channel An instance of channel to player be muted.
	 */
	@Override
	public void mutePlayer(Player player, Channel channel, boolean mute)
	{
		assert(player != null) : "Player can't be null.";
		assert(channel != null) : "Channel can't be null.";
		Collection<Channel> c = new HashSet<Channel>();
		if (mutes.containsKey(player.getUniqueId()))
		{
			c = mutes.get(player.getUniqueId());
		}

		if (mute)
		{
			c.add(channel);
		}
		else
		{
			c.remove(channel);
		}

		mutes.put(player.getUniqueId(), c);
	}

	/**
	 * Mute or unmute an player (in-all-channels)
	 *
	 * @param player The player to be muted. (Only UUID is used)
	 * @param mute <code>true</code> for mute, and <code>false</code> to unmute
	 */
	@Override
	public void mutePlayer(Player player, boolean mute)
	{
		assert(player != null) : "Player can't be null.";

		if (mute)
		{
			mutes.put(player.getUniqueId(), Channels.ALL_CHANNELS);
		}
		else
		{
			mutes.remove(player.getUniqueId());
		}
	}

	@Override
	public boolean isMuted(Player player, Channel channel)
	{
		assert(player != null) : "Player can't be null.";
		assert(channel != null) : "Channel can't be null.";
		if (!mutes.containsKey(player.getUniqueId()))
		{
			return false;
		}
		else
		{
			Collection<Channel> c = mutes.get(player.getUniqueId());
			return c.contains(channel);
		}
	}

	@Override
	public Collection<Entry<Channel, Boolean>> isMuted(Player player, Collection<Channel> channels)
	{
		assert(player != null) : "Player can't be null.";
		assert(channels != null) : "The channel's collection can't be null";
		Map<Channel, Boolean> map = new HashMap<Channel, Boolean>();
		for (Channel c : channels)
		{
			boolean m = isMuted(player, c);
			map.put(c, m);
		}

		return map.entrySet();
	}

}
