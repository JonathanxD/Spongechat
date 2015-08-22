package me.kaward.spongechat.providers;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import me.kaward.spongechat.logs.LogData;
import me.kaward.spongechat.logs.LogItem;

public interface ILoggerManager
{

	public LogItem item(String group, UUID uuid);

	public LogItem add(String group, LogData... data);

	public Set<UUID> listLogs();

	public Set<String> listGroups();

	public List<UUID> getLogsOf(String group);

	public void remove(LogItem item);

}
