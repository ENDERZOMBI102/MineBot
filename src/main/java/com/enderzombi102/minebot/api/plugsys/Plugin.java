package com.enderzombi102.minebot.api.plugsys;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Plugin {

	void onLoad();
	void onUnload();

	/**
	 * @return The logger for this plugin (uses class name)
	 */
	default Logger getLogger() {
		return LogManager.getLogger( this.getClass().getSimpleName() );
	}


}
