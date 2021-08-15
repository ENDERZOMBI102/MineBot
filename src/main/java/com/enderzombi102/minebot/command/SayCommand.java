package com.enderzombi102.minebot.command;

import com.enderzombi102.minebot.api.command.CommandManager;
import com.enderzombi102.minebot.api.command.CommandPreserverProvider;
import com.enderzombi102.minebot.api.command.CommandTypes;
import com.enderzombi102.minebot.api.exception.CommandAlreadyRegisteredException;
import com.mojang.brigadier.arguments.StringArgumentType;

public class SayCommand extends CommandPreserverProvider {

	@Override
	protected void register(CommandManager manager) throws CommandAlreadyRegisteredException {
		manager.RegisterCommand(
				CommandTypes.literal("say").then(
						CommandTypes.argument("string", StringArgumentType.string() ).executes(
								ctx -> {
									ctx.getSource().getChannel().sendMessage(
											ctx.getArgument("string", String.class)
									).queue();
									return 0;
								}
						)
				)
		);
	}
}
