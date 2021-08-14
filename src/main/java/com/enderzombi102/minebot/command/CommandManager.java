package com.enderzombi102.minebot.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class CommandManager implements com.enderzombi102.minebot.api.CommandManager {

	private final CommandDispatcher<Message> dispatcher = new CommandDispatcher<>();
	private final Logger logger = LogManager.getLogger("CommandManager");

	@Override
	public void init(@NotNull JDA jda) {

	}

	@Override
	public void stop() {

	}

	@Override
	public void Execute(Message source, String command) {
		try {
			dispatcher.execute(command, source);
		} catch (CommandSyntaxException e) {
			logger.error(e);
		}
	}

	@Override
	public void RegisterCommand(LiteralArgumentBuilder<Message> literal) {
		dispatcher.register(literal);
	}


}
