package org.sparkpowered.spongechat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Optional;

public class FormatManager implements Serializable
{

	private static final long serialVersionUID = -4029550265695310144L;
	public static Map<UUID, Optional<Format>> formats = new HashMap<UUID, Optional<Format>>();
	public static int FORMAT_GLOBAL = Integer.valueOf(1);
	public static int FORMAT_REPLACE = Integer.valueOf(2);

	public static void registerDefaults()
	{
		final FormatManager fm = new FormatManager();
		final Format local = fm.getFormatBuilder().tag("default_local").content("§l[{nick}] §r{prefix} §f{username}§f: §e{message}").as(FormatManager.FORMAT_GLOBAL).build().orNull();
		final Format global = fm.getFormatBuilder().tag("default_local").content("§7[{nick}] §r{prefix} §f{username}§f: §7{message}").as(FormatManager.FORMAT_GLOBAL).build().orNull();
		fm.getFormatRegister().format(local).register();
		fm.getFormatRegister().format(global).register();
	}

	public FormatRegister getFormatRegister()
	{
		return new FormatRegister();
	}

	public FormatBuilder getFormatBuilder()
	{
		return new FormatBuilder();
	}

	private final class FormatRegister implements Serializable
	{

		private static final long serialVersionUID = -3817862649041299527L;

		@Nonnull
		private UUID id = null;

		@Nullable
		private Format format = null;

		private FormatRegister()
		{
			id = UUID.randomUUID();
		}

		public FormatRegister format(final Format format)
		{
			this.format = format;
			return this;
		}

		public UUID register()
		{
			formats.put(id, Optional.fromNullable(format));
			return id;
		}
	}

	private final class FormatBuilder implements Serializable
	{

		private static final long serialVersionUID = -7501938032848546377L;

		@Nonnull
		private String tag = "";

		@Nonnull
		private String content = "";

		@Nullable
		private int type = 0;

		private FormatBuilder()
		{
		}

		public FormatBuilder content(final String content)
		{
			this.content = content;
			return this;
		}

		public FormatBuilder tag(final String tag)
		{
			this.tag = tag;
			return this;
		}

		public FormatBuilder as(final int type)
		{
			this.type = type;
			return this;
		}

		private Optional<String> getTag()
		{
			return Optional.of(tag);
		}

		private Optional<String> getContent()
		{
			return Optional.of(content);
		}

		@SuppressWarnings("unused")
		public Optional<Integer> getAs()
		{
			return Optional.fromNullable(type).or(Optional.of(0));
		}

		public Optional<Format> build()
		{
			@Nullable
			final Format format = new Format()
			{
				@Override
				public String tag()
				{
					return getTag().orNull();
				}

				@Override
				public String content()
				{
					return getContent().orNull();
				}

			};

			return Optional.fromNullable(format);
		}
	}

}
