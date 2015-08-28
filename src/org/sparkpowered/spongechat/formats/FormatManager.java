/**
 * 	Spongechat, a new powered chat system for SpongePowered Minecraft API.
 * 	Copyright (C) 2015 Kaward <https://github.com/Kaward/>
 * 	Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/>
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sparkpowered.spongechat.formats;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Optional;

/**
 * <p>
 * Esta classe contém os construtores e registradores de formatos.
 * </p>
 *
 * @see java.util.UUID O sistema UUID
 * @see org.sparkpowered.spongechat.formats.Format A interface Format
 * @category Format Handlering
 *
 */
public class FormatManager implements Serializable
{

	private static final long serialVersionUID = -4029550265695310144L;
	public static Map<UUID, Optional<Format>> formats = new HashMap<UUID, Optional<Format>>();
	public static int FORMAT_GLOBAL = Integer.valueOf(1);
	public static int FORMAT_REPLACE = Integer.valueOf(2);

	/**
	 * <p>
	 * Isto registra formatos de canais padrões, como formato local e global.
	 * </p>
	 */
	public static void registerDefaults()
	{
		final FormatManager fm = new FormatManager();
		final Format local = fm.getFormatBuilder().tag("default_local").content("§l[{nick}] §r{prefix} §f{username}§f: §e{message}").as(FormatManager.FORMAT_GLOBAL).build().orNull();
		final Format global = fm.getFormatBuilder().tag("default_local").content("§7[{nick}] §r{prefix} §f{username}§f: §7{message}").as(FormatManager.FORMAT_GLOBAL).build().orNull();
		fm.getFormatRegister().format(local).register();
		fm.getFormatRegister().format(global).register();
	}

	/**
	 *
	 * @return Retorna o construtor de registros de formatos.
	 */
	public FormatRegister getFormatRegister()
	{
		return new FormatRegister();
	}

	/**
	 *
	 * @return Retorna o construtor de formatos.
	 */
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

		/**
		 * <p>
		 * Adiciona o {@link Format} a ser registrado no sistema.
		 * </p>
		 *
		 * @param format O {@link Format} a ser registrado.
		 * @return O construtor para você continuar a registrar o {@link Format}.
		 */
		public FormatRegister format(final Format format)
		{
			this.format = format;
			return this;
		}

		/**
		 * <p>
		 * Registra o {@link Format} no sistema, para poder ser usado no futuro.
		 * </p>
		 *
		 * @return Retorna o UUID do {@link Format} que foi registrado, isso serve como identificação.
		 */
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

		/**
		 * <p>
		 * Adiciona o conteúdo do {@link Format} a ser criado.
		 * </p>
		 *
		 * @param content O conteúdo
		 * @return O construtor para você continuar a criar o {@link Format}.
		 */
		public FormatBuilder content(final String content)
		{
			this.content = content;
			return this;
		}

		/**
		 * <p>
		 * Adiciona a tag do {@link Format} a ser criado. A tag é como sua identificação, ela poderá ser usada desta forma: {tag_aqui}
		 * </p>
		 *
		 * @param tag A tag
		 * @return O construtor para você continuar a criar o {@link Format}.
		 */
		public FormatBuilder tag(final String tag)
		{
			this.tag = tag;
			return this;
		}

		/**
		 * <p>
		 * Define o tipo de {@link Format} a ser criado. Tipo <b>1</b> significa que será o formato de um canal, e <b>2</b> que será uma tag de substituição, como exemplo <code>{playerprefix}</code>,
		 * </p>
		 *
		 * @param type O tipo de {@link Format}.
		 * @return O construtor para você continuar a criar o {@link Format}.
		 */
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

		public Optional<Integer> getAs()
		{
			return Optional.fromNullable(type).or(Optional.of(0));
		}

		public Optional<Format> build()
		{
			@Nullable
			final Format format = new Format()
			{
				private static final long serialVersionUID = 8348812934983743547L;

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

				@Override
				public int type()
				{
					return getAs().or(0);
				}

			};

			return Optional.fromNullable(format);
		}
	}

}
