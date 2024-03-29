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
package org.sparkpowered.spongechat.messages;

import java.util.List;
import java.util.UUID;

import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public abstract interface Message
{

	public UUID getIdentification();

	public String getMessageText();

	public String getSentDate();

	public Location<World> getSendLocation();

	public List<Precondition> preconditions();

	public UUID getSender();

	public void setSender(UUID player);

	public void setSended(boolean isSended);

	public void setMessageText(String message);

	public void send();

	public boolean isSended();

	public static enum Precondition
	{
		PREPARED_TO_SEND,
		PLAYER_HAS_DELAY_ACTIVE,
		PLAYER_MUTED,
		PLAYER_NOT_IN_CHANNEL,
		PLAYER_NOT_PRESENT,
		CHANNEL_MUTED,
		CHANNEL_NOT_PRESENT,
		MESSAGE_BLOCKED,
		FAILED_WITH_REASON,
		FAILED_REASON_UNKNOWED;
	}

}
