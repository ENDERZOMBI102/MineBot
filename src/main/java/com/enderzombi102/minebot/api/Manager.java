package com.enderzombi102.minebot.api;

import net.dv8tion.jda.api.JDA;

public interface Manager {
	void init(JDA jda);
	void stop();
}
