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
package org.sparkpowered.spongechat.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * <p>
 * This annotation preserve necessary informations per language item.
 * </p>
 *
 * @category Language Handlering
 * @see org.sparkpowered.spongechat.lang.Language Language Handlering
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Lang
{

	/**
	 *
	 * @return The formal name of the item.
	 */
	public String name() default "Unknown";

	/**
	 *
	 * @return The representative id of item.
	 */
	public int id() default 0;

	/**
	 *
	 * @return The representative id of item's group.
	 */
	public int group() default 0;

	/**
	 *
	 * @return The keywords to match this item.
	 */
	public String[]keywords() default {};

}
