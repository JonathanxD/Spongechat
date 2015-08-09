package me.kaward.spongepowered.spongechat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.spongepowered.api.service.sql.SqlService;

public final class ChannelLoader
{

	protected static SqlService sql = null;

	protected ChannelLoader()
	{
	}

	public void loadChannels(boolean debug) throws SQLException, ClassNotFoundException, IOException
	{
		if (debug)
		{
			getSpongechat().getLogger().info("Trying to connect to the database..");
		}

		Connection conn = datasource("jdbc:h2:spongechat-a0.db").getConnection();

		if (debug)
		{
			getSpongechat().getLogger().info("Access granted successfully.");
		}

		try
		{
			if (debug)
			{
				getSpongechat().getLogger().info("Preparing database to get data..");
			}

			StringBuilder sb = new StringBuilder();
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
				getSpongechat().getLogger().info("Getting database data process started.");
			}

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Spongechannels`;");
			ResultSet rs = ps.executeQuery();

			Set<Spongechannel> channels = new HashSet<Spongechannel>();

			if (debug)
			{
				getSpongechat().getLogger().info("Processing existent Spongechannels...");
			}

			while (rs.next())
			{
				byte[] des = rs.getBytes("UniqueID");
				UUID uniqueId = (UUID) parseByte(des);
				String name = rs.getString("Channel");
				String nick = rs.getString("Nickname");
				String form = rs.getString("Format");
				Integer delay = rs.getInt("Delay");
				Integer cost = rs.getInt("Cost");
				Integer distance = rs.getInt("Distance");
				Boolean colors = rs.getBoolean("Colors");
				Boolean crossworld = rs.getBoolean("Crossworld");
				Boolean mathematics = rs.getBoolean("Mathematics");
				Spongechannel channel = new Spongechannel(uniqueId, name, nick, cost, delay, distance, crossworld, colors, mathematics, form);

				for (Spongechannel sc : channels)
				{
					if (sc.getNickname().equalsIgnoreCase(channel.getNickname()))
					{
						continue;
					}
				}

				if (debug)
				{
					getSpongechat().getLogger().info("Loading Spongechannel: " + channel.getChannelName() + " (" + channel.getNickname() + ")");
				}

				channels.add(channel);
			}

		}
		finally
		{
			conn.close();
			if (debug)
			{
				getSpongechat().getLogger().info("Database loaded successfully (no-errors)");
			}
		}
	}

	protected javax.sql.DataSource datasource(String jdbcUrl) throws SQLException
	{
		if (sql == null)
		{

			sql = getSpongechat().getGame().getServiceManager().provide(SqlService.class).get();
		}

		return sql.getDataSource(jdbcUrl);
	}

	protected Spongechat getSpongechat()
	{
		return Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
	}

	public static byte[] parseObject(Object obj) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		os.close();
		return out.toByteArray();
	}

	public static Object parseByte(byte[] data) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

}
