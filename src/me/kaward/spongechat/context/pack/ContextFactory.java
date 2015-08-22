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
package me.kaward.spongechat.context.pack;

import java.util.UUID;

import me.kaward.spongechat.Spongechat;
import me.kaward.spongechat.context.Context;

public class ContextFactory
{

	@SuppressWarnings("unused")
	public static void setContext(UUID player, Object context)
	{
		Object[] reader = (Object[]) context;
		String name = String.valueOf(reader[0]);
		String simp = String.valueOf(reader[1]);
		Integer id = Integer.valueOf(String.valueOf(reader[2]));

		if (context == Context.CHANNEL_CREATED_CONTEXT)
		{
			ChannelCreateContext ccc = new ChannelCreateContext(player);
			getSpongechat().getGame().getEventManager().register(getSpongechat(), ccc);
			ccc.next();
		}
	}

	/**
	 * Temporary method to get the Main Class, in future, its placed into the class SpongechatAPI
	 *
	 * @return The Spongechat class from provider
	 */
	protected static Spongechat getSpongechat()
	{
		return Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
	}

}
