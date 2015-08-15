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
 **/
package me.kaward.spongepowered.spongechat;

import java.io.Serializable;
import java.util.UUID;

import org.spongepowered.api.util.command.spec.CommandSpec;

public class Spongechannel implements Serializable
{

	private static final long serialVersionUID = -5576999395852541159L;
	private transient CommandSpec channelCommand = null;
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

	protected Spongechannel(UUID channelUniqueId, String channelName, String channelNick, Integer cost, Integer delay, Integer distance, Boolean crossworld, Boolean colors, Boolean math, String format)
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

	public Boolean getChannelMathematicsAllowed()
	{
		return channelMathAllowed;
	}

	public void setChannelMathematicsAllowed(Boolean math)
	{
		this.channelMathAllowed = math;
	}

	public UUID getChannelUniqueId()
	{
		return channelUniqueId;
	}

	public Integer getDistance()
	{
		return channelDistance;
	}

	public void setDistance(Integer channelDistance)
	{
		this.channelDistance = channelDistance;
	}

	public Boolean isCrossworld()
	{
		return channelCrossworld;
	}

	public void setCrossworld(Boolean channelCrossworld)
	{
		this.channelCrossworld = channelCrossworld;
	}

	public CommandSpec getChannelCommand()
	{
		return channelCommand;
	}

	public void setChannelCommand(CommandSpec channelCommand)
	{
		this.channelCommand = channelCommand;
	}

	public String getNickname()
	{
		return channelNick;
	}

	public String getFormat()
	{
		return channelFormat;
	}

	public void setNickname(String channelNick)
	{
		this.channelNick = channelNick;
	}

	public String getChannelName()
	{
		return channelName;
	}

	public void setChannelName(String channelName)
	{
		this.channelName = channelName;
	}

	public Integer getChannelMessageCost()
	{
		return channelMessageCost;
	}

	public void setChannelMessageCost(Integer channelMessageCost)
	{
		this.channelMessageCost = channelMessageCost;
	}

	public Integer getChannelMessageDelay()
	{
		return channelMessageDelay;
	}

	public void setChannelMessageDelay(Integer channelMessageDelay)
	{
		this.channelMessageDelay = channelMessageDelay;
	}

	public Boolean getChannelColorsAllowed()
	{
		return channelColorsAllowed;
	}

	public void setChannelColorsAllowed(Boolean channelColorsAllowed)
	{
		this.channelColorsAllowed = channelColorsAllowed;
	}

}
