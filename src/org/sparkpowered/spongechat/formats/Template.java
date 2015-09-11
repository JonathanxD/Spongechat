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
package org.sparkpowered.spongechat.formats;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.spongepowered.api.plugin.Plugin;

public class Template
{

	private static final Map<UUID, Format> formats = new HashMap<UUID, Format>();
	private static final Map<UUID, Tag> tags = new HashMap<UUID, Tag>();

	public static Collection<Format> getFormats()
	{
		return formats.values();
	}

	public static Collection<Tag> getTags()
	{
		return tags.values();
	}

	public static Format getFormat(final UUID id)
	{
		final Format format = formats.containsKey(id) ? formats.get(id) : null;
		return format;
	}

	public static Tag getTag(final UUID id)
	{
		final Tag tag = tags.containsKey(id) ? tags.get(id) : null;
		return tag;
	}

	public static Format getFormat(final String keyword)
	{
		for (final Format format : formats.values())
		{
			if (format.keyword().toLowerCase().equals(keyword.toLowerCase()))
			{
				return format;
			}
		}

		return null;
	}

	public static Tag getTag(final String keyword)
	{
		for (final Tag tag : tags.values())
		{
			if (tag.keyword().toLowerCase().equals(keyword.toLowerCase()))
			{
				return tag;
			}
		}

		return null;
	}

	public static Format registerFormat(final String keyword, final String content)
	{
		final Format format = new Format()
		{
			private final UUID id = UUID.randomUUID();

			@Override
			public UUID id()
			{
				return id;
			}

			@Override
			public String keyword()
			{
				return keyword;
			}

			@Override
			public String content()
			{
				return content;
			}
		};

		formats.put(format.id(), format);
		return format;
	}

	public static Tag registerTag(final Plugin provider, final String content, final String keyword, final String bruckets)
	{
		final Tag tag = new Tag()
		{
			private final UUID id = UUID.randomUUID();

			@Override
			public UUID id()
			{
				return id;
			}

			@Override
			public Plugin provider()
			{
				return provider;
			}

			@Override
			public String keyword()
			{
				return keyword;
			}

			@Override
			public String content()
			{
				return content;
			}

			@Override
			public String brackets()
			{
				return bruckets;
			}
		};

		tags.put(tag.id(), tag);
		return tag;
	}

}
