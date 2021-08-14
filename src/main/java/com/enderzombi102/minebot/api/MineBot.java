package com.enderzombi102.minebot.api;

import net.dv8tion.jda.api.JDA;

public interface MineBot {

	static MineBot getInstance() {
		return com.enderzombi102.minebot.MineBot.getInstance();
	}
	// api methods
	void start();
	JDA getJda();
	<T extends Manager> T getManager(String name);


}
