package me.kaward.spongepowered.spongechat.context.pack;

import java.util.UUID;

public class ContextFactory
{

	public static void setContext(UUID player, Object context)
	{
		Object[] reader = (Object[]) context;
		String name = String.valueOf(reader[0]);
		String simp = String.valueOf(reader[1]);
		Integer id = Integer.valueOf(String.valueOf(reader[2]));
	}

}
