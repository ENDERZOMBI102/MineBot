package com.enderzombi102.minebot.api;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.dv8tion.jda.api.entities.Message;

public interface CommandManager extends Manager {

	void Execute(Message source, String command);
	void RegisterCommand(LiteralArgumentBuilder<Message> literal);
}
