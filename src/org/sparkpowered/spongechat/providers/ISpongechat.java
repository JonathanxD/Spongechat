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
package org.sparkpowered.spongechat.providers;

import java.util.logging.Logger;

import org.sparkpowered.spongechat.commands.CommandHandler;
import org.spongepowered.api.Game;
import org.spongepowered.api.util.command.spec.CommandSpec;

/**
 *
 * <p>
 * This class contains the interface of provider of Spongechat main class.
 * </p>
 *
 * @see org.sparkpowered.spongechat.Spongechat The Spongechat Main
 * @category Spongechat
 *
 */
public interface ISpongechat
{

	/**
	 *
	 * @return Returns the Game instance of SpongePowered.
	 */
	public Game getGame();

	/**
	 *
	 * @return Returns the CommandHandler, containing the global command executors of Spongechat.
	 */
	public CommandHandler getCommands();

	/**
	 *
	 * @return The CommandSpec of Help Command.
	 */
	public CommandSpec getHelpCommand();

	/**
	 *
	 * @return The CommandSpec of Create Channel Command.
	 */
	public CommandSpec getCreateChannelCommand();

	/**
	 *
	 * @return The CommandSpec of Remove Channel Command.
	 */
	public CommandSpec getRemoveChannelCommand();

	/**
	 *
	 * @return The CommandSpec of Mute Player Command.
	 */
	public CommandSpec getMutePlayerCommand();

	/**
	 *
	 * @return The CommandSpec of Unmute Player Command.
	 */
	public CommandSpec getUnmutePlayerCommand();

	/**
	 *
	 * @return The CommandSpec of Mute Channel Command.
	 */
	public CommandSpec getMuteChannelCommand();

	/**
	 *
	 * @return The CommandSpec of Unmute Channel Command.
	 */
	public CommandSpec getUnmuteChannelCommand();

	/**
	 *
	 * @return The CommandSpec of principal Command.
	 */
	public CommandSpec getSpongechatCommand();

	/**
	 *
	 * @return The Spongechat's logger.
	 */
	public Logger getLogger();

}
