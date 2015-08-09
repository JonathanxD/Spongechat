package me.kaward.spongepowered.spongechat;

import me.kaward.spongepowered.spongechat.options.DefaultChannel;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerChatEvent;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;
import org.spongepowered.api.text.Text;

public class EventManager
{

	@Subscribe(order = Order.LAST)
	public void handle(PlayerJoinEvent event)
	{
		Player player = event.getEntity();

		if (ChannelOptions.DEFAULT_CHANNEL == null)
		{
			ChannelOptions.DEFAULT_CHANNEL = DefaultChannel.build();
		}

		SpongechatAPI.getPlayerManager().setFocus(player, ChannelOptions.DEFAULT_CHANNEL);
	}

	@Subscribe(order = Order.LAST)
	@SuppressWarnings("unused")
	public void handle(PlayerChatEvent event)
	{
		Player player = event.getEntity();
		Text text = event.getMessage();

		Message message = new Message(player, SpongechatAPI.getPlayerManager().getFocusedChannel(player), text);
	}

}
