package com.enderzombi102.minebot.command;

import com.enderzombi102.minebot.api.Manager;
import com.mojang.brigadier.CommandDispatcher;
import net.dv8tion.jda.api.JDA;

public class CommandManager implements Manager {

	private final CommandDispatcher<CommandSource> dispatcher = new CommandDispatcher<>();

	@Override
	public void init(JDA jda) {

	}

	@Override
	public void stop() {

	}

	public void Execute() {

	}


}
