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
package org.sparkpowered.spongechat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import org.sparkpowered.spongechat.channels.Channel;
import org.sparkpowered.spongechat.channels.ChannelManager;
import org.sparkpowered.spongechat.json.JSONObject;

public class Options
{

	private File language = null;

	@Nullable
	private Channel defaultChannel = null;

	public Options()
	{
	}

	public File getLanguage()
	{
		return language;
	}

	public Channel getDefaultChannel()
	{
		return defaultChannel;
	}

	public void setDefaultChannel(@Nullable final Channel channel)
	{
		defaultChannel = channel;
	}

	public void setLanguage(final File language)
	{
		this.language = language;
	}

	public boolean exists()
	{
		final File file = new File("Spongechat" + File.separator + "options.dat");
		return file.exists();
	}

	@SuppressWarnings("unchecked")
	public void load() throws IOException
	{
		final File file = new File("Spongechat" + File.separator + "options.dat");
		final FileInputStream fis = new FileInputStream(file);
		final InputStreamReader stream = new InputStreamReader(fis, Charset.forName("UTF-8"));
		final BufferedReader reader = new BufferedReader(stream);

		String source = "";
		String string = "";
		while ((string = reader.readLine()) != null)
		{
			source += string;
		}

		reader.close();
		stream.close();
		fis.close();

		final ChannelManager cm = new ChannelManager();
		final JSONObject json = new JSONObject(source);
		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) json.get("options");

		language = new File(String.valueOf(map.get("language")));
		defaultChannel = cm.getChannel(String.valueOf(map.get("defaultChannel")), false).getKey();
	}

	public void makeDefault() throws IOException
	{
		final Map<String, Object> map = new HashMap<String, Object>();

		final File file = new File("Spongechat" + File.separator + "lang_english(us).txt");
		if (!file.exists())
		{
			// copy from jar to folder
		}

		final String uuid = (defaultChannel != null ? defaultChannel.getIdentification().toString() : "0");
		map.put("defaultChannel", uuid);
		map.put("language", file.getAbsolutePath());
		save(map);
	}

	public void save() throws IOException
	{
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("defaultChannel", defaultChannel.getIdentification().toString());
		map.put("language", language.getAbsolutePath());
		save(map);
	}

	public void save(@Nullable Map<String, Object> map) throws IOException
	{
		if (map == null)
		{
			map = new HashMap<String, Object>();
		}

		final JSONObject json = new JSONObject();

		map.put("defaultChannel", defaultChannel.getIdentification().toString());
		map.put("language", language.getAbsolutePath());
		json.put("options", map);

		final File file = new File("Spongechat" + File.separator + "options.dat");
		file.getParentFile().mkdirs();
		if (!file.exists())
		{
			file.createNewFile();
		}

		final FileOutputStream fos = new FileOutputStream(file);
		final OutputStreamWriter stream = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
		final BufferedWriter writer = new BufferedWriter(stream);
		json.write(writer);
		writer.flush();
		writer.close();
	}

	// > /spongechat options setdefault <name>
	// > /spongechat options setlanguage <file.txt> — #Não agora
	// > /spongechat options update <check/download>
	// >

}
