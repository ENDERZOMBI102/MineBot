package com.enderzombi102.minebot.api.exception;

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
