package me.kaward.spongechat.providers;

import java.util.Map.Entry;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.scheduler.Task;

import me.kaward.spongechat.Channel;

public interface IDelayManager
{

	public Entry<Channel, Task> addDelay(Player player, Channel channel);

}
