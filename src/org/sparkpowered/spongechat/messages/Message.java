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
package org.sparkpowered.spongechat.messages;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.sparkpowered.spongechat.Locations;
import org.sparkpowered.spongechat.Spongechat;
import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.channels.Channel;
import org.sparkpowered.spongechat.events.ChannelMessageEvent;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.world.Location;

/**
 *
 * @author Pitter Thog (Kaward) <https://github.com/Kaward/>
 * @category Message Management and Service
 *
 */
@SuppressWarnings("unused")
public class Message
{

	private static final long serialVersionUID = -2817680756335125068L;
	private final Set<UUID> receivers = new HashSet<UUID>();
	private final UUID messageId = UUID.randomUUID();
	private UUID sentBy = null;
	private Channel channel = null;
	private String unformattedMessage = "";
	private Text text = null;
	private String timeFormat = "";
	private Location<?> location = null;

	public Message(final Player sentBy, final Channel channel, final String message)
	{
		this.sentBy = sentBy.getUniqueId();
		this.channel = channel;
		unformattedMessage = message;
		location = sentBy.getLocation();
	}

	public Message(final Player sentBy, final Channel channel, final Text text)
	{
		this.sentBy = sentBy.getUniqueId();
		this.channel = channel;
		unformattedMessage = Texts.toPlain(text).replace("§", "&");
	}

	/**
	 * [ENGLISE] This is used to send message by the Internal System. Don't use it, because it's don't call the Spongechat Custom Event, and can cause
	 * errors. To send an message, use <code>SpongechatAPI.sendMessage(UUID player, Message instance)</code>
	 *
	 * [PORTUGUESE] Isto é usado para enviar a mensagem pelo Serviço Interno. Não utilize isso, porque isso não chama o Evento Customizado do
	 * Spongechat, e consequentemente, pode causar erros severos. Para enviar uma mensagem, use
	 * <code>SpongechatAPI.sendMessage(UUID player, Message instancia)</code>
	 */
	@SuppressWarnings("deprecation")
	public void send()
	{
		final Spongechat sponge = Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
		final Player sender = sponge.getGame().getServer().getPlayer(sentBy).get();
		final Set<UUID> receivers = new HashSet<UUID>();
		for (final Player p : sponge.getGame().getServer().getOnlinePlayers())
		{
			if (p.getUniqueId().equals(sender.getUniqueId()) == false)
			{
				if (receivers.contains(p.getUniqueId()) == false)
				{
					if (!getChannel().isCrossworld() && Locations.distance(sender.getLocation(), p.getLocation()) <= channel.getDistance())
					{
						receivers.add(p.getUniqueId());
						continue;
					}
					else
					{
						receivers.add(p.getUniqueId());
						continue;
					}
				}
			}
		}

		for (final UUID unique : getReceivers())
		{
			final Player p = sponge.getGame().getServer().getPlayer(unique).get();
			// p.sendMessage(Texts.of(Texts.replaceCodes(format, '&')).builder().build());
			setText(text);
		}
	}

	public ChannelMessageEvent call()
	{
		final ChannelMessageEvent event = new ChannelMessageEvent(getSender(), getChannel(), this, getText(), getReceivers());
		SpongechatAPI.getProvider().getGame().getEventManager().post(event);
		return event;
	}

	public Location<?> getLocation()
	{
		return location;
	}

	public String getLocationText()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("World (" + location.getExtent() + "), ");
		sb.append("X (" + location.getBlockX() + "), ");
		sb.append("Y (" + location.getBlockY() + "), ");
		sb.append("Z (" + location.getBlockZ() + ")");
		return sb.toString();
	}

	/**
	 * [ENGLISH] This void allows you to edit the Formatted Text of message.
	 *
	 * [PORTUGUESE] Este void permite você editar o Texto Formatado da mensagme.
	 *
	 * @param text [ENGLISH] Text to set
	 * @param text [PORTUGUESE] Texto a ser setado
	 */
	public void setText(final Text text)
	{
		this.text = text;
	}

	/**
	 * @return Text [ENGLISH] Returns the 'Text' of message.
	 * @return Text [PORTGUESE] Returns the 'Text' of message.
	 */
	public Text getText()
	{
		return text;
	}

	/**
	 *
	 * @return [PORTUGUESE] Retorna uma lista de jogadores que irão receber a mensagem.
	 */
	public Set<UUID> getReceivers()
	{
		return receivers;
	}

	/**
	 *
	 * @return [ENGLISH] Returns the Universal Unique Identification of que message. It can be used to access message logger and get information of
	 *         this message.
	 * @return [PORTUGUESE] Retorna a Identificação Universal da mensagem. Isto pode ser utilizado para acessar o Logger de mensagens e obter
	 *         informação desta mensagem.
	 */
	public UUID getMessageId()
	{
		return messageId;
	}

	/**
	 *
	 * @return [ENGLISH] Returns the UUID of sender (player) of message.
	 * @return [PORTUGUESE] Retorna a UUID de quem enviou a mensagem.
	 */
	public UUID getSender()
	{
		return sentBy;
	}

	/**
	 * [ENGLISH] Modify the sender of message.
	 *
	 * [PORTUGUESE] Modificar o enviador da mensagem.
	 *
	 * @param sentBy [ENGLISH] The UUID of new sender of message.
	 * @param sentBy [PORTUGUESE] O UUID do novo enviador da mensagem.
	 */
	public void setSender(final UUID sentBy)
	{
		this.sentBy = sentBy;
	}

	/**
	 *
	 * @return [ENGLISH] Returns the Channel of message.
	 * @return [PORTUGUESE] Retorna o Canal da mensagem.
	 */
	public Channel getChannel()
	{
		return channel;
	}

	/**
	 * [ENGLISH] Modify the channel of message be sended.
	 *
	 * [PORTUGUESE] Modifica o canal que a mensagem será enviada.
	 *
	 * @param channel [ENGLISH] The new channel to be send.
	 * @param channel [PORTUGUESE] O novo canal no qual será enviada.
	 */
	public void setChannel(final Channel channel)
	{
		this.channel = channel;
	}

	public String getUnformattedMessage()
	{
		return unformattedMessage;
	}

	public void setUnformattedMessage(final String unformattedMessage)
	{
		this.unformattedMessage = unformattedMessage;
	}

	public String getTimeFormat()
	{
		return timeFormat;
	}

	public void setTimeFormat(final String timeFormat)
	{
		this.timeFormat = timeFormat;
	}

}
