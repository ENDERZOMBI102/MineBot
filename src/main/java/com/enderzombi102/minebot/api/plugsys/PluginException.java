package com.enderzombi102.minebot.api.plugsys;

/**
 * Exception used for plugins
 */
public class PluginException extends Exception {
	public PluginException(String msg) {
		super(msg);
	}

	public PluginException(Throwable e) {
		super(e);
	}
}
