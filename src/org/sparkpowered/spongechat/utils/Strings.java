package org.sparkpowered.spongechat.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Strings
{

	public static String join(final String[] pieces, final String separator)
	{
		return join(Arrays.asList(pieces), separator);
	}

	public static String join(final Collection<String> pieces, final String separator)
	{
		final StringBuilder buffer = new StringBuilder();
		for (final Iterator<String> iter = pieces.iterator(); iter.hasNext();)
		{
			buffer.append(iter.next());
			if (iter.hasNext())
			{
				buffer.append(separator);
			}
		}

		return buffer.toString();
	}

}
