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
package org.sparkpowered.spongechat.permissions;

import java.util.HashSet;
import java.util.Set;

import org.spongepowered.api.entity.player.Player;

public class PermissionEntry
{

	private final Set<String> grant = new HashSet<String>();
	private String entry = "";

	public PermissionEntry(final String entry, final Set<String> grant)
	{
		this.entry = entry.toLowerCase();
		for (final String s : grant)
		{
			this.grant.add(s.toLowerCase().replace("#", "").trim());
		}
	}

	public String getEntry()
	{
		return entry;
	}

	public Set<String> getGranted()
	{
		return grant;
	}

	public boolean isGranted(final Player player)
	{
		for (final String permission : grant)
		{
			if (player.hasPermission(permission))
			{
				return true;
			}
		}

		return false;
	}

}
