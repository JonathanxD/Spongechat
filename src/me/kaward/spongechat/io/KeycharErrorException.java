package me.kaward.spongechat.io;

import java.io.PrintStream;
import java.io.PrintWriter;

public class KeycharErrorException extends Exception
{

	private static final long serialVersionUID = -6515894210902250033L;

	public KeycharErrorException(char c)
	{
		super("Unsupported char match in key: " + c);
	}

	@Override
	public synchronized Throwable fillInStackTrace()
	{
		return super.fillInStackTrace();
	}

	@Override
	public synchronized Throwable getCause()
	{
		return super.getCause();
	}

	@Override
	public String getLocalizedMessage()
	{
		return super.getLocalizedMessage();
	}

	@Override
	public String getMessage()
	{
		return super.getMessage();
	}

	@Override
	public StackTraceElement[] getStackTrace()
	{
		return super.getStackTrace();
	}

	@Override
	public synchronized Throwable initCause(Throwable arg0)
	{
		return super.initCause(arg0);
	}

	@Override
	public void printStackTrace()
	{
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream arg0)
	{
		super.printStackTrace(arg0);
	}

	@Override
	public void printStackTrace(PrintWriter arg0)
	{
		super.printStackTrace(arg0);
	}

	@Override
	public String toString()
	{
		return super.toString();
	}

}
