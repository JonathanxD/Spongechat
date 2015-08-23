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

import java.util.Map.Entry;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.scheduler.Task;

import me.kaward.spongechat.Channel;

/**
 * <p>
 * This class contains the interface of provider of DelayManager.
 * </p>
 *
 * @category Delay Handlering
 * @see me.kaward.spongechat.DelayManager Delay Handlering
 *
 */
public interface IDelayManager
{

	/**
	 * <p>
	 * Creates a new delay for a player and disallow player to send messages into the channel when delay nots ends.
	 * </p>
	 *
	 * @param player The player to be affected.
	 * @param channel The channel to be added the delay.
	 * @return Returns an Entry containing Channel and Task.
	 */
	public Entry<Channel, Task> addDelay(Player player, Channel channel);

}
