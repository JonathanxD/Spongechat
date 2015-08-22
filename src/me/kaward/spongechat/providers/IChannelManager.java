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

import java.util.Collection;
import java.util.Map;

import org.spongepowered.api.util.command.spec.CommandSpec;

import me.kaward.spongechat.Channel;
import me.kaward.spongechat.logs.ChannelLogs;

public interface IChannelManager
{

	public void delete(Channel channel);

	public void setMuted(Channel channel, boolean muted);

	public void setMuted(Collection<Channel> channels, boolean muted);

	public void registerCommand(Channel channel, CommandSpec command, String... names);

	public void registerCommand(Channel channel);

	public void clear(Channel channel);

	public boolean isMuted(Channel channel);

	public ChannelLogs getLogs();

	public Collection<Map.Entry<Channel, Boolean>> isMuted(Collection<Channel> channels);

}
