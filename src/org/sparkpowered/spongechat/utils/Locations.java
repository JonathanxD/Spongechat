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

import org.sparkpowered.spongechat.SpongechatAPI;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.Extent;

/**
 *
 * <p>
 * Esta classe contém utilidades para a classe Location.
 * </p>
 *
 */
public final class Locations
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

	/**
	 * <p>
	 * Serializa o Location transformando-o em uma String legível. Este processo pode ser desfeito: <code>deserialize(Location)</code>
	 * </p>
	 *
	 * @param font O local para ser serializado.
	 * @return Retorna o serial (string) que poderá ser revertida no futuro.
	 */
	public static <E extends Extent> String serialize(final Location<E> font)
	{
		final String format = "X (%s) Y (%s) Z (%s) W (%s)";
		return String.format(format, font.getBlockX(), font.getBlockY(), font.getBlockZ(), ((World) font.getExtent()).getName());
	}

	public static Location<World> deserialize(final String serial)
	{
		final String[] s = serial.split("(");
		final int x = Integer.parseInt(s[1].split(")")[0]);
		final int y = Integer.parseInt(s[2].split(")")[0]);
		final int z = Integer.parseInt(s[3].split(")")[0]);
		final World world = SpongechatAPI.getProvider().getGame().getServer().getWorld(s[4].split(")")[0]).orNull();
		return new Location<World>(world, x, y, z);
	}

}
