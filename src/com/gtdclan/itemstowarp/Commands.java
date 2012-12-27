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
		this.plugin.Util.console("   " + playerName + " sent chat command [" + fullCommand + "]", Level.INFO);
		
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
					    "  ^gray/itw mylist^white : Lists all your warps.",
					    "  ^gray/itw import <plugin>^white : Imports warps from another plugin.",
					    "  ^gray - available imports:",
					    "  ^gray - - EcoWarp."
					};
					sender.sendMessage(this.plugin.Util.parseColors(messages));
					return true;
				}
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
						this.plugin.Warps.warpList(playerName);
					}
					else {
						player.sendMessage(this.plugin.Util.parseColors("^redYou do not have permission to list warps."));
					}
				}
				if (args[0].equalsIgnoreCase("mylist")) {
					if (player.hasPermission("itemstowarp.list.own")) {
						this.plugin.Warps.warpMyList(playerName);
					}
					else {
						player.sendMessage(this.plugin.Util.parseColors("^redYou do not have permission to list your warps."));
					}
				}
				if (args[0].equalsIgnoreCase("import")) {
					if (sender.hasPermission("itemstowarp.import")) {
						if (args.length == 1) {
							sender.sendMessage(this.plugin.Util.parseColors("^redYou must include a plugin name to import from"));
						}
						else if (args.length > 2) {
							sender.sendMessage(this.plugin.Util.parseColors("^redPlugin name should not contain spaces."));
						}
						else {
							this.plugin.ImportUtil.doImport(sender.getName(), args[1]);
						}
					}
					else {
						sender.sendMessage(this.plugin.Util.parseColors("^redYou do not have permission to import warps."));
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
