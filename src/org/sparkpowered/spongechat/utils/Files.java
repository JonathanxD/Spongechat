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
package org.sparkpowered.spongechat.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class Files
{

	public static final InputStreamReader getInputStreamReader(final String filepathInJar)
	{
		final InputStream stream = Files.class.getClass().getResourceAsStream(filepathInJar);
		final InputStreamReader reader = new InputStreamReader(stream, Charset.forName("UTF-8"));
		return reader;
	}

	public static final InputStream getInputStream(final String filepathInJar)
	{
		final InputStream stream = Files.class.getClass().getResourceAsStream(filepathInJar);
		return stream;
	}

	public static void writeResource(final String resourcepathInJar, final File outfile) throws IOException
	{
		final InputStream is = getInputStream(resourcepathInJar);
		writeResource(is, outfile);
	}

	public static void writeResource(final InputStream is, final File outfile) throws IOException
	{
		if (!outfile.exists())
		{
			outfile.getParentFile().mkdirs();
			outfile.createNewFile();
		}

		final OutputStream os = new FileOutputStream(outfile);
		final byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer)) > -1)
		{
			os.write(buffer, 0, len);
		}

		os.flush();
		os.close();
		is.close();
	}

}
