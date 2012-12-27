package com.gtdclan.itemstowarp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public Commands Commands = new Commands(this);
	public Configuration Config = new Configuration(this);
	public UtilDatabase database;
	public Listeners Listener = new Listeners(this);
	public Boolean signProtected, cords, worlds, freeCreative;
	public ImportUtil ImportUtil = new ImportUtil(this);
	public Util Util = new Util(this);
	public Integer warpAmount, warpItem, updatetool;
	public Warps Warps = new Warps(this);
	public FileConfiguration yml_config;
	public File yml_configFile;
	public String publicText, privateText;
	
	private void initializeDatabase() {
		// Configuration config = Config.LoadConfig();
		this.database = new UtilDatabase(this) {
			
			@Override
			protected java.util.List<Class<?>> getDatabaseClasses() {
				List<Class<?>> list = new ArrayList<Class<?>>();
				list.add(DB.class);
				return list;
			};
		};
		this.database.initializeDatabase(
		    (String) this.Config.getProp("DB.driver"),
		    (String) this.Config.getProp("DB.url"),
		    (String) this.Config.getProp("DB.username"),
		    (String) this.Config.getProp("DB.password"),
		    (String) this.Config.getProp("DB.isolation"),
		    (Boolean) this.Config.getProp("DB.logging"),
		    (Boolean) this.Config.getProp("DB.rebuildDB"));
		
		this.Config.setProp("DB.rebuildDB", false);
	}
	
	@Override
	public void onDisable() {
		this.Util.console("Plugin has been disabled.", Level.INFO);
	}
	
	@Override
	public void onEnable() {
		// Register events.
		PluginManager PluginManager = this.getServer().getPluginManager();
		PluginManager.registerEvents(this.Listener, this);
		
		// Register commands.
		this.getCommand("itw").setExecutor(this.Commands);
		
		// Load Config
		this.Config.LoadConfig();
		this.warpAmount = this.getConfig().getInt("currency.amount");
		this.warpItem = this.getConfig().getInt("currency.item");
		this.signProtected = this.getConfig().getBoolean("sign.protected");
		this.cords = this.getConfig().getBoolean("show.cords");
		this.worlds = this.getConfig().getBoolean("show.world");
		this.freeCreative = this.getConfig().getBoolean("currency.freeforcreative");
		this.updatetool = this.getConfig().getInt("sign.updatetool");
		this.publicText = this.getConfig().getString("sign.text.public", "");
		this.privateText = this.getConfig().getString("sign.text.private", "Private");
		
		// removes old config setting and rebuilds database for new version
		if (this.getConfig().get("DB.rebuild") != null) {
			this.getConfig().set("DB.rebuild", null);
			this.saveConfig();
			this.Config.setProp("DB.rebuildDB", true);
			File original = new File(this.getDataFolder() + "/" + this.getName() + ".db");
			File backup = new File(this.getDataFolder() + "/" + this.getName() + "-BACKUP.db");
			try {
				this.Util.copyFile(original, backup);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		// Setup database
		// this.Warps.beforeUpgrade(); future use
		this.initializeDatabase();
		// this.Warps.afterUpgrade(); future use
		this.Util.console("Plugin has been enabled.", Level.INFO);
	}
}
