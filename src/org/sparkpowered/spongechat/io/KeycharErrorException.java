/**
 * 	This file is part from Spongechat.
 *
 *  Spongechat — A new powered engine for server conversations.
 *  Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/> and your contributors;
 *  Copyright (C) 2015 contributors
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sparkpowered.spongechat.io;

import java.io.PrintStream;
import java.io.PrintWriter;

public class KeycharErrorException extends Exception
{

	private static final long serialVersionUID = -6515894210902250033L;

	public KeycharErrorException(final char c)
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
	public synchronized Throwable initCause(final Throwable arg0)
	{
		return super.initCause(arg0);
	}

	@Override
	public void printStackTrace()
	{
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(final PrintStream arg0)
	{
		super.printStackTrace(arg0);
	}

	@Override
	public void printStackTrace(final PrintWriter arg0)
	{
		super.printStackTrace(arg0);
	}

	@Override
	public String toString()
	{
		return super.toString();
	}

}
