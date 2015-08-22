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
package me.kaward.spongechat;

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
	private Integer channelMessageCost = 0;
	private Integer channelMessageDelay = 0;
	private Integer channelDistance = 0;
	private Boolean channelCrossworld = true;
	private Boolean channelColorsAllowed = false;
	private Boolean channelMathAllowed = false;

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
	public static Channel makeFake(UUID channelUniqueId, String channelName, String channelNick, Integer cost, Integer delay, Integer distance, Boolean crossworld, Boolean colors, Boolean math, String format)
	{
		return new Channel(channelUniqueId, channelName, channelNick, cost, delay, distance, crossworld, colors, math, format);
	}

	protected Channel(UUID channelUniqueId, String channelName, String channelNick, Integer cost, Integer delay, Integer distance, Boolean crossworld, Boolean colors, Boolean math, String format)
	{
		this.channelUniqueId = channelUniqueId;
		this.channelNick = channelNick.replaceAll("\\D\\W", "").trim();
		this.channelName = channelName.replaceAll("([^a-zA-Z0-9 ])+", "").trim().replaceAll("  ", " ");
		this.channelColorsAllowed = colors;
		this.channelMessageCost = cost;
		this.channelMessageDelay = delay;
		this.channelFormat = format;
		this.channelCrossworld = crossworld;
		this.channelDistance = distance;
		this.channelMathAllowed = math;
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
	public void setMathematicsAllowed(boolean math)
	{
		this.channelMathAllowed = math;
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
	public void setDistance(int channelDistance)
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
	public void setCrossworld(boolean channelCrossworld)
	{
		this.channelCrossworld = channelCrossworld;
	}

	/**
	 *
	 * @return The CommandSpec of the Channel
	 * @see org.spongepowered.api.util.command.spec.CommandSpec CommandSpec
	 * @see me.kaward.spongechat.ChatCommand Command per channel handlering
	 */
	public CommandSpec getChannelCommand()
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
	public void setNickname(String channelNick)
	{
		this.channelNick = channelNick;
	}

	/**
	 *
	 * @return The name of the channel
	 */
	public String getChannelName()
	{
		return channelName;
	}

	/**
	 * Modify the channel's name.
	 *
	 * @param channelName The new channel's name to replace the actually name.
	 */
	public void setChannelName(String channelName)
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
	public int getChannelMessageCost()
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
	public void setMessageCost(int channelMessageCost)
	{
		this.channelMessageCost = channelMessageCost;
	}

	/**
	 *
	 * @return The channel's delay per message
	 */
	public int getMessageDelay()
	{
		return channelMessageDelay;
	}

	/**
	 * Modify the channel's delay per message
	 *
	 * @param channelMessageDelay The new channel's delay
	 */
	public void setChannelMessageDelay(int channelMessageDelay)
	{
		this.channelMessageDelay = channelMessageDelay;
	}

	/**
	 *
	 * @return If colors are allowed in the channel, returns <b>true</b> and if not, <b>false</b>
	 */
	public boolean getChannelColorsAllowed()
	{
		return channelColorsAllowed;
	}

	/**
	 * Enable or disable the use of colors in the channel.
	 *
	 * @param channelColorsAllowed <b>true</b> to enable the use of colors and <b>false</b> to disable.
	 */
	public void setChannelColorsAllowed(boolean channelColorsAllowed)
	{
		this.channelColorsAllowed = channelColorsAllowed;
	}

}
