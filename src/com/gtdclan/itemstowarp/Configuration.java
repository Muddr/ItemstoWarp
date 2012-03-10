package com.gtdclan.itemstowarp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Configuration {
	
	private final Main plugin;
	
	public Configuration(Main instance) {
		plugin = instance;
	}
	
	/*
	 * this copy(); method copies the specified file from your jar to your /plugins/<pluginName>/ folder
	 */
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void firstRun() {
		if (!plugin.yml_configFile.exists()) {                        // checks if the yaml does not exists
			plugin.yml_configFile.getParentFile().mkdirs();         // creates the /plugins/<pluginName>/ directory if not found
			copy(plugin.getResource("config.yml"), plugin.yml_configFile); // copies the yaml from your jar to the folder /plugin/<pluginName>
		}
	}
	
	/*
	 * in here, each of the FileConfigurations loaded the contents of yamls found at the /plugins/<pluginName>/*yml. needed at onEnable() after using firstRun(); can be called
	 * anywhere if you need to reload the yamls.
	 */
	public void loadYamls() {
		try {
			plugin.yml_config.load(plugin.yml_configFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
