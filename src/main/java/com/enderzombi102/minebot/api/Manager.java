package com.enderzombi102.minebot.api;

import net.dv8tion.jda.api.JDA;
import org.jetbrains.annotations.NotNull;

public interface Manager {
	void init(@NotNull JDA jda) throws Exception;
	void stop();
}
