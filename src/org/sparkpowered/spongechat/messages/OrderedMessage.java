package org.sparkpowered.spongechat.messages;

import java.util.UUID;

import org.sparkpowered.spongechat.events.ChannelMessageEvent;

/**
 * [ENGLISH] This class is used to execute messages in a correct sended order
 *
 * [PORTUGUESE] Esta classe é usada para executar as mensagens na ordem que correta que foram enviadas
 *
 * @author Pitter Thog (Kaward) <https://github.com/Kaward/>
 * @category Internal Services
 *
 */
public class OrderedMessage
{

	@SuppressWarnings("unused")
	private UUID random = UUID.randomUUID();
	private UUID player = null;
	private ChannelMessageEvent event = null;

	public OrderedMessage(UUID player, ChannelMessageEvent event)
	{
		this.player = player;
		this.event = event;
	}

	public UUID getPlayer()
	{
		return player;
	}

	public ChannelMessageEvent getEvent()
	{
		return event;
	}

}
