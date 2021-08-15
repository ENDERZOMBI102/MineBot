package com.enderzombi102.exampleplugin;

import com.enderzombi102.minebot.api.command.CommandManager;
import com.enderzombi102.minebot.api.command.CommandTypes;
import com.enderzombi102.minebot.api.MineBot;
import com.enderzombi102.minebot.api.exception.CommandAlreadyRegisteredException;
import com.enderzombi102.minebot.api.plugsys.Plugin;
import com.mojang.brigadier.arguments.StringArgumentType;
import org.apache.logging.log4j.Logger;

public class Main implements Plugin {
	@Override
	public void onLoad() {
		Logger logger = getLogger();
		CommandManager comManager = MineBot.getInstance().getManager("command");
		assert comManager != null;
		try {
			comManager.RegisterCommand(
					CommandTypes.literal("say").then(
							CommandTypes.argument("string", StringArgumentType.string()).executes(
									ctx -> {
										ctx.getSource().getChannel().sendMessage(
												ctx.getArgument("string", String.class)
										).queue();
										return 0;
									}
							)
					)
			);
		} catch (CommandAlreadyRegisteredException e) {
			logger.error("Failed to register commands!", e);
		}
	}

	@Override
	public void onUnload() {

	}
}
