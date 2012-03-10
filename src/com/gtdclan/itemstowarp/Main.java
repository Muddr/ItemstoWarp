package com.gtdclan.itemstowarp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
		database = new UtilDatabase(this) {
			
			@Override
			protected java.util.List<Class<?>> getDatabaseClasses() {
				List<Class<?>> list = new ArrayList<Class<?>>();
				list.add(DB.class);
				return list;
			};
		};
		database.initializeDatabase(
		    getConfig().getString("DB.driver"),
		    getConfig().getString("DB.url"),
		    getConfig().getString("DB.username"),
		    getConfig().getString("DB.password"),
		    getConfig().getString("DB.isolation"),
		    getConfig().getBoolean("DB.logging"),
		    getConfig().getBoolean("DB.rebuild"));
	}
	
	@Override
	public void onDisable() {
		Util.console("Plugin has been disabled.", Level.INFO);
	}
	
	@Override
	public void onEnable() {
		// Register events.
		PluginManager PluginManager = getServer().getPluginManager();
		PluginManager.registerEvents(Listener, this);
		
		// Register commands.
		getCommand("itw").setExecutor(Commands);
		
		// Load config
		yml_configFile = new File(getDataFolder(), "config.yml");
		yml_config = new YamlConfiguration();
		Config.firstRun();
		Config.loadYamls();
		
		// Load settings from config
		warpAmount = getConfig().getInt("currency.amount");
		warpItem = getConfig().getInt("currency.item");
		signProtected = getConfig().getBoolean("sign.protected");
		
		// Setup database
		initializeDatabase();
		Util.console("Plugin has been enabled.", Level.INFO);
	}
}
