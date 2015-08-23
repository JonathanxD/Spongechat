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
package me.kaward.spongechat.providers;

import java.util.Collection;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import org.spongepowered.api.entity.player.Player;

import me.kaward.spongechat.Channel;

/**
 *
 * <p>
 * This class contains the interface of the provider of the Player Manager.
 * </p>
 *
 * @category Player Handlering
 * @see me.kaward.spongechat.PlayerManager Player Handlering
 *
 */
public interface IPlayerManager
{

	/**
	 * <p>
	 * Get active channels of player
	 * </p>
	 *
	 * @param player The player to be checked
	 * @return the list of active channels of player has
	 */
	public Collection<Channel> getActiveChannels(Player player);

	/**
	 * <p>
	 * Mute or unmute a player.
	 * </p>
	 *
	 * @param player The player to be affected.
	 * @param channel The channel to be used.
	 * @param mute <b>true </b> to mute and <b>false</b> to unmute.
	 */
	public void mutePlayer(Player player, Channel channel, boolean mute);

	/**
	 * <p>
	 * Mute or unmute the player in all channels.
	 * </p>
	 *
	 * @param player The player to be affected.
	 * @param mute <b>true</b> to mute and <b>false</b> to unmute.
	 */
	public void mutePlayer(Player player, boolean mute);

	/**
	 * <p>
	 * Set the focused channel of an player.
	 * </p>
	 *
	 * @param player The player to be affected.
	 * @param channel The channel to be focused.
	 */
	public void setFocus(Player player, Channel channel);

	/**
	 * <p>
	 * Set the player's total messages count.
	 * </p>
	 *
	 * @param player The player to be affected.
	 * @param total The new value
	 */
	public void setPlayerTotalMessages(Player player, Integer total);

	/**
	 * <p>
	 * Change the player's afk status.
	 * </p>
	 *
	 * @param player The player to be affected.
	 * @param afk <b>true</b> to set as afk and <b>false</b> to set as active.
	 */
	public void setPlayerAfk(Player player, boolean afk);

	/**
	 * <p>
	 * Get the player afk status.
	 * </p>
	 *
	 * @param player The player to be checked the afk status.
	 * @return <b>true</b> if afk and <b>false</b> if not.
	 */
	public boolean isAfk(Player player);

	/**
	 * <p>
	 * Check if the player is muted in a channel.
	 * <p>
	 *
	 * @param player The player to be checked if then is muted.
	 * @param channel The channel to be used for checkment.
	 * @return <b>true</b> if then is muted in the channel and <b>false</b> if not.
	 */
	public boolean isMuted(Player player, Channel channel);

	/**
	 * <p>
	 * Get mute status in various channels.
	 * </p>
	 *
	 * @param player The player to be used to check if then is muted or not.
	 * @param channels A collection of channels to be checked.
	 * @return Returns a Collection of Entry<Channel, Boolean>, containing results of mutes.
	 */
	public Collection<Entry<Channel, Boolean>> isMuted(Player player, Collection<Channel> channels);

	/**
	 * <p>
	 * Get the number of player's active channels.
	 * </p>
	 *
	 * @param player Player to be used to checkment.
	 * @return Returns the number of player's active channels.
	 */
	public int activeChannels(Player player);

	/**
	 * <p>
	 * Get the total messages of player who sended successfully.
	 * </p>
	 *
	 * @param player Player to be used to checkment.
	 * @return the total messages of player who sended with success.
	 */
	@Nullable
	public int getPlayerTotalMessages(Player player);

	/**
	 * <p>
	 * Get the actually focused channel of a player.
	 * </p>
	 * 
	 * @param player The player to be checked.
	 * @return Returns the player's focused channel.
	 */
	@Nullable
	public Channel getFocusedChannel(Player player);

}
