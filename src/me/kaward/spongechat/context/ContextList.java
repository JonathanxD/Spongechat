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
package me.kaward.spongechat.context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.spongepowered.api.entity.player.Player;

public class ContextList
{

	protected static Set<UUID> sources = new HashSet<UUID>();
	protected static Map<UUID, ContextHandler<?>> handlerList = new HashMap<UUID, ContextHandler<?>>();

	public static Map<UUID, ContextHandler<?>> getSources()
	{
		return handlerList;
	}

	public static Set<UUID> getActive()
	{
		return sources;
	}

	public static boolean cand(Player player)
	{
		UUID uid = player.getUniqueId();
		return (getSources().containsKey(uid) == false) && (getActive().contains(uid) == false);
	}

	@SuppressWarnings("unchecked")
	public static <Handler> ContextHandler<Handler> getSource(UUID player)
	{
		return (ContextHandler<Handler>) handlerList.get(player);
	}

}
