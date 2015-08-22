package me.kaward.spongechat.providers;

import org.spongepowered.api.entity.player.Player;

import me.kaward.spongechat.Message;

public interface IChatPagination
{

	public void createInfo(Player player, Message message);

}
