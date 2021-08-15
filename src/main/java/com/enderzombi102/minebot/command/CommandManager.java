package com.enderzombi102.minebot.command;

import com.enderzombi102.minebot.api.exception.CommandAlreadyRegisteredException;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandManager implements com.enderzombi102.minebot.api.command.CommandManager {

	private final CommandDispatcher<Message> dispatcher = new CommandDispatcher<>();
	private final Logger logger = LogManager.getLogger("CommandManager");

	@Override
	public void init(@NotNull JDA jda) { }

	@Override
	public void stop() { }

	public void initBuiltin() {
		// TODO: better handling
		new SayCommand().onRegister(this);
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
	public void RegisterCommand(LiteralArgumentBuilder<Message> literal) throws CommandAlreadyRegisteredException {
		if ( dispatcher.findNode( List.of( literal.getLiteral() ) ) == null) {
			dispatcher.register(literal);
		} else {
			throw new CommandAlreadyRegisteredException( literal.getLiteral() + " is already registered!" );
		}
	}

	@Override
	public boolean TryRegisterCommand(LiteralArgumentBuilder<Message> literal) {
		try {
			RegisterCommand(literal);
			return true;
		} catch (CommandAlreadyRegisteredException e) {
			return false;
		}
	}

	@Override
	public boolean HasCommand(String path) {
		return HasCommand( List.of( path ) );
	}

	@Override
	public boolean HasCommand(List<String> path) {
		return dispatcher.findNode( path ) == null;
	}


}
