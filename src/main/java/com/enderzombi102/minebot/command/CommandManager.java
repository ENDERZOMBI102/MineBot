package com.enderzombi102.minebot.command;

import com.enderzombi102.minebot.api.Manager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.dv8tion.jda.api.JDA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandManager implements Manager {

	private final CommandDispatcher<CommandSource> dispatcher = new CommandDispatcher<>();
	private final Logger logger = LogManager.getLogger("CommandManager");

	@Override
	public void init(JDA jda) {

	}

	@Override
	public void stop() {

	}

	public void Execute(CommandSource source, String command) {
		try {
			dispatcher.execute(command, source);
		} catch (CommandSyntaxException e) {

		}
	}


}
