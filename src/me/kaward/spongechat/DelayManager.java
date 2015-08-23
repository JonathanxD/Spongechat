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
package me.kaward.spongechat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.scheduler.Task;
import org.spongepowered.api.service.scheduler.TaskBuilder;

import me.kaward.spongechat.providers.IDelayManager;

/**
 *
 * <p>
 * This class handle and manage the delays of players/channels. It's used also for prevent flood on the chat.
 * </p>
 *
 */
public class DelayManager implements IDelayManager
{

	private static Map<Channel, Collection<UUID>> channels = new HashMap<Channel, Collection<UUID>>();

	@Override
	public strictfp Entry<Channel, Task> addDelay(final Player player, final Channel channel)
	{
		assert(player != null) : "Player can't be null.";
		assert(channel != null) : "Channel can't be null.";
		final TaskBuilder builder = SpongechatAPI.getProvider().getGame().getScheduler().createTaskBuilder();

		Runnable executor = new Runnable()
		{
			@Override
			public void run()
			{
			}
		};

		final Task task = builder.name("CD" + player.getUniqueId().toString() + channel.getChannelName()).async().delay(channel.getMessageDelay(), TimeUnit.SECONDS).execute(executor).submit(SpongechatAPI.getProvider());

		Entry<Channel, Task> set = new Entry<Channel, Task>()
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
			public Task setValue(Task arg0)
			{
				t = arg0;
				return arg0;
			}
		};

		return set;
	}

}
