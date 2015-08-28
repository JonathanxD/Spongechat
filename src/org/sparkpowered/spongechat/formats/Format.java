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

/**
 * <p>
 * Esta interface serve para representação de dados de um {@link Format}.
 * </p>
 *
 * @see org.sparkpowered.spongechat.formats.FormatManager O manipulador de Formatos
 *
 */
public abstract interface Format extends Serializable
{

	/**
	 *
	 * @return Retorna a keyword do Format. Exemplo: {tag_here}, a keyword é tag_here.
	 */
	public String tag();

	/**
	 *
	 * @return Retorna o conteúdo do Format. Exemplo: <code>[{nick}] {prefix} {username}: {msg}</code>
	 */
	public String content();

	/**
	 *
	 * @return Retorna o tipo do Format. (1) para formato de canal, e (2) para formato de substituição no chat
	 */
	public int type();

}
