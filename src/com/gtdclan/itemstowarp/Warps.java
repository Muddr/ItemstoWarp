package com.gtdclan.itemstowarp;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.avaje.ebean.Query;

public class Warps {
	
	/** The plugin. */
	private final Main plugin;
	
	public Warps(Main instance) {
		plugin = instance;
	}
	
	public void createWarp(String playerName, Location playerloc, String warpName) {
		String[] messages;
		DB data = plugin.database.getDatabase().find(DB.class).where().ieq("Warpname", warpName).findUnique();
		if (data == null) {
			data = new DB();
			data.setIsprivate(false);
			data.setPlayername(playerName);
			data.setWarpworld(playerloc.getWorld().getName());
			data.setWarpx(playerloc.getBlockX());
			data.setWarpy(playerloc.getBlockY());
			data.setWarpz(playerloc.getBlockZ());
			data.setWarpname(warpName);
			plugin.database.getDatabase().save(data);
			messages = new String[] {
			    "Warp created with name: " + warpName,
			    "To make warp private, Use /itw toggle " + warpName
			};
		}
		else {
			messages = new String[] {
			    "Warp already exists with name: " + warpName,
			    "If you own the warp. Use /itw remove " + warpName + " first."
			};
			
		}
		Player player = plugin.getServer().getPlayerExact(playerName);
		player.sendMessage(plugin.Util.parseColors(messages));
	}
	
	public void removeWarp(String playerName, String warpName) {
		DB data = plugin.database.getDatabase().find(DB.class).where().ieq("Warpname", warpName).findUnique();
		Player player = plugin.getServer().getPlayerExact(playerName);
		if (data == null) {
			player.sendMessage(plugin.Util.parseColors("Can not find warp with name: " + warpName));
		}
		else {
			Boolean isWarpCreator = playerName.equals(data.getPlayername());
			Boolean hasPerm = player.hasPermission("itemstowarp.remove.any");
			if (isWarpCreator || hasPerm) {
				plugin.database.getDatabase().delete(data);
				player.sendMessage(plugin.Util.parseColors("Warp " + warpName + " has be removed."));
			}
			else {
				player.sendMessage(plugin.Util.parseColors("You can not delete a warp that you don't own."));
			}
		}
	}
	
	public void togglePrivate(String playerName, String warpName) {
		DB data = plugin.database.getDatabase().find(DB.class).where().ieq("Warpname", warpName).findUnique();
		Player player = plugin.getServer().getPlayerExact(playerName);
		if (data == null) {
			player.sendMessage(plugin.Util.parseColors("Can not find warp with name: " + warpName));
		}
		else {
			Boolean isWarpCreator = playerName.equals(data.getPlayername());
			Boolean hasPerm = player.hasPermission("itemstowarp.makeprivate.any");
			Boolean status = data.getIsprivate();
			if (isWarpCreator || hasPerm) {
				data.setIsprivate(!status);
				plugin.database.getDatabase().save(data);
				String wasstatus = (status ? "private" : "public");
				String isstatus = (status ? "public" : "private");
				player.sendMessage(plugin.Util.parseColors("Warp " + warpName + " is now " + isstatus));
				player.sendMessage(plugin.Util.parseColors("Use /itw toggle " + warpName + " to make it " + wasstatus));
				
			}
			else {
				player.sendMessage(plugin.Util.parseColors("You can not toggle a warp that you don't own."));
			}
		}
	}
	
