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
package org.sparkpowered.spongechat;

import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.extent.Extent;

/**
 *
 * <p>
 * Esta classe contém utilidades para a classe Location.
 * </p>
 *
 */
public class Locations
{

	/**
	 * <p>
	 * Calcular a distância entre um local até o outro.
	 * </p>
	 *
	 * @param a Primeiro local
	 * @param b Segundo local
	 * @return Retorna o resultado do cálculo
	 */
	public static <E extends Extent> double distance(final Location<E> a, final Location<E> b)
	{
		return Math.sqrt((Math.pow(a.getX() - b.getX(), 2.0D) + Math.pow(a.getY() - b.getY(), 2.0D) + Math.pow(a.getZ() - b.getZ(), 2.0D)));
	}

}
