package com.enderzombi102.minebot.api.command;

import com.enderzombi102.minebot.api.Manager;
import com.enderzombi102.minebot.api.exception.CommandAlreadyRegisteredException;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;

public interface CommandManager extends Manager {

	void Execute(Message source, String command);
	void RegisterCommand(LiteralArgumentBuilder<Message> literal) throws CommandAlreadyRegisteredException;
	boolean TryRegisterCommand(LiteralArgumentBuilder<Message> literal);
	boolean HasCommand(String path);
	boolean HasCommand(List<String> path);
}
