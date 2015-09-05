package org.sparkpowered.spongechat.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Reader
{

	public static final strictfp InputStreamReader is(final String path)
	{
		final InputStream stream = Reader.class.getClass().getResourceAsStream(path);
		final InputStreamReader reader = new InputStreamReader(stream, Charset.forName("UTF-8"));
		return reader;
	}

}
