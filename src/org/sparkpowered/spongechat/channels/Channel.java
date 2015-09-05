/**
 * 	Spongechat, a new powered chat system for SpongePowered Minecraft API.
 * 	Copyright (C) 2015 Kaward <https://github.com/Kaward/>
 * 	Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/>
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sparkpowered.spongechat.channels;

import java.io.Serializable;
import java.util.UUID;

import org.spongepowered.api.util.command.spec.CommandSpec;

/**
 * <p>
 * This class handle information of channel like your <b>UUID</b>, <b>name</b> and <b>nickname</b>, etc.
 * </p>
 *
 * @category Channel Handlering
 *
 */
public class Channel implements Serializable
{

	public static final long serialVersionUID = -5576999395852541159L;
	public transient CommandSpec channelCommand = null;
	private UUID channelUniqueId = null;
	private String channelFormat = "";
	private String channelNick = "";
	private String channelName = "";
	private int channelMessageCost = 0;
	private int channelMessageDelay = 0;
	private int channelDistance = 0;
	private boolean channelCrossworld = true;
	private boolean channelColorsAllowed = false;
	private boolean channelMathAllowed = false;
	private boolean def = false;

	/**
	 * <p>
	 * Create a fake channel, to use the instance for something. It's used for create channel's.
	 * </p>
	 *
	 * @param channelUniqueId An Universal Unique Identification
	 * @param channelName The name
	 * @param channelNick The nick
	 * @param cost The cost per message
	 * @param delay The delay per message (in secs)
	 * @param distance The distance of message to be displayed (like a radius)
	 * @param crossworld If the message be displayed in all worlds of server
	 * @param colors If collors will be allowed for player's who has permissions for it
	 * @param math If automatic mathematic calculator is allowed in the chat
	 * @param format The format of channel (like e.g. [{nick}] {prefix} {groupprefix} {name}: {message}), use of collors allowed
	 *
	 * @return An fake instance (the channel does not exists) of a Channel
	 */
	public static Channel makeFake(final UUID channelUniqueId, final String channelName, final String channelNick, final Integer cost, final Integer delay, final Integer distance, final Boolean crossworld, final Boolean colors, final Boolean math, final String format)
	{
		return new Channel(channelUniqueId, channelName, channelNick, cost, delay, distance, crossworld, colors, math, format);
	}

	protected Channel(final UUID channelUniqueId, final String channelName, final String channelNick, final Integer cost, final Integer delay, final Integer distance, final Boolean crossworld, final Boolean colors, final Boolean math, final String format)
	{
		this.channelUniqueId = channelUniqueId;
		this.channelNick = channelNick.replaceAll("\\D\\W", "").trim();
		this.channelName = channelName.replaceAll("([^a-zA-Z0-9 ])+", "").trim().replaceAll("  ", " ");
		channelColorsAllowed = colors;
		channelMessageCost = cost;
		channelMessageDelay = delay;
		channelFormat = format;
		channelCrossworld = crossworld;
		channelDistance = distance;
		channelMathAllowed = math;
	}

	/**
	 *
	 * @return If the auto mathematic calculator is allowed and activated, returns <b>true</b>, if not returns <b>false</b>.
	 */
	public boolean hasMathematicsAllowed()
	{
		return channelMathAllowed;
	}

	/**
	 * Activate and allows or block the auto mathematic calculator on the channel
	 *
	 * @param math <b>true</b> for activate and allow, and <b>false</b> for block.
	 */
	public void setMathematicsAllowed(final boolean math)
	{
		channelMathAllowed = math;
	}

	/**
	 *
	 * @return Returns the <b>Universal Unique Identification (UUID)</b> of the channel.
	 * @see java.util.UUID Universal Unique Identification
	 */
	public UUID getIdentification()
	{
		return channelUniqueId;
	}

	/**
	 *
	 * @return The distance (radius) of the channel for messages (0 = worldwide)
	 */
	public int getDistance()
	{
		return channelDistance;
	}

