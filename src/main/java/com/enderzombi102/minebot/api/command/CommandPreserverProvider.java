package com.enderzombi102.minebot.api.command;

import com.enderzombi102.minebot.api.exception.CommandAlreadyRegisteredException;

/**
 * Class used to register commands without replacing existing ones.
 * if a command with the provided name is already present, the @method register will not be called.
 */
public abstract class CommandPreserverProvider implements CommandProvider {

	private final String name;

	/**
	 * Constructor used to set the command's name
	 * @param name command name
	 */
	public CommandPreserverProvider(final String name) {
		this.name = name;
	}

	/**
	 * Use this constructor if you want to automatically generate the command name
	 */
	public CommandPreserverProvider() {
		this.name = getClass()
				.getSimpleName()
				.replaceFirst("Command", "")
				.toLowerCase();
	}

	@Override
	public void onRegister(final CommandManager manager) {
		if ( manager.HasCommand(name) ) return;
		try {
			register(manager);
		} catch (CommandAlreadyRegisteredException ignored) { }
	}

	/**
	 * Called if no command with the provided name is found.
	 * @param manager current {@link CommandManager} instance.
	 * @throws CommandAlreadyRegisteredException threw if the command is already registered (unused)
	 */
	protected abstract void register(CommandManager manager) throws CommandAlreadyRegisteredException;
}
