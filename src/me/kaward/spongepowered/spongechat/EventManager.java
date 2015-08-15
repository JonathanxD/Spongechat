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
package me.kaward.spongepowered.spongechat;

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
		SpongechatAPI.getPlayerManager().setFocus(player, Channel.DEFAULT_CHANNEL);
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
