/**
 *  Spongechat — A new Powered Chat System for SpongePowered Minecraft API.
 *  Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/> and your contributors;
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sparkpowered.spongechat.context;

import java.util.Map;

public interface ContextHandler<Response>
{

	public Map<Integer, ContextExecutor> subscription();

	public ContextExecutor getAction(Integer id);

	public ContextExecutor getNext();

	public ContextExecutor onCompleted();

	public ContextExecutor onFinished(final int reason, final Response argument);

	public boolean canContinue();

	public boolean canBack();

	public void setAction(Integer id, ContextExecutor executor);

	public void addAction(ContextExecutor executor);

	public void back();

	public void next();

	public int size();

	public int now();
}
