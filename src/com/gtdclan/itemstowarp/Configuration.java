package com.gtdclan.itemstowarp;

import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {
	
	/** The plugin. */
	private Main plugin;
	
	/**
	 * Hooks into the plugin.
	 * 
	 * @param instance
	 *        The plugin instance.
	 */
	public Configuration(Main instance) {
		plugin = instance;
	}
	
	/** The server configuration. */
	FileConfiguration PluginConfig;
	
	/**
	 * Loads the plugin config file.
	 */
	public void LoadConfig() {
		plugin.reloadConfig();
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		this.PluginConfig = plugin.getConfig();
		plugin.Util.console("Loaded plugin config file.", Level.CONFIG);
	}
	
	/**
	 * Gets the prop.
	 * 
	 * @param key
	 *            the key
	 * @return the prop
	 */
	public Object getProp(String key) {
		return (Object) PluginConfig.get(key);
	}
	
	public void setProp(String key, Object value) {
		PluginConfig.set(key, value);
		plugin.saveConfig();
		
	}
}
