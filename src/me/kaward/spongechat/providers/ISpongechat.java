/**
 *  Spongechat — A new Powered Chat System for SpongePowered Minecraft API.
 *  Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/> and your contributors;
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.kaward.spongechat.providers;

import java.util.logging.Logger;

import org.spongepowered.api.Game;
import org.spongepowered.api.util.command.spec.CommandSpec;

import me.kaward.spongechat.CommandHandler;

public interface ISpongechat
{

	public Game getGame();

	public CommandHandler getCommands();

	public CommandSpec getHelpCommand();

	public CommandSpec getCreateChannelCommand();

	public CommandSpec getRemoveChannelCommand();

	public CommandSpec getMutePlayerCommand();

	public CommandSpec getUnmutePlayerCommand();

	public CommandSpec getMuteChannelCommand();

	public CommandSpec getUnmuteChannelCommand();

	public CommandSpec getSpongechatCommand();

	public Logger getLogger();

}
