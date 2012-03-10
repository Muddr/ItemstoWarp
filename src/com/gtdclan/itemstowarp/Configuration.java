package com.gtdclan.itemstowarp;

import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {
	
	/** The plugin. */
	private final Main plugin;
	
	/** The server configuration. */
	FileConfiguration PluginConfig;
	
	/**
	 * Hooks into the plugin.
	 * 
	 * @param instance
	 *        The plugin instance.
	 */
	public Configuration(Main instance) {
		this.plugin = instance;
	}
	
	/**
	 * Gets the prop.
	 * 
	 * @param key
	 *        the key
	 * @return the prop
	 */
	public Object getProp(String key) {
		return this.PluginConfig.get(key);
	}
	
	/**
	 * Loads the plugin config file.
	 */
	public void LoadConfig() {
		this.plugin.reloadConfig();
		this.plugin.getConfig().options().copyDefaults(true);
		this.plugin.saveConfig();
		this.PluginConfig = this.plugin.getConfig();
		this.plugin.Util.console("Loaded plugin config file.", Level.CONFIG);
	}
	
	public void setProp(String key, Object value) {
		this.PluginConfig.set(key, value);
		this.plugin.saveConfig();
		
	}
}
