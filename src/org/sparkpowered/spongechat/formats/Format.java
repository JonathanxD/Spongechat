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
package org.sparkpowered.spongechat.formats;

import java.util.UUID;

/**
 * <p>
 * Esta interface serve para representa��o de dados de um {@link Format}.
 * </p>
 *
 * @see org.sparkpowered.spongechat.formats.FormatManager O manipulador de Formatos
 *
 */
public interface Format
{

	public UUID id();

	/**
	 *
	 * @return Retorna a keyword do Format. Exemplo: {tag_here}, a keyword � tag_here.
	 */
	public String keyword();

	/**
	 *
	 * @return Retorna o conte�do do Format. Exemplo: <code>[{nick}] {prefix} {username}: {msg}</code>
	 */
	public String content();

}
