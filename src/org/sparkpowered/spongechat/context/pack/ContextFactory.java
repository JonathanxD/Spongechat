/**
 * 	This file is part from Spongechat.
 *
 *  Spongechat — A new powered engine for server conversations.
 *  Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/> and your contributors;
 *  Copyright (C) 2015 contributors
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sparkpowered.spongechat.context.pack;

import java.util.UUID;

import org.sparkpowered.spongechat.Spongechat;
import org.sparkpowered.spongechat.context.Context;

public class ContextFactory
{

	@SuppressWarnings("unused")
	public static void setContext(final UUID player, final Object context)
	{
		final Object[] reader = (Object[]) context;
		final String name = String.valueOf(reader[0]);
		final String simp = String.valueOf(reader[1]);
		final Integer id = Integer.valueOf(String.valueOf(reader[2]));

		if (context == Context.CHANNEL_CREATED_CONTEXT)
		{
			final ChannelCreateContext ccc = new ChannelCreateContext(player);
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
