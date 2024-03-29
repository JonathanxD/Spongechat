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
package org.sparkpowered.spongechat.channels;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.sparkpowered.spongechat.SpongechatAPI;
import org.sparkpowered.spongechat.commands.ChatCommand;
import org.sparkpowered.spongechat.formats.Template;
import org.spongepowered.api.service.sql.SqlService;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.args.GenericArguments;
import org.spongepowered.api.util.command.spec.CommandSpec;

public class Channels
{

	public static SqlService sql = null;
	public static List<Channel> ALL_CHANNELS = Channels.allChannels();
	public static Channel DEFAULT_CHANNEL = Channels.defaultChannel();

	public Channels()
	{
	}

	private static List<Channel> allChannels()
	{
		return SpongechatAPI.getChannels();
	}

	private static Channel defaultChannel()
	{
		return null;
	}

	/**
	 * Load and parse all existent channels.
	 *
	 * @param debug <code>true</code> to activate information's on the console.
	 */
	public strictfp Collection<Channel> loadChannels(final boolean debug) throws SQLException, ClassNotFoundException, IOException
	{
		SpongechatAPI.getChannels().clear();

		if (debug)
		{
			SpongechatAPI.getProvider().getLogger().info("Trying to connect to the database..");
		}

		final Connection conn = datasource("jdbc:h2:spongechat-a0.db").getConnection();

		if (debug)
		{
			SpongechatAPI.getProvider().getLogger().info("Access granted successfully.");
		}

		try
		{
			if (debug)
			{
				SpongechatAPI.getProvider().getLogger().info("Preparing database to get data..");
			}

			final StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE IF NOT EXISTS `Spongechannels` (");
			sb.append("`UniqueID` TINYINT, ");
			sb.append("`Channel` VARCHAR(150), ");
			sb.append("`Nickname` VARCHAR(150), ");
			sb.append("`Format` VARCHAR(200), ");
			sb.append("`Delay` INTEGER(150) NOT NULL DEFAULT 0, ");
			sb.append("`Distance` INTEGER(150) NOT NULL DEFAULT 0, ");
			sb.append("`Cost` DOUBLE(150) NOT NULL DEFAULT 0, ");
			sb.append("`Colors` BOOLEAN NOT NULL DEFAULT false, ");
			sb.append("`Crossworld` BOOLEAN NOT NULL DEFAULT true, ");
			sb.append("`Mathematics` BOOLEAN NOT NULL DEFAULT true, ");
			sb.append("PRIMARY KEY(`UniqueID`));");
			conn.prepareStatement(sb.toString()).execute();

			if (debug)
			{
				SpongechatAPI.getProvider().getLogger().info("Getting database data process started.");
			}

			final PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Spongechannels`;");
			final ResultSet rs = ps.executeQuery();

			if (debug)
			{
				SpongechatAPI.getProvider().getLogger().info("Processing existent Spongechannels...");
			}

			while (rs.next())
			{
				final byte[] des = rs.getBytes("UniqueID");
				final UUID uniqueId = (UUID) parseByte(des);
				final String name = rs.getString("Channel");
				final String nick = rs.getString("Nickname");
				final String form = rs.getString("Format");
				final Integer delay = rs.getInt("Delay");
				final Integer cost = rs.getInt("Cost");
				final Integer distance = rs.getInt("Distance");
				final Boolean colors = rs.getBoolean("Colors");
				final Boolean crossworld = rs.getBoolean("Crossworld");
				final Boolean mathematics = rs.getBoolean("Mathematics");
				final Channel channel = new Channel(uniqueId, name, nick, cost, delay, distance, crossworld, colors, mathematics, Template.getFormat(form));

				for (final Channel sc : SpongechatAPI.getChannels())
				{
					if (sc.getNickname().equalsIgnoreCase(channel.getNickname()))
					{
						continue;
					}
				}

				if (debug)
				{
					SpongechatAPI.getProvider().getLogger().info("Loading Spongechannel: " + channel.getName() + " (" + channel.getNickname() + ")");
				}

				final ChatCommand cc = new ChatCommand(channel);
				final CommandSpec cs = CommandSpec.builder().description(Texts.of("Command to speak in the channel " + name)).arguments(GenericArguments.remainingJoinedStrings(Texts.of("message"))).permission("spongechat.speak." + nick.toLowerCase()).executor(cc).build();
				channel.channelCommand = cs;

				SpongechatAPI.getChannelManager().registerCommand(channel, cs, nick.toLowerCase());
				SpongechatAPI.getChannels().add(channel);
			}

		}
		finally
		{
			conn.close();
			if (debug)
			{
				SpongechatAPI.getProvider().getLogger().info("Database loaded successfully (no-errors)");
			}
		}

		return SpongechatAPI.getChannels();
	}

	/**
	 * Get datasource from SpongePowered provider.
	 *
	 * @param jdbcUrl The URL of jdbc
	 * @return The datasource content
	 */
	protected strictfp javax.sql.DataSource datasource(final String jdbcUrl) throws SQLException
	{
		if (sql == null)
		{

			sql = SpongechatAPI.getProvider().getGame().getServiceManager().provide(SqlService.class).get();
		}

		return sql.getDataSource(jdbcUrl);
	}

	/**
	 * Convert object to byte's array.
	 *
	 * @param obj The object to be converted.
	 * @return Returns the byte's array converted from object.
	 */
	protected strictfp byte[] parseObject(final Object obj) throws IOException
	{
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		os.close();
		return out.toByteArray();
	}

	/**
	 * Convert byte's array to object
	 *
	 * @param data The byte's array
	 * @return Returns the object converted from byte's array.
	 */
	protected strictfp Object parseByte(final byte[] data) throws IOException, ClassNotFoundException
	{
		final ByteArrayInputStream in = new ByteArrayInputStream(data);
		final ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

}
