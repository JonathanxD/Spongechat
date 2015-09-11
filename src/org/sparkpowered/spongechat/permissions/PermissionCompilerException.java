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
package org.sparkpowered.spongechat.permissions;

import java.io.InputStreamReader;

public class PermissionCompilerException extends Exception
{

	private static final long serialVersionUID = -3447973245181076624L;
	private InputStreamReader reader = null;
	private String message = "";

	public PermissionCompilerException(final InputStreamReader reader, final String message)
	{
		super(message);
		this.reader = reader;
		this.message = message;
	}

	public InputStreamReader getScriptStream()
	{
		return reader;
	}

	@Override
	public String getMessage()
	{
		return message;
	}

}
