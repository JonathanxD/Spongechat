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
