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
package org.sparkpowered.spongechat;

import java.util.ArrayList;
import java.util.List;

import org.sparkpowered.spongechat.channels.Channel;
import org.sparkpowered.spongechat.channels.ChannelManager;
import org.sparkpowered.spongechat.channels.Channels;
import org.sparkpowered.spongechat.delays.DelayManager;
import org.sparkpowered.spongechat.pages.ChatPagination;
import org.sparkpowered.spongechat.providers.IChatPagination;

/**
 * This class provides access for all API's existents on the Spongechat. Esta classe provém acesso a todas as API's existentes no Spongechat.
 *
 * @author Pitter Thog (Kaward) <https://github.com/Kaward/>
 * @category API for Developers
 *
 */
public class SpongechatAPI
{

	private static final List<Channel> channels = new ArrayList<Channel>();

	/**
	 * <p>
	 * Esta lista foi criada mais pelo controle interno, mas você poderá usá-la normalmente para verificar quais canais existem.
	 * </p>
	 *
	 * @return Retorna o List de canais existentes.
	 */
	public static List<Channel> getChannels()
	{
		return channels;
	}

	/**
	 * <p>
	 * Com essa instância você tem acesso à classe principal do Spongechat.
	 * </p>
	 *
	 * @return Retorna o provedor da classe principal do Spongechat.
	 */
	public static Spongechat getProvider()
	{
		return Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
	}

	/**
	 * <p>
	 * O Core de Canais permite que você acesse informações e dados internos de canais, e deve ser usado com muito cuidado para não causar danos.
	 * </p>
	 *
	 * @return Retorna o Core de canais.
	 */
	public static Channels getChannelCore()
	{
		return new Channels();
	}

	/**
	 * <p>
	 * O Core de Paginação pode ser usado para criar books contendo informações sobre mensagens, canais, etc.
	 * </p>
	 *
	 * @return Retorna o Core de paginação.
	 */
	public static IChatPagination getPaginationCore()
	{
		return new ChatPagination();
	}

	/**
	 * <p>
	 * A API de gerenciamento de canais permite que você mute canais, crie novos canais, remova, edite e obtenha informações, além de contar com API's de edição direta.
	 * </p>
	 *
	 * @return Retorna a API de gerenciamento e controle dos canais de conversa.
	 */
	public static ChannelManager getChannelManager()
	{
		return new ChannelManager();
	}

	/**
	 * <p>
	 * A API de gerenciamento de jogadores permite que você controle em quais canais ele está ativo, qual ele está focado, gerenciar os delays que ele tem, etc.
	 * </p>
	 *
	 * @return Retorna a API de gerenciamento e controle de jogadores e seus dados.
	 */
	public static PlayerManager getPlayerManager()
	{
		return new PlayerManager();
	}

	public static DelayManager getDelayManager()
	{
		return new DelayManager();
	}

}
