package com.gtdclan.itemstowarp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * The Class Util.
 */
public class Util {
	
	/** Minecraft's console logger. */
	private final Logger minecraftLogger = Logger.getLogger("Minecraft");
	
	/** The plugin. */
	private final Main plugin;
	
	public Util(Main instance) {
		this.plugin = instance;
	}
	
	/**
	 * Sends message to the console.
	 * 
	 * @param message
	 *        the message
	 * @param level
	 *        the level
	 * @see com.gtdclan.gtdsmp.gtdsmpUtil#console(String[]) console(String[])
	 */
	public void console(String message, Level level) {
		this.minecraftLogger.log(level, "[" + this.plugin.getDescription().getName() + "] " + message);
	}
	
	/**
	 * Sends a list of messages to the console.
	 * 
	 * @param messages
	 *        the messages
	 * @param level
	 *        the level
	 * @see com.gtdclan.gtdsmp.gtdsmpUtil#console(String) console(String)
	 */
	public void console(String[] messages, Level level) {
		for (String Message : messages) {
			this.console(Message, level);
		}
	}
	
	public void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		
		FileChannel source = null;
		FileChannel destination = null;
		
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public Boolean hasAmount(String playerName) {
		Player player = this.plugin.getServer().getPlayerExact(playerName);
		PlayerInventory playerInv = player.getInventory();
		ItemStack[] playerItems = player.getInventory().getContents();
		Material feeItem = Material.getMaterial(this.plugin.warpItem);
		Integer feeAmount = this.plugin.warpAmount;
		ItemStack fee = new ItemStack(feeItem, feeAmount);
		Integer has = 0;
		for (ItemStack item : playerItems) {
			if (item != null && item.getType() == feeItem) {
				has += item.getAmount();
			}
		}
		if (has >= feeAmount) {
			playerInv.removeItem(fee);
			player.updateInventory();
			return true;
		}
		return false;
	}
	
	public Boolean noCost(String playerName) {
		Player player = this.plugin.getServer().getPlayerExact(playerName);
		boolean hasPerm = player.hasPermission("itemstowarp.warp.nocost");
		int gamemode = player.getGameMode().getValue();
		if (hasPerm || (this.plugin.freeCreative & gamemode == 1)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// @formatter:off
	/**
	 * Parses chat colors and replaces it with the correct output value. <br/>
	 * <br/> Type a slash (/) in from of the caret (^) to display a literal
	 * caret (^). <br/> Color conversions are as follows: <br/> <table style=
	 * "border: 2px solid black; background-color: #222; color: #fff; text-align:center;"
	 * > <tr><th>Input</th><th>Final Color</th><th>Raw Code</th></tr>
	 * <tr><td>^black</td><td
	 * style="background-color: #000"></td><td>&0</td></tr>
	 * <tr><td>^darkblue</td><td
	 * style="background-color: #00006E"></td><td>&1</td></tr>
	 * <tr><td>^darkgreen</td><td
	 * style="background-color: #008000"></td><td>&2</td></tr>
	 * <tr><td>^darkaqua</td><td
	 * style="background-color: #43BFC7"></td><td>&3</td></tr>
	 * <tr><td>^darkred</td><td
	 * style="background-color: #9B0000"></td><td>&4</td></tr>
	 * <tr><td>^darkpurple</td><td
	 * style="background-color: #800080"></td><td>&5</td></tr>
	 * <tr><td>^gold</td><td
	 * style="background-color: #E09C14"></td><td>&6</td></tr>
	 * <tr><td>^gray</td><td
	 * style="background-color: #808080"></td><td>&7</td></tr>
	 * <tr><td>^darkgray</td><td
	 * style="background-color: #404040"></td><td>&8</td></tr>
	 * <tr><td>^blue</td><td
	 * style="background-color: #3F66FF"></td><td>&9</td></tr>
	 * <tr><td>^green</td><td
	 * style="background-color: #8AFF6D"></td><td>&a</td></tr>
	 * <tr><td>^aqua</td><td
	 * style="background-color: #9AFEFF"></td><td>&b</td></tr>
	 * <tr><td>^red</td><td
	 * style="background-color: #D1564E"></td><td>&c</td></tr>
	 * <tr><td>^lightpurple</td><td
	 * style="background-color: #DE6CFF"></td><td>&d</td></tr>
	 * <tr><td>^yellow</td><td
	 * style="background-color: #FFEF63"></td><td>&e</td></tr>
	 * <tr><td>^white</td><td
	 * style="background-color: #FFF; color: #000">default</td><td>&f</td></tr>
	 * </table>
	 * 
	 * @param message the message
	 * @return The new chat/broadcast friendly colored message.
	 */
	// @formatter:on
	public String parseColors(String message) {
		for (ChatColor Color : ChatColor.values()) {
			String ColorName = Color.name().toLowerCase().replace("_", "");
			if (ColorName.equals("magic")) {
				continue;
			}
			message = message.replace("^" + ColorName, Color.toString()).replace("/" + Color.toString(), "^" + ColorName);
		}
		return message;
	}
	
	public String[] parseColors(String[] messages) {
		int amount = messages.length;
		String[] newmessages = new String[amount];
		for (int i = 0; i < amount; i++) {
			
			newmessages[i] = this.parseColors(messages[i]);
		}
		return newmessages;
	}
}
