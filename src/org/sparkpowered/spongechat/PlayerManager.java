/**
 * 	This file is part from Spongechat.
 *
 *  Spongechat — A new powered engine for server conversations.
 *  Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/> and your contributors;
 *  Copyright (C) 2015 contributors
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Nullable;

import org.sparkpowered.spongechat.channels.Channel;
import org.sparkpowered.spongechat.channels.Channels;
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

	private static Map<UUID, List<Channel>> channels = new HashMap<UUID, List<Channel>>();
	private static Map<UUID, List<Channel>> mutes = new HashMap<UUID, List<Channel>>();
	private static Map<UUID, Channel> focus = new HashMap<UUID, Channel>();
	private static Map<UUID, Integer> count = new HashMap<UUID, Integer>();
	private static Collection<UUID> afk = new HashSet<UUID>();

	protected PlayerManager()
	{
	}

	@Override
	public void setFocus(final Player player, final Channel channel)
	{
		assert(player != null) : "Player can't be null.";
		assert(channel != null) : "Channel can't be null.";
		focus.put(player.getUniqueId(), channel);
	}

	@Override
	public void setPlayerTotalMessages(final Player player, final Integer total)
	{
		assert(player != null) : "Player can't be null.";
		assert(total >= 0) : "Number can't be < 0";
		count.put(player.getUniqueId(), total);
	}

	@Nullable
	@Override
	public int getPlayerTotalMessages(final Player player)
	{
		assert(player != null) : "Player can't be null.";
		return (count.containsKey(player.getUniqueId()) ? count.get(player.getUniqueId()) : 0);
	}

	@Nullable
	@Override
	public Channel getFocusedChannel(final Player player)
	{
		assert(player != null) : "Player can't be null.";
		return focus.get(player.getUniqueId());
	}

	@Override
	public boolean isAfk(final Player player)
	{
		assert(player != null) : "Player can't be null.";
		return afk.contains(player.getUniqueId());
	}

	public boolean isAfk(final UUID uuid)
	{
		assert(uuid != null) : "UUID can't be null.";
		return afk.contains(uuid);
	}

	@Override
	public int activeChannels(final Player player)
	{
		assert(player != null) : "Player can't be null.";
		return (channels.containsKey(player.getUniqueId()) ? channels.get(player.getUniqueId()).size() : 0);
	}

	@Override
	public List<Channel> getActiveChannels(final Player player)
	{
		assert(player != null) : "Player can't be null.";

		if (channels.containsKey(player.getUniqueId()))
		{
			return channels.get(player.getUniqueId());
		}
		else
		{
			final List<Channel> c = new ArrayList<Channel>();
			channels.put(player.getUniqueId(), c);
			return channels.get(player.getUniqueId());
		}
	}

	@Override
	public void setPlayerAfk(final Player player, final boolean isafk)
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
	public void mutePlayer(final Player player, final Channel channel, final boolean mute)
	{
		assert(player != null) : "Player can't be null.";
		assert(channel != null) : "Channel can't be null.";
		List<Channel> c = new ArrayList<Channel>();
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
	public void mutePlayer(final Player player, final boolean mute)
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
	public boolean isMuted(final Player player, final Channel channel)
	{
		assert(player != null) : "Player can't be null.";
		assert(channel != null) : "Channel can't be null.";
		if (!mutes.containsKey(player.getUniqueId()))
		{
			return false;
		}
		else
		{
			final Collection<Channel> c = mutes.get(player.getUniqueId());
			return c.contains(channel);
		}
	}

	@Override
	public Collection<Entry<Channel, Boolean>> isMuted(final Player player, final Collection<Channel> channels)
	{
		assert(player != null) : "Player can't be null.";
		assert(channels != null) : "The channel's collection can't be null";
		final Map<Channel, Boolean> map = new HashMap<Channel, Boolean>();
		for (final Channel c : channels)
		{
			final boolean m = isMuted(player, c);
			map.put(c, m);
		}

		return map.entrySet();
	}

}
