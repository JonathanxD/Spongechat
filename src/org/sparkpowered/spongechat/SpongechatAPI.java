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
package org.sparkpowered.spongechat;

import java.util.Collection;
import java.util.HashSet;

import org.sparkpowered.spongechat.channels.Channel;
import org.sparkpowered.spongechat.channels.ChannelManager;
import org.sparkpowered.spongechat.channels.Channels;

/**
 * This class provides access for all API's existents on the Spongechat. Esta classe provém acesso a todas as API's existentes no Spongechat.
 *
 * @author Pitter Thog (Kaward) <https://github.com/Kaward/>
 * @category API for Developers
 *
 */
public class SpongechatAPI
{

	private static final Collection<Channel> channels = new HashSet<Channel>();

	public static Collection<Channel> getChannels()
	{
		return channels;
	}

	public static Channels getInternalChannelAPI()
	{
		return new Channels();
	}

	/**
	 * Retorna a instância da classe "Spongechat".
	 *
	 * @return Returns the "Spongechat" instance class.
	 */
	public static Spongechat getProvider()
	{
		return Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
	}

	/**
	 * Retorna a API para gerenciamento geral de canais.
	 *
	 * @return Returns the API for geral management of channels.
	 */
	public static ChannelManager getChannelManager()
	{
		return new ChannelManager();
	}

	/**
	 * Retorna a API para gerenciamento dos jogadores.
	 *
	 * @return Returns the API for management of players.
	 */
	public static PlayerManager getPlayerManager()
	{
		return new PlayerManager();
	}

}
