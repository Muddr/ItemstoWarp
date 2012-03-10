package com.gtdclan.itemstowarp;

import java.util.logging.Level;

public class Configuration {
	
	/** The plugin. */
	private final Main plugin;
	
	/** The server configuration. */
	org.bukkit.configuration.Configuration PluginConfig;
	
	/**
	 * Hooks into the plugin.
	 * 
	 * @param instance
	 *        The plugin instance.
	 */
	public Configuration(Main instance) {
		plugin = instance;
	}
	
	/**
	 * Gets the prop.
	 * 
	 * @param key
	 *        the key
	 * @return the prop
	 */
	public Object getProp(String key) {
		return PluginConfig.get(key);
	}
	
	/**
	 * Loads the plugin config file.
	 */
	public void LoadConfig() {
		// plugin.reloadConfig();
		// plugin.getConfig();
		PluginConfig = plugin.getConfig().getDefaults();
		plugin.getConfig().setDefaults(PluginConfig);
		// plugin.saveConfig();
		
		plugin.Util.console("Loaded plugin config file.", Level.CONFIG);
	}
	
	public void setProp(String key, Object value) {
		PluginConfig.set(key, value);
		plugin.saveConfig();
		
	}
}
