package me.kaward.spongepowered.spongechat.context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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

	@SuppressWarnings("unchecked")
	public static <Handler> ContextHandler<Handler> getSource(UUID player)
	{
		return (ContextHandler<Handler>) handlerList.get(player);
	}

}
