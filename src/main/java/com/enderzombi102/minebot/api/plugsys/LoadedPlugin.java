package com.enderzombi102.minebot.api.plugsys;

import com.enderzombi102.minebot.api.command.CommandProvider;

import java.nio.file.Path;
import java.util.List;

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

	/**
	 * Getter for the plugin's `CommandProvider`s
	 */
	List<CommandProvider> GetCommandProviders();
}
