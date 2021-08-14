package com.enderzombi102.minebot.api;

import net.dv8tion.jda.api.JDA;
import org.jetbrains.annotations.Nullable;

public interface MineBot {

	/**
	 * Get the bot singleton
	 */
	static MineBot getInstance() {
		return com.enderzombi102.minebot.MineBot.getInstance();
	}

	/**
	 * Getter for the JDA singleton.
	 */
	JDA getJda();

	/**
	 * Getter for registered managers
	 * @param name name of the managers
	 * @param <T> class of the manager
	 * @return the requested manager instance, or null if not found
	 */
	<T extends Manager> @Nullable T getManager(String name);


}
