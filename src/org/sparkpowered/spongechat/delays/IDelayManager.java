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
package org.sparkpowered.spongechat.delays;

import java.util.Map.Entry;

import org.sparkpowered.spongechat.channels.Channel;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.scheduler.Task;

/**
 * <p>
 * Esta interface contém o sistema de gerenciamento de delays.
 * </p>
 *
 * @category Delay Handlering
 * @see org.sparkpowered.spongechat.delays.DelayManager Delay Handlering
 *
 */
public interface IDelayManager
{

	/**
	 * <p>
	 * Cria um novo delay para um {@link org.spongepowered.api.entity.player.Player} em um {@link org.sparkpowered.spongechat.channels.Channel}. Isto é também
	 * usado para o sistema AntiFlood, o {@link org.spongepowered.api.entity.player.Player} não poderá mandar mensagens até que o delay acabe.
	 * </p>
	 *
	 * @param player O {@link org.spongepowered.api.entity.player.Player} que será afetado.
	 * @param channel O {@link org.sparkpowered.spongechat.channels.Channel} no qual será adicionado o delay para o {@link org.spongepowered.api.entity.player.Player}.
	 * @return Retorna um {@link java.util.Map.Entry} contendo Channel e a Task criada.
	 */
	public Entry<Player, Entry<Channel, Task>> addDelay(org.spongepowered.api.entity.player.Player player, Channel channel);

	/**
	 * <p>
	 * Remove o delay de um {@link org.spongepowered.api.entity.player.Player} em um {@link org.sparkpowered.spongechat.channels.Channel}
	 * </p>
	 *
	 * @param player The {@link org.spongepowered.api.entity.player.Player} to be affected.
	 * @param channel The {@link org.sparkpowered.spongechat.channels.Channel} to be affected.
	 * @return Retorna <b>true</b> se tiver sido removido e <b>false</b> caso contrário.
	 */
	public boolean truncateDelay(Player player, Channel channel);

}
