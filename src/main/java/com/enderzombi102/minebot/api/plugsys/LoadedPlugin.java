package com.enderzombi102.minebot.api.plugsys;

import java.nio.file.Path;

public interface LoadedPlugin {

	/**
	 * Getter for the plugin's name
	 */
	String GetName();

	/**
	 * Getter for the plugin's path
	 */
	Path GetPath();

	/**
	 * Getter for the plugin's instance
	 */
	Plugin GetInstance();

	/**
	 * Getter for the plugin's main class full name
	 */
	String GetMainClass();

}
