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
package org.sparkpowered.spongechat.providers;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.sparkpowered.spongechat.channels.Channel;
import org.sparkpowered.spongechat.logs.ChannelLogs;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.util.command.spec.CommandSpec;

/**
 *
 * <p>
 * This class provides the interface of public api for channel management.
 * </p>
 *
 * @see org.sparkpowered.spongechat.channels.ChannelManager
 * @category Channel Handlering
 *
 */
public interface IChannelManager
{

	public Entry<Channel, CommandSpec> getChannel(String channelsName, boolean ignoreCase);

	/**
	 * <p>
	 * Delete and remove a channel
	 * </p>
	 *
	 * @param channel The channel to be deleted/removed.
	 */
	public void delete(Channel channel);

	/**
	 * <p>
	 * Mute or unmute an channel.
	 * </p>
	 *
	 * @param channel The channel to be muted or unmuted.
	 * @param muted <b>true</b> to mute the channel and <b>false</b> to unmute.
	 */
	public void setMuted(Channel channel, boolean muted);

	/**
	 * <p>
	 * Mute or unmute a collection of channels.
	 * </p>
	 *
	 * @param channel The collection of channels to be muted or unmuted.
	 * @param muted <b>true</b> to mute the channels and <b>false</b> to unmute then.
	 */
	public void setMuted(Collection<Channel> channels, boolean muted);

	/**
	 * <p>
	 * Register a command for an channel with personal properties.
	 * </p>
	 *
	 * @param channel The channel to be affected
	 * @param command The CommandSpec to be used in the command
	 * @param names The names of command (e.g 'g' to '/g')
	 */
	public void registerCommand(Channel channel, CommandSpec command, String... names);

	/**
	 * <p>
	 * Register a command for an channel with default properties.
	 * </p>
	 *
	 * @param channel The channel to be affected
	 */
	public void registerCommand(Channel channel);

	/**
	 * <p>
	 * Clear the chat, removing all visible messages.
	 * </p>
	 */
	public void clear();

	/**
	 * <p>
	 * Clear the chat, removing all visible messages, for an expected player.
	 * </p>
	 *
	 * @param player The player to be affected.
	 */
	public void clear(Player player);

	/**
	 * <p>
	 * Verify if an channel is muted.
	 * </p>
	 *
	 * @param channel Channel to be verified.
	 * @return <b>true</b> if channel is muted, and <b>false</b> if not.
	 */
	public boolean isMuted(Channel channel);

	/**
	 * <p>
	 * Get the available logs of the channel.
	 * </p>
	 *
	 * @param channel The channel to be useds
	 * @return Returns logs of channel.
	 */
	public ChannelLogs getLogs(Channel channel);

	/**
	 * Verify if a collection of channels is muted or not.
	 *
	 * @param channels The collection of channels to be checked
	 * @return Returns an Collection of Entry<Channel, Boolean>.
	 * @see java.util.Collection Collection
	 * @see java.util.Entry Entry
	 */
	public Collection<Map.Entry<Channel, Boolean>> isMuted(Collection<Channel> channels);

}
