package me.kaward.spongepowered.spongechat;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import bsh.EvalError;

public class Mathematic
{

	public static String get(String expression) throws EvalError, ScriptException
	{
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		return String.valueOf(engine.eval(expression));
	}

}
