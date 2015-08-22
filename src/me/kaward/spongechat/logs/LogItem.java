package me.kaward.spongechat.logs;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

public interface LogItem
{

	public UUID id();

	@Nullable
	public <T> T get(String name);

	public List<LogData> data();

}
