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
package org.sparkpowered.spongechat.pages;

import java.util.Collection;
import java.util.HashSet;

import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.messages.PublicMessage;
import org.sparkpowered.spongechat.providers.IChatPagination;
import org.sparkpowered.spongechat.utils.Locations;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.pagination.PaginationBuilder;
import org.spongepowered.api.service.pagination.PaginationService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.world.World;

/**
 *
 * <p>
 * This class handle the Chat Pagination system, creating information's page, and more.
 * </p>
 *
 * @see org.sparkpowered.spongechat.providers.IChatPagination The ChatPagination Interface
 * @category Pagination Services
 *
 */
public class ChatPagination implements IChatPagination
{

	public ChatPagination()
	{
	}

	@Override
	public void createInfo(final Player sendTo, final PublicMessage message)
	{
		final PaginationService ps = SpongechatAPI.getProvider().getGame().getServiceManager().provide(PaginationService.class).get();
		final PaginationBuilder pb = ps.builder();
		final Collection<String> plain = new HashSet<String>();
		final Collection<Text> source = new HashSet<Text>();

		plain.add(" ");
		plain.add("§lMessage by: §0" + message.getSender());
		plain.add("§lMessage Id: §0" + message.getIdentification().toString());
		plain.add("§lChannel: §0" + message.getChannel().getName());
		plain.add("§lLocation: §0" + Locations.<World> serialize(message.getSendLocation()));
		plain.add(" ");

		for (final String s : plain)
		{
			source.add(Texts.of(s));
		}

		pb.contents(source).title(Texts.of("§lMESSAGE INFORMATION§r")).header(Texts.of("")).footer(Texts.of(message.getSentDate())).paddingString("#").sendTo(sendTo);
	}

}
