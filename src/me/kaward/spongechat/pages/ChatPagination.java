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
package me.kaward.spongechat.pages;

import java.util.Collection;
import java.util.HashSet;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.pagination.PaginationBuilder;
import org.spongepowered.api.service.pagination.PaginationService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;

import me.kaward.spongechat.Message;
import me.kaward.spongechat.SpongechatAPI;
import me.kaward.spongechat.providers.IChatPagination;

/**
 *
 * <p>
 * This class handle the Chat Pagination system, creating information's page, and more.
 * </p>
 *
 * @see me.kaward.spongechat.providers.IChatPagination The ChatPagination Interface
 * @category Pagination Services
 *
 */
public class ChatPagination implements IChatPagination
{

	protected ChatPagination()
	{
	}

	@Override
	public void createInfo(Player sendTo, Message message)
	{
		PaginationService ps = SpongechatAPI.getProvider().getGame().getServiceManager().provide(PaginationService.class).get();
		PaginationBuilder pb = ps.builder();
		Collection<String> plain = new HashSet<String>();
		Collection<Text> source = new HashSet<Text>();

		plain.add(" ");
		plain.add("§lMessage by: §0" + message.getSender());
		plain.add("§lMessage Id: §0" + message.getMessageId().toString());
		plain.add("§lChannel: §0" + message.getChannel().getChannelName());
		plain.add("§lLocation: §0" + message.getLocationText());
		plain.add(" ");

		for (String s : plain)
		{
			source.add(Texts.of(s));
		}

		pb.contents(source).title(Texts.of("§lMESSAGE INFORMATION§r")).header(Texts.of("")).footer(Texts.of(message.getTimeFormat())).paddingString("#").sendTo(sendTo);
	}

}
