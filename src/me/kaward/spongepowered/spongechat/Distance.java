package me.kaward.spongepowered.spongechat;

import org.spongepowered.api.world.Location;

public class Distance
{

	public static double build(Location loc1, Location loc2)
	{
		return Math.sqrt(quad(loc1, loc2));
	}

	private static double quad(Location a, Location b)
	{
		return Math.pow(a.getX() - b.getX(), 2.0D) + Math.pow(a.getY() - b.getY(), 2.0D) + Math.pow(a.getZ() - b.getZ(), 2.0D);
	}

}
