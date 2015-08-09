package me.kaward.spongepowered.spongechat.events;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import me.kaward.spongepowered.spongechat.Message;
import me.kaward.spongepowered.spongechat.Spongechannel;
import me.kaward.spongepowered.spongechat.Spongechat;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.AbstractEvent;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.text.Text;

public class ChannelMessageEvent extends AbstractEvent implements Cancellable
{

	private Set<UUID> receivers = new HashSet<UUID>();
	private UUID playerUniqueId = null;
	private Spongechannel channel = null;
	private Text text = null;
	private Message message = null;
	private Boolean cancelled = false;

	public ChannelMessageEvent(UUID playerUniqueId, Spongechannel channel, Message message, Text text, Set<UUID> receivers)
	{
		this.receivers = receivers;
		this.playerUniqueId = playerUniqueId;
		this.channel = channel;
		this.text = text;
		this.message = message;
	}

	public Set<UUID> getReceivers()
	{
		return receivers;
	}

	public void setText(Text text)
	{
		this.text = text;
	}

	public Text getText()
	{
		return text;
	}

	public Player getPlayer()
	{
		return (Player) Spongechat.sponge.getServiceManager().provide(Spongechat.class).get().getGame().getServer().getPlayer(getPlayerUniqueId());
	}

	public Message getMessage()
	{
		return message;
	}

	public UUID getPlayerUniqueId()
	{
		return playerUniqueId;
	}

	public Spongechannel getChannel()
	{
		return channel;
	}

	@Override
	public boolean isCancelled()
	{
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}

}
