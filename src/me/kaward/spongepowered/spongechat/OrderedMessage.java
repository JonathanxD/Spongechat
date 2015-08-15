package me.kaward.spongepowered.spongechat;

import java.util.UUID;

public class OrderedMessage
{

	@SuppressWarnings("unused")
	private UUID random = UUID.randomUUID();
	private UUID player = null;
	private Message message = null;

	public OrderedMessage(UUID player, Message message)
	{
		this.player = player;
		this.message = message;
	}

	public UUID getPlayer()
	{
		return player;
	}

	public Message getMessage()
	{
		return message;
	}

}
