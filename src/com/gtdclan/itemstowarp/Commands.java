package com.gtdclan.itemstowarp;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	private final Main plugin;
	
	public Commands(Main instance) {
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command senderCommand, String label, String[] args) {
		Player player = null;
		String playerName = "Console";
		if (sender instanceof Player) {
			player = (Player) sender;
			playerName = player.getName();
		}
		
		String command = senderCommand.getName().toLowerCase();
		
		String fullCommand = "/" + senderCommand.getName();
		for (String arg : args) {
			fullCommand += " " + arg;
		}
		this.plugin.Util.console("Player [" + playerName + "] sent chat command [" + fullCommand + "]", Level.INFO);
		
		// itw Command
		if (command.equals("itw")) {
			if (player != null) {// Make sure it's a player
				if (args.length == 0) { // if no args, display usage message
				
					String[] messages = new String[] {
					    "^redCommands:",
					    "  ^gray/itw create <name>^white : Creates a warp at your location.",
					    "  ^gray/itw toggle <name>^white : Toggles the warp from public/private.",
					    "  ^gray/itw remove <name>^white : Removes the warp.",
					    "  ^gray/itw warp <name>^white : Warps to <name>.",
					    "  ^gray/itw list^white : Lists all public warps.",
					    "  ^gray/itw mylist^white : Lists all your warps."
					};
					player.sendMessage(this.plugin.Util.parseColors(messages));
					return true;
				}
				// itw create Command
				if (args[0].equalsIgnoreCase("create")) {
					if (player.hasPermission("itemstowarp.create")) {
						if (args.length == 1) {
							player.sendMessage(this.plugin.Util.parseColors("^redYou must include a name to set a warp."));
						}
						else if (args.length > 2) {
							player.sendMessage(this.plugin.Util.parseColors("^redWarp name can not contain spaces."));
						}
						
						else {
							Location playerloc = player.getLocation();
							this.plugin.Warps.createWarp(playerName, playerloc, args[1].toLowerCase());
						}
					}
					else {
						player.sendMessage(this.plugin.Util.parseColors("^redYou do not have permission to create warps."));
					}
				}
				if (args[0].equalsIgnoreCase("toggle")) {
					if (player.hasPermission("itemstowarp.makeprivate") || player.hasPermission("itemstowarp.makeprivate.any")) {
						if (args.length == 1) {
							player.sendMessage(this.plugin.Util.parseColors("^redYou must include a name to toggle a warp."));
						}
						else if (args.length > 2) {
							player.sendMessage(this.plugin.Util.parseColors("^redWarp name can not contain spaces."));
						}
						
						else {
							this.plugin.Warps.togglePrivate(playerName, args[1].toLowerCase());
						}
					}
					else {
						player.sendMessage(this.plugin.Util.parseColors("^redYou do not have permission to toggle warps."));
					}
				}
				if (args[0].equalsIgnoreCase("remove")) {
					if (player.hasPermission("itemstowarp.remove") || player.hasPermission("itemstowarp.remove.any")) {
						if (args.length == 1) {
							player.sendMessage(this.plugin.Util.parseColors("^redYou must include a name to set a warp."));
						}
						else if (args.length > 2) {
							player.sendMessage(this.plugin.Util.parseColors("^redWarp name can not contain spaces."));
						}
						else {
							this.plugin.Warps.removeWarp(playerName, args[1].toLowerCase());
						}
					}
					else {
						player.sendMessage(this.plugin.Util.parseColors("^redYou do not have permission to remove warps."));
					}
				}
				if (args[0].equalsIgnoreCase("warp")) {
					if (player.hasPermission("itemstowarp.warp") || player.hasPermission("itemstowarp.warp.any")) {
						if (args.length == 1) {
							player.sendMessage(this.plugin.Util.parseColors("^redYou must include a name to set a warp."));
						}
						else if (args.length > 2) {
							player.sendMessage(this.plugin.Util.parseColors("^redWarp name can not contain spaces."));
						}
						
						else {
							this.plugin.Warps.warpPlayer(playerName, args[1].toLowerCase());
						}
					}
					else {
						player.sendMessage(this.plugin.Util.parseColors("^redYou do not have permission to use warps."));
					}
				}
				if (args[0].equalsIgnoreCase("list")) {
					if (player.hasPermission("itemstowarp.list") || player.hasPermission("itemstowarp.list.all")) {
						Integer page = 1;
						if (args.length == 1) {
							player.sendMessage(this.plugin.Util.parseColors("^yellowUse /itw list # for more pages"));
							page = 1;
							this.plugin.Warps.warpList(playerName, page);
						}
						else {
							page = Integer.parseInt(args[1]);
							if (page > 0) {
								this.plugin.Warps.warpList(playerName, page);
							}
							else {
								player.sendMessage(this.plugin.Util.parseColors("^redPage must be a number."));
							}
						}
					}
					else {
						player.sendMessage(this.plugin.Util.parseColors("^redYou do not have permission to list warps."));
					}
				}
				if (args[0].equalsIgnoreCase("mylist")) {
					if (player.hasPermission("itemstowarp.list.own")) {
						Integer page = 1;
						if (args.length == 1) {
							player.sendMessage(this.plugin.Util.parseColors("^yellowUse /itw mylist # for more pages"));
							page = 1;
							this.plugin.Warps.warpMyList(playerName, page);
						}
						else {
							page = Integer.parseInt(args[1]);
							if (page > 0) {
								this.plugin.Warps.warpMyList(playerName, page);
							}
							else {
								player.sendMessage(this.plugin.Util.parseColors("^redPage must be a number."));
							}
						}
					}
					else {
						player.sendMessage(this.plugin.Util.parseColors("^redYou do not have permission to list your warps."));
					}
				}
			}
			else {
				System.out.println("ItemtoWarp Commands must be done ingame.");
			}
			return true;
		}
		return false;
	}
}
