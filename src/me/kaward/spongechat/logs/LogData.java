package me.kaward.spongechat.logs;

public class LogData
{

	private String name = "";
	private Object data = null;

	public static LogData from(String name, Object data)
	{
		return new LogData(name, data);
	}

	public LogData(String name, Object data)
	{
		this.name = name;
		this.data = data;
	}

	public String getName()
	{
		return name;
	}

	public Object getData()
	{
		return data;
	}

}
