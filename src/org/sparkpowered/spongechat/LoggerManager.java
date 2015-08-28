package org.sparkpowered.spongechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import org.sparkpowered.spongechat.logs.LogData;
import org.sparkpowered.spongechat.logs.LogItem;
import org.sparkpowered.spongechat.providers.ILoggerManager;

public class LoggerManager implements ILoggerManager
{

	private HashMap<String, List<UUID>> center = new HashMap<String, List<UUID>>();
	private HashMap<UUID, LogItem> items = new HashMap<UUID, LogItem>();

	protected LoggerManager()
	{
	}

	@Override
	@Nullable
	public LogItem item(String group, UUID uuid)
	{
		for (Map.Entry<UUID, LogItem> item : items.entrySet())
		{
			if (item.getKey().equals(uuid))
			{
				return item.getValue();
			}
		}

		SpongechatAPI.getProvider().getLogger().severe("Can't returns the log item (id don't match)!");
		return null;
	}

	@Override
	public LogItem add(final String group, final LogData... data)
	{
		final UUID random = UUID.randomUUID();
		LogItem logData = new LogItem()
		{
			@Override
			public UUID id()
			{
				return random;
			}

			@Override
			@Nullable
			@SuppressWarnings("unchecked")
			public <T> T get(String name)
			{
				for (LogData c : data())
				{
					if (c.getName().equals(name))
					{
						return (T) c.getData();
					}
				}

				return null;
			}

			@Override
			public List<LogData> data()
			{
				List<LogData> data0 = new ArrayList<LogData>();
				for (LogData logdata : data)
				{
					data0.add(logdata);
				}

				return data0;
			}
		};

		List<UUID> list = new ArrayList<UUID>();
		if (center.containsKey(group))
		{
			list = center.get(group);
		}

		list.add(random);
		center.put(group, list);
		items.put(random, logData);
		return logData;
	}

	@Override
	public Set<UUID> listLogs()
	{
		return items.keySet();
	}

	@Override
	public Set<String> listGroups()
	{
		return center.keySet();
	}

	@Override
	public List<UUID> getLogsOf(String group)
	{
		return center.get(group);
	}

	@Override
	public void remove(LogItem item)
	{
		items.remove(item.id());
	}

}
