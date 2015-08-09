package me.kaward.spongepowered.spongechat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import me.kaward.spongepowered.spongechat.providers.IPlayerManager;

import org.spongepowered.api.entity.player.Player;

public class PlayerManager implements IPlayerManager
{

	protected Map<UUID, Set<Spongechannel>> channels = new HashMap<UUID, Set<Spongechannel>>();
	protected Map<UUID, Spongechannel> focus = new HashMap<UUID, Spongechannel>();
	protected Map<UUID, Integer> count = new HashMap<UUID, Integer>();
	protected Set<UUID> afk = new HashSet<UUID>();

	protected PlayerManager()
	{
	}

	@Override
	public void setFocus(Player player, Spongechannel channel)
	{
		focus.put(player.getUniqueId(), channel);
	}

	@Override
	public void setPlayerTotalMessages(Player player, Integer total)
	{
		count.put(player.getUniqueId(), total);
	}

	@Nullable
	@Override
	public int getPlayerTotalMessages(Player player)
	{
		return (count.containsKey(player.getUniqueId()) ? count.get(player.getUniqueId()) : 0);
	}

	@Nullable
	@Override
	public Spongechannel getFocusedChannel(Player player)
	{
		return focus.get(player.getUniqueId());
	}

	@Override
	public boolean isAfk(Player player)
	{
		return afk.contains(player.getUniqueId());
	}

	public boolean isAfk(UUID uuid)
	{
		return afk.contains(uuid);
	}

	@Override
	public int activeChannels(Player player)
	{
		return (channels.containsKey(player.getUniqueId()) ? channels.get(player.getUniqueId()).size() : 0);
	}

	@Override
	public Set<Spongechannel> getActiveChannels(Player player)
	{
		return channels.get(player.getUniqueId());
	}

	@Override
	public void setPlayerAfk(Player player, Boolean isafk)
	{
		if (isafk)
		{
			afk.add(player.getUniqueId());
		}
		else
		{
			afk.remove(player.getUniqueId());
		}
	}

}
