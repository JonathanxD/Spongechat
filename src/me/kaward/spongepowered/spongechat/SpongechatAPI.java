package me.kaward.spongepowered.spongechat;


public class SpongechatAPI
{

	public static ChannelLoader getChannelLoader()
	{
		return new ChannelLoader();
	}

	public static ChannelManager getChannelManager()
	{
		return new ChannelManager();
	}

	public static PlayerManager getPlayerManager()
	{
		return new PlayerManager();
	}

}
