package com.gtdclan.itemstowarp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ImportUtil {
	
	private final Main plugin;
	
	public ImportUtil(Main instance) {
		this.plugin = instance;
	}
	
	public void doImport(String playerName, String pluginName) {
		Integer count = 0;
		Integer renamed = 0;
		
		this.logImport("---------------", playerName);
		this.logImport("Starting Import Process - " + playerName, playerName);
		this.logImport("", playerName);
		
		if (pluginName.equals("EcoWarp")) {
			
			File warpDir = new File("plugins/EcoWarp/Warps");
			if (warpDir.exists() && warpDir.isDirectory()) {
				
				for (File warp : warpDir.listFiles()) {
					String[] fileInfo = warp.getName().split("\\.");
					if (fileInfo[1].equalsIgnoreCase("yml")) {
						FileConfiguration warpConf = YamlConfiguration.loadConfiguration(warp);
						DB newWarp = new DB();
						String warpName = warpConf.getString("Name");
						
						if (this.plugin.Warps.warpExists(warpConf.getString("Name"))) {
							this.logImport("RENAMED WARP - " + warpName + " to " + warpName + "import", playerName);
							warpName += "import";
							renamed++;
							
						}
						newWarp.setWarpname(warpName);
						newWarp.setWarpworld((warpConf.getString("World")));
						newWarp.setWarpx(Integer.parseInt(warpConf.getString("X")));
						newWarp.setWarpy(Integer.parseInt(warpConf.getString("Y")));
						newWarp.setWarpz(Integer.parseInt(warpConf.getString("Z")));
						newWarp.setWarppitch(Float.parseFloat(warpConf.getString("Pitch")));
						newWarp.setWarpyaw(Float.parseFloat(warpConf.getString("Yaw")));
						newWarp.setPlayername((warpConf.getString("Creator")));
						newWarp.setIsprivate(false);
						this.plugin.database.getDatabase().save(newWarp);
						newWarp = null;
						this.logImport("Imported " + warpName, playerName);
						count++;
					}
				}
			}
			else {
				this.logImport(this.plugin.Util.parseColors("^red Can't find Warps folder"), playerName);
			}
			
		}
		this.logImport("", playerName);
		this.logImport("Renamed Total: " + renamed + " warps", playerName);
		this.logImport("Import Total: " + count + " warps", playerName);
		this.logImport("", playerName);
		this.logImport("End Import Process", playerName);
		this.logImport("log saved to " + this.plugin.getDataFolder() + "/import_log.txt", playerName);
		this.logImport("---------------", playerName);
	}
	
	public void logImport(String message, String playerName) {
		Player player = this.plugin.getServer().getPlayerExact(playerName);
		this.plugin.Util.console(message, Level.INFO);
		player.sendMessage(message);
		FileWriter fWriter = null;
		BufferedWriter bufWriter = null;
		File wFile = null;
		String path = this.plugin.getDataFolder() + "/import_log.txt";
		try
		{
			wFile = new File(path);
			fWriter = new FileWriter(wFile, true);
			bufWriter = new BufferedWriter(fWriter);
			bufWriter.write(message);
			bufWriter.newLine();
		}
		catch (IOException e)
		{
			this.plugin.Util.console("Error writing to log.", Level.SEVERE);
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fWriter.flush();
				bufWriter.flush();
				fWriter.close();
				bufWriter.close();
			}
			catch (IOException ioe)
			{
				this.plugin.Util.console("Error writing to log.", Level.SEVERE);
				ioe.printStackTrace();
			}
		}
		
	}
}
