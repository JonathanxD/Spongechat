/**
 * 	This file is part from Spongechat.
 *
 *  Spongechat � A new powered engine for server conversations.
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
package org.sparkpowered.spongechat.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Javascript
{

	public static String eval(final String expression) throws ScriptException
	{
		final ScriptEngineManager mgr = new ScriptEngineManager();
		final ScriptEngine engine = mgr.getEngineByName("JavaScript");
		return String.valueOf(engine.eval(expression));
	}

}
