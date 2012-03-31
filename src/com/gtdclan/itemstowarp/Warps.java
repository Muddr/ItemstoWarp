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
		this.plugin = instance;
	}
	
	public void createWarp(String playerName, Location playerloc, String warpName) {
		String[] messages;
		DB data = this.plugin.database.getDatabase().find(DB.class).where().ieq("Warpname", warpName).findUnique();
		if (data == null) {
			data = new DB();
			data.setIsprivate(false);
			data.setPlayername(playerName);
			data.setWarpworld(playerloc.getWorld().getName());
			data.setWarpx(playerloc.getBlockX());
			data.setWarpy(playerloc.getBlockY());
			data.setWarpz(playerloc.getBlockZ());
			data.setWarpname(warpName);
			this.plugin.database.getDatabase().save(data);
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
		Player player = this.plugin.getServer().getPlayerExact(playerName);
		player.sendMessage(this.plugin.Util.parseColors(messages));
	}
	
	public void removeWarp(String playerName, String warpName) {
		DB data = this.plugin.database.getDatabase().find(DB.class).where().ieq("Warpname", warpName).findUnique();
		Player player = this.plugin.getServer().getPlayerExact(playerName);
		if (data == null) {
			player.sendMessage(this.plugin.Util.parseColors("Can not find warp with name: " + warpName));
		}
		else {
			Boolean isWarpCreator = playerName.equals(data.getPlayername());
			Boolean hasPerm = player.hasPermission("itemstowarp.remove.any");
			if (isWarpCreator || hasPerm) {
				this.plugin.database.getDatabase().delete(data);
				player.sendMessage(this.plugin.Util.parseColors("Warp " + warpName + " has be removed."));
			}
			else {
				player.sendMessage(this.plugin.Util.parseColors("You can not delete a warp that you don't own."));
			}
		}
	}
	
	public void togglePrivate(String playerName, String warpName) {
		DB data = this.plugin.database.getDatabase().find(DB.class).where().ieq("Warpname", warpName).findUnique();
		Player player = this.plugin.getServer().getPlayerExact(playerName);
		if (data == null) {
			player.sendMessage(this.plugin.Util.parseColors("Can not find warp with name: " + warpName));
		}
		else {
			Boolean isWarpCreator = playerName.equals(data.getPlayername());
			Boolean hasPerm = player.hasPermission("itemstowarp.makeprivate.any");
			Boolean status = data.getIsprivate();
			if (isWarpCreator || hasPerm) {
				data.setIsprivate(!status);
				this.plugin.database.getDatabase().save(data);
				String wasstatus = (status ? "private" : "public");
				String isstatus = (status ? "public" : "private");
				player.sendMessage(this.plugin.Util.parseColors("Warp " + warpName + " is now " + isstatus));
				player.sendMessage(this.plugin.Util.parseColors("Use /itw toggle " + warpName + " to make it " + wasstatus));
				
			}
			else {
				player.sendMessage(this.plugin.Util.parseColors("You can not toggle a warp that you don't own."));
			}
		}
	}
	
	public void warpList(String playerName) {
		Player player = this.plugin.getServer().getPlayerExact(playerName);
		Boolean seeAll = player.hasPermission("itemstowarp.list.all");
		Query<DB> data;
		if (seeAll) {
			data = this.plugin.database.getDatabase().find(DB.class).where().gt("id", 0).orderBy("Warpname");
		}
		else {
			data = this.plugin.database.getDatabase().find(DB.class).where().eq("Isprivate", false).orderBy("Warpname");
		}
		int warpCount = data.findRowCount();
		if (data != null && warpCount > 0) {
			
			List<DB> warpList = data.findList();
			player.sendMessage("");
			player.sendMessage(this.plugin.Util.parseColors("^gold^underlineWarp Name   |   Owner   |   Location"));
			
			for (DB warp : warpList) {
				String color = "";
				String warpName = warp.getWarpname();
				String ownerName = warp.getPlayername();
				String message = "";
				if (this.plugin.worlds) {
					message = warp.getWarpworld();
				}
				if (this.plugin.cords) {
					if (this.plugin.worlds) {
						message += " : ";
					}
					Integer x = warp.getWarpx();
					Integer y = warp.getWarpy();
					Integer z = warp.getWarpz();
					message += x + ", " + y + ", " + z;
				}
				Boolean isPrivate = warp.getIsprivate();
				if (isPrivate) {
					color = "^red";
				}
				player.sendMessage(this.plugin.Util.parseColors(color + warpName + "   |   " + ownerName + "   |   " + message));
			}
		}
		else {
			player.sendMessage("Error: Could not find any warps.");
		}
	}
	
	public void warpMyList(String playerName) {
		
		Player player = this.plugin.getServer().getPlayerExact(playerName);
		Query<DB> data = this.plugin.database.getDatabase().find(DB.class).where().eq("PlayerName", playerName).orderBy("Warpname");
		int warpCount = data.findRowCount();
		if (data != null && warpCount > 0) {
			
			List<DB> warpList = data.findList();
			player.sendMessage("");
			player.sendMessage(this.plugin.Util.parseColors("^gold^underlineWarp Name   |   Owner   |   Location"));
			for (DB warp : warpList) {
				String color = "";
				String warpName = warp.getWarpname();
				String ownerName = warp.getPlayername();
				String message = "";
				if (this.plugin.worlds) {
					message = warp.getWarpworld();
				}
				if (this.plugin.cords) {
					if (this.plugin.worlds) {
						message += " : ";
					}
					Integer x = warp.getWarpx();
					Integer y = warp.getWarpy();
					Integer z = warp.getWarpz();
					message += x + ", " + y + ", " + z;
				}
				Boolean isPrivate = warp.getIsprivate();
				if (isPrivate) {
					color = "^red";
				}
				player.sendMessage(this.plugin.Util.parseColors(color + warpName + "   |   " + ownerName + "   |   " + message));
			}
		}
		else {
			player.sendMessage("Error: Could not find any warps.");
		}
	}
	
	public void warpPlayer(String playerName, String warpName) {
		DB data = this.plugin.database.getDatabase().find(DB.class).where().ieq("Warpname", warpName).findUnique();
		Player player = this.plugin.getServer().getPlayerExact(playerName);
		if (data == null) {
			player.sendMessage(this.plugin.Util.parseColors("Can not find warp with name: " + warpName));
		}
		else {
			Boolean isWarpCreator = playerName.equals(data.getPlayername());
			Boolean isPublic = !data.getIsprivate();
			Boolean hasPerm = player.hasPermission("itemstowarp.warp.any");
			Boolean nocost = player.hasPermission("itemstowarp.warp.nocost");
			
			if (isWarpCreator || isPublic || hasPerm) {
				Boolean charged = this.plugin.Util.hasAmount(playerName);
				if (charged || nocost) {
					World warpWorld = this.plugin.getServer().getWorld(data.getWarpworld());
					double warpx = data.getWarpx() + 0.5;
					double warpy = data.getWarpy() + 0.5;
					double warpz = data.getWarpz() + 0.5;
					Location warpLoc = new Location(warpWorld, warpx, warpy, warpz);
					player.teleport(warpLoc);
				}
				else {
					player.sendMessage(this.plugin.Util.parseColors("Sorry. You can't afford to warp."));
				}
			}
			else {
				player.sendMessage(this.plugin.Util.parseColors("Sorry. This warp is private."));
			}
		}
	}
}
