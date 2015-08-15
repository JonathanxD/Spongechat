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
package me.kaward.spongepowered.spongechat.context.pack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import me.kaward.spongepowered.spongechat.Spongechat;
import me.kaward.spongepowered.spongechat.context.ContextExecutor;
import me.kaward.spongepowered.spongechat.context.ContextHandler;
import me.kaward.spongepowered.spongechat.context.ContextList;

import org.spongepowered.api.event.EventHandler;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerChatEvent;
import org.spongepowered.api.text.Texts;

public class ChannelCreateContext implements ContextHandler<ChannelCreateContext>, EventHandler<PlayerChatEvent>, Cloneable, Serializable
{

	private static final long serialVersionUID = 907479619568749248L;
	protected Map<Integer, ContextExecutor> subscriptions = new HashMap<Integer, ContextExecutor>();
	protected Map<Integer, Object> args = new HashMap<Integer, Object>();
	protected Integer now = 1, size = 0;
	protected UUID userId = null;

	public ChannelCreateContext(UUID player)
	{
		this.userId = player;
	}

	@Override
	@Subscribe(order = Order.FIRST)
	public void handle(PlayerChatEvent event)
	{
		if (event.getSource().getUniqueId().equals(userId))
		{
			String message = Texts.toPlain(event.getMessage());

			if (message.contains("@"))
			{
				if (message.equals("@back"))
				{
					if (canBack())
					{
						back();
						event.setCancelled(true);
					}
					else
					{
						event.getSource().sendMessage(Texts.of("§e[Spongechat] §6You can't back more."));
						event.setCancelled(true);
					}
				}
				else if (message.equals("@next"))
				{
					if (canContinue())
					{
						next();
						event.setCancelled(true);
					}
					else
					{
						event.getSource().sendMessage(Texts.of("§e[Spongechat] §6You can't next more."));
						event.setCancelled(true);
					}
				}
				else if (message.equals("@exit"))
				{
					event.getSource().sendMessage(Texts.of("§e[Spongechat] §6You are leaving now."));
					event.setCancelled(true);
					onFinished(1, this).execute();
				}
				else
				{
					event.getSource().sendMessage(Texts.of("§e[Spongechat] §6Invalid @command, try again."));
					event.setCancelled(true);
				}
			}
			else
			{
				args.put(now, message);
				getNext().execute();
			}
		}
	}

	@Override
	public Map<Integer, ContextExecutor> subscription()
	{
		return subscriptions;
	}

	@Override
	public ContextExecutor getNext()
	{
		if (canContinue())
		{
			now++;
			return getAction(now);
		}
		else
		{
			return onCompleted();
		}
	}

	@Override
	public ContextExecutor getAction(Integer id)
	{
		return subscription().get(id);
	}

	@Override
	public void addAction(ContextExecutor executor)
	{
		size++;
		subscription().put(size, executor);
	}

	@Override
	public void setAction(Integer id, ContextExecutor executor)
	{
		subscription().put(id, executor);
	}

	@Override
	public boolean canContinue()
	{
		if ((now() + 1) <= size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean canBack()
	{
		if ((now() - 1) > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public void back()
	{
		now--;
		getAction(now).execute();
	}

	@Override
	public void next()
	{
		now++;
		getAction(now).execute();
	}

	@Override
	public int now()
	{
		return now;
	}

	@Override
	public ContextExecutor onCompleted()
	{
		return new ContextExecutor()
		{
			@Override
			public void execute()
			{

			}
		};
	}

	@Override
	public ContextExecutor onFinished(final int reason, final ChannelCreateContext handler)
	{
		return new ContextExecutor()
		{
			@Override
			public void execute()
			{
				if (reason == 1)
				{
					Spongechat sc = Spongechat.sponge.getServiceManager().provide(Spongechat.class).get();
					sc.getGame().getEventManager().unregister(handler);
					ContextList.getActive().remove(userId);
					ContextList.getSources().remove(userId);
				}
			}
		};
	}
}
