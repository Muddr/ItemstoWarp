package com.gtdclan.itemstowarp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gtdclan.itemstowarp.Configuration;
import com.gtdclan.itemstowarp.DB;
import com.gtdclan.itemstowarp.UtilDatabase;

public class Main extends JavaPlugin {
	
	// TODO: Plugin Desc.
	public UtilDatabase database;
	public Commands Commands = new Commands(this);
	public Listeners Listener = new Listeners(this);
	public Configuration Config = new Configuration(this);
	public Util Util = new Util(this);
	public Warps Warps = new Warps(this);
	public Integer warpAmount;
	public Integer warpItem;
	public Boolean signProtected;
	
	@Override
	public void onEnable() {
		// Register events.
		PluginManager PluginManager = getServer().getPluginManager();
		PluginManager.registerEvents(Listener, this);
		
		// Register commands.
		getCommand("itw").setExecutor(Commands);
		// Load Config
		Config.LoadConfig();
		warpAmount = getConfig().getInt("currency.amount");
		warpItem = getConfig().getInt("currency.item");
		signProtected = getConfig().getBoolean("sign.protected");
		
		// Setup Database
		initializeDatabase();
		Util.console("Plugin has been enabled.", Level.INFO);
	}
	
	public void onDisable() {
		Util.console("Plugin has been disabled.", Level.INFO);
	}
	
	private void initializeDatabase() {
		database = new UtilDatabase(this) {
			
			protected java.util.List<Class<?>> getDatabaseClasses() {
				List<Class<?>> list = new ArrayList<Class<?>>();
				list.add(DB.class);
				return list;
			};
		};
		database.initializeDatabase(
		    (String) Config.getProp("DB.driver"),
		    (String) Config.getProp("DB.url"),
		    (String) Config.getProp("DB.username"),
		    (String) Config.getProp("DB.password"),
		    (String) Config.getProp("DB.isolation"),
		    (Boolean) Config.getProp("DB.logging"),
		    (Boolean) Config.getProp("DB.rebuild"));
		
		Config.setProp("DB.rebuild", false);
	}
}
