/**
 * 	Spongechat, a new powered chat system for SpongePowered Minecraft API.
 * 	Copyright (C) 2015 Kaward <https://github.com/Kaward/>
 * 	Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/>
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sparkpowered.spongechat.delays;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.channels.Channel;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.scheduler.Task;
import org.spongepowered.api.service.scheduler.TaskBuilder;

import com.google.common.base.Preconditions;

/**
 *
 * <p>
 * This class handle and manage the delays of players/channels. It's used also for prevent flood on the chat.
 * </p>
 *
 */
public class DelayManager implements IDelayManager
{

	@Nonnull
	private static Map<Channel, Collection<UUID>> channels = new HashMap<Channel, Collection<UUID>>();

	@Nonnull
	private static Collection<Entry<Player, Entry<Channel, Task>>> set = new HashSet<Entry<Player, Entry<Channel, Task>>>();

	@Override
	public Entry<Player, Entry<Channel, Task>> addDelay(@Nonnull final Player player, @Nonnull final Channel channel)
	{
		Preconditions.checkNotNull(player, "Player can't be null.");
		Preconditions.checkNotNull(channel, "Channel can't be null.");
		final TaskBuilder builder = SpongechatAPI.getProvider().getGame().getScheduler().createTaskBuilder();

		final Runnable executor = new Runnable()
		{
			@Override
			public void run()
			{
				truncateDelay(player, channel);
			}
		};

		final Task task = builder.name("CD" + player.getUniqueId().toString() + channel.getName()).async().delay(channel.getDelay(), TimeUnit.SECONDS).execute(executor).submit(SpongechatAPI.getProvider());

		final Entry<Channel, Task> entryset = new Entry<Channel, Task>()
		{
			private Task t = task;

			@Override
			public Channel getKey()
			{
				return channel;
			}

			@Override
			public Task getValue()
			{
				return t;
			}

			@Override
			public Task setValue(final Task arg0)
			{
				t = arg0;
				return arg0;
			}
		};

		final Entry<Player, Entry<Channel, Task>> result = new Entry<Player, Entry<Channel, Task>>()
		{

			private Entry<Channel, Task> set0 = entryset;

			@Override
			public Player getKey()
			{
				return player;
			}

			@Override
			public Entry<Channel, Task> getValue()
			{
				return set0;
			}

			@Override
			public Entry<Channel, Task> setValue(final Entry<Channel, Task> arg0)
			{
				set0 = arg0;
				return set0;
			}

		};

		final boolean cons = channels.containsKey(channel);
		final Collection<UUID> players = (cons ? channels.get(channel) : new HashSet<UUID>());
		players.add(player.getUniqueId());
		channels.put(channel, players);
		return result;
	}

	@Override
	public boolean truncateDelay(@Nonnull final Player player, @Nonnull final Channel channel)
	{
		Preconditions.checkNotNull(player, "Player can't be null.");
		Preconditions.checkNotNull(channel, "Channel can't be null.");

		final boolean cons = channels.containsKey(channel);
		final Collection<UUID> players = (cons ? channels.get(channel) : new HashSet<UUID>());

		if (players.contains(player.getUniqueId()))
		{
			players.remove(player.getUniqueId());
			channels.put(channel, players);
			return true;
		}

		return false;
	}

}