	/**
	 * <p>
	 * Modify the distance (radius) of channel for messages.
	 * </p>
	 *
	 * @param channelDistance The new radius value
	 */
	public void setDistance(final int channelDistance)
	{
		this.channelDistance = channelDistance;
	}

	/**
	 *
	 * @return If the channel is crossworld returns <b>true</b>, if not returns <b>false</b>.
	 */
	public boolean isCrossworld()
	{
		return channelCrossworld;
	}

	/**
	 * <p>
	 * Modify crossworld of channel.. If enabled, the message will be displayed in all worlds and worldwide (ignoring radius).
	 * </p>
	 *
	 * @param channelCrossworld <b>true</b> for enable and <b>false</b> for disable
	 */
	public void setCrossworld(final boolean channelCrossworld)
	{
		this.channelCrossworld = channelCrossworld;
	}

	/**
	 *
	 * @return The CommandSpec of the Channel
	 * @see org.spongepowered.api.util.command.spec.CommandSpec CommandSpec
	 * @see org.sparkpowered.spongechat.commands.ChatCommand Command per channel handlering
	 */
	public CommandSpec getCommand()
	{
		return channelCommand;
	}

	/**
	 *
	 * @return The nickname of the channel. It's also used to create the Channel Command, like /{nickname} {message here}
	 */
	public String getNickname()
	{
		return channelNick;
	}

	/**
	 *
	 * @return The unformated format of channel to be displayed, like e.g '<b>[{nick}] {prefix} {groupprefix} {name}: {message}'<b>
	 */
	public String getFormat()
	{
		return channelFormat;
	}

	/**
	 * <p>
	 * Modify the channel nickname.
	 * <p>
	 *
	 * <p>
	 * <b>CAUTION:</b> needs server restart to be affect the channel command
	 * </p>
	 *
	 * @param channelNick The new nickname for the channel to replace the actually
	 */
	public void setNickname(final String channelNick)
	{
		this.channelNick = channelNick;
	}

	/**
	 *
	 * @return The name of the channel
	 */
	public String getName()
	{
		return channelName;
	}

	/**
	 * Modify the channel's name.
	 *
	 * @param channelName The new channel's name to replace the actually name.
	 */
	public void setName(final String channelName)
	{
		this.channelName = channelName;
	}

	/**
	 * <p>
	 * <b>CAUTION:</b> The Economy Hook Services (EHS) of Spongechat is actually in Development and in Alpha.
	 * </p>
	 *
	 * @return The channel's price/cost for send messages.
	 */
	public int getCost()
	{
		return channelMessageCost;
	}

	/**
	 * <p>
	 * <b>CAUTION:</b> The Economy Hook Services (EHS) of Spongechat is actually in Development and in Alpha.
	 * </p>
	 *
	 * <p>
	 * Modify the channel's price/cost for send messages.
	 * </p>
	 *
	 * @param channelMessageCost The new channel's price/cost
	 */
	public void setCost(final int channelMessageCost)
	{
		this.channelMessageCost = channelMessageCost;
	}

	/**
	 *
	 * @return The channel's delay per message
	 */
	public int getDelay()
	{
		return channelMessageDelay;
	}

	/**
	 * Modify the channel's delay per message
	 *
	 * @param channelMessageDelay The new channel's delay
	 */
	public void setDelay(final int channelMessageDelay)
	{
		this.channelMessageDelay = channelMessageDelay;
	}

	/**
	 *
	 * @return If colors are allowed in the channel, returns <b>true</b> and if not, <b>false</b>
	 */
	public boolean colorsIsAllowed()
	{
		return channelColorsAllowed;
	}

	/**
	 * Enable or disable the use of colors in the channel.
	 *
	 * @param channelColorsAllowed <b>true</b> to enable the use of colors and <b>false</b> to disable.
	 */
	public void setColorsAllowed(final boolean channelColorsAllowed)
	{
		this.channelColorsAllowed = channelColorsAllowed;
	}

	public boolean isDefault()
	{
		return def;
	}

	public void setDefault(final boolean default0)
	{
		def = default0;
	}

}