	public void warpList(String playerName, Integer page) {
		Integer max = 9;
		int pagestart = (page - 1) * max;
		int pageend = (pagestart + max);
		Player player = plugin.getServer().getPlayerExact(playerName);
		Boolean seeAll = player.hasPermission("itemstowarp.list.all");
		Query<DB> data;
		if (seeAll) {
			data = plugin.database.getDatabase().find(DB.class).where().gt("id", 0).orderBy("Warpname");
		}
		else {
			data = plugin.database.getDatabase().find(DB.class).where().eq("Isprivate", false).orderBy("Warpname");
		}
		int warpCount = data.findRowCount();
		if (data != null && warpCount > 0) {
			
			List<DB> warpList = data.findList();
			
			player.sendMessage("Warp Name | Owner | Location; Page " + page + "/" + (int) Math.ceil((double) warpCount / (double) max));
			
			if (warpCount < pageend) {
				pageend = warpCount;
			}
			for (int i = pagestart; i < pageend; i++) {
				String color = "";
				String warpName = warpList.get(i).getWarpname();
				String ownerName = warpList.get(i).getPlayername();
				String world = warpList.get(i).getWarpworld();
				Integer x = warpList.get(i).getWarpx();
				Integer y = warpList.get(i).getWarpy();
				Integer z = warpList.get(i).getWarpz();
				Boolean isPrivate = warpList.get(i).getIsprivate();
				if (isPrivate) {
					color = "^red";
				}
				player.sendMessage(plugin.Util.parseColors(color + warpName + " | " + ownerName + " | " + world + ":" + x + "," + y + "," + z));
			}
		}
		else {
			player.sendMessage("Error: Could not find any warps.");
		}
	}
	
	public void warpMyList(String playerName, Integer page) {
		Integer max = 9;
		int pagestart = (page - 1) * max;
		int pageend = (pagestart + max);
		Player player = plugin.getServer().getPlayerExact(playerName);
		Query<DB> data = plugin.database.getDatabase().find(DB.class).where().eq("PlayerName", playerName).orderBy("Warpname");
		int warpCount = data.findRowCount();
		if (data != null && warpCount > 0) {
			
			List<DB> warpList = data.findList();
			
			player.sendMessage("Warp Name | Owner | Location; Page " + page + "/" + (int) Math.ceil((double) warpCount / (double) max));
			if (warpCount < pageend) {
				pageend = warpCount;
			}
			for (int i = pagestart; i < pageend; i++) {
				String color = "";
				String warpName = warpList.get(i).getWarpname();
				
				String ownerName = warpList.get(i).getPlayername();
				String world = warpList.get(i).getWarpworld();
				Integer x = warpList.get(i).getWarpx();
				Integer y = warpList.get(i).getWarpy();
				Integer z = warpList.get(i).getWarpz();
				Boolean isPrivate = warpList.get(i).getIsprivate();
				if (isPrivate) {
					color = "^red";
				}
				player.sendMessage(plugin.Util.parseColors(color + warpName + " | " + ownerName + " | " + world + ":" + x + "," + y + "," + z));
			}
		}
		else {
			player.sendMessage("Error: Could not find any warps.");
		}
	}
	
	public void warpPlayer(String playerName, String warpName) {
		DB data = plugin.database.getDatabase().find(DB.class).where().ieq("Warpname", warpName).findUnique();
		Player player = plugin.getServer().getPlayerExact(playerName);
		if (data == null) {
			player.sendMessage(plugin.Util.parseColors("Can not find warp with name: " + warpName));
		}
		else {
			Boolean isWarpCreator = playerName.equals(data.getPlayername());
			Boolean isPublic = !data.getIsprivate();
			Boolean hasPerm = player.hasPermission("itemstowarp.warp.any");
			Boolean nocost = player.hasPermission("itemstowarp.warp.nocost");
			
			if (isWarpCreator || isPublic || hasPerm) {
				Boolean charged = plugin.Util.hasAmount(playerName);
				if (charged || nocost) {
					World warpWorld = plugin.getServer().getWorld(data.getWarpworld());
					int warpx = data.getWarpx();
					int warpy = data.getWarpy() + 1;
					int warpz = data.getWarpz();
					Location warpLoc = new Location(warpWorld, warpx, warpy, warpz);
					player.teleport(warpLoc);
				}
				else {
					player.sendMessage(plugin.Util.parseColors("Sorry. You can't afford to warp."));
				}
			}
			else {
				player.sendMessage(plugin.Util.parseColors("Sorry. This warp is private."));
			}
		}
	}
}
