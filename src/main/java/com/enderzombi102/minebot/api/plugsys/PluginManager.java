package com.enderzombi102.minebot.api.plugsys;

import com.enderzombi102.minebot.api.Manager;
import com.enderzombi102.minebot.api.exception.PluginException;

import java.io.File;
import java.io.IOException;

public interface PluginManager extends Manager {

	/**
	 * Loads a plugin off a jar file
	 * @param file plugin to load
	 */
	void LoadPlugin(File file) throws IOException, PluginException;

	/**
	 * Getter for the array containing the loaded plugins
	 */
	LoadedPlugin[] GetPlugins();



}
