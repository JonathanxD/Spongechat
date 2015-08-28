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
package org.sparkpowered.spongechat.logs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.providers.ILoggerManager;

public class LoggerManager implements ILoggerManager
{

	private final HashMap<String, List<UUID>> center = new HashMap<String, List<UUID>>();
	private final HashMap<UUID, LogItem> items = new HashMap<UUID, LogItem>();

	public LoggerManager()
	{
	}

	@Override
	@Nullable
	public LogItem item(final String group, final UUID uuid)
	{
		for (final Map.Entry<UUID, LogItem> item : items.entrySet())
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
		final LogItem logData = new LogItem()
		{
			@Override
			public UUID id()
			{
				return random;
			}

			@Override
			@Nullable
			@SuppressWarnings("unchecked")
			public <T> T get(final String name)
			{
				for (final LogData c : data())
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
				final List<LogData> data0 = new ArrayList<LogData>();
				for (final LogData logdata : data)
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
	public List<UUID> getLogsOf(final String group)
	{
		return center.get(group);
	}

	@Override
	public void remove(final LogItem item)
	{
		items.remove(item.id());
	}

}
