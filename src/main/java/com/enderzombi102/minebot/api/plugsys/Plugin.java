package com.enderzombi102.minebot.api.plugsys;

import com.enderzombi102.minebot.api.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public interface Plugin {

	void onLoad();
	void onUnload();
	List<CommandProvider> getProviders();

	/**
	 * @return The logger for this plugin (uses class name)
	 */
	default Logger getLogger() {
		return LogManager.getLogger( this.getClass().getSimpleName() );
	}


}
