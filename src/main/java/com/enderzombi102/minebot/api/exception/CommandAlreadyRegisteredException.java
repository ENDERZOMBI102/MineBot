package com.enderzombi102.minebot.api.exception;

public class CommandAlreadyRegisteredException extends Exception {
	public CommandAlreadyRegisteredException(String msg) {
		super(msg);
	}

	public CommandAlreadyRegisteredException(Throwable e) {
		super(e);
	}
}
