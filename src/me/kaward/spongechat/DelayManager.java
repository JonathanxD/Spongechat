package me.kaward.spongechat;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.scheduler.Task;
import org.spongepowered.api.service.scheduler.TaskBuilder;

import me.kaward.spongechat.providers.IDelayManager;

public class DelayManager implements IDelayManager
{

	private static Collection<UUID> active = new HashSet<UUID>();

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

		final Task task = builder.name("CD" + player.getUniqueId().toString() + channel.getChannelName()).async().delay(channel.getChannelMessageDelay(), TimeUnit.SECONDS).execute(executor).submit(SpongechatAPI.getProvider());

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
