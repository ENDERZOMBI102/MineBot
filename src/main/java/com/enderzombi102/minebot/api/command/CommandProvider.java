package com.enderzombi102.minebot.api.command;

@FunctionalInterface
public interface CommandProvider {
	/**
	 * Called to register the command.
	 * @param manager Current {@link CommandManager} instance.
	 */
	void onRegister(CommandManager manager);
}
