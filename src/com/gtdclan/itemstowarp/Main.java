package com.gtdclan.itemstowarp;

import java.io.File;
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
	public Boolean signProtected;
	public Util Util = new Util(this);
	public Integer warpAmount;
	public Integer warpItem;
	public Warps Warps = new Warps(this);
	public FileConfiguration yml_config;
	public File yml_configFile;
	
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
		    (Boolean) this.Config.getProp("DB.rebuild"));
		
		this.Config.setProp("DB.rebuild", false);
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
		
		// Setup database
		this.initializeDatabase();
		this.Util.console("Plugin has been enabled.", Level.INFO);
	}
}
