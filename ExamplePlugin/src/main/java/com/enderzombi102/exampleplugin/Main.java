package com.enderzombi102.exampleplugin;

import com.enderzombi102.minebot.api.command.CommandManager;
import com.enderzombi102.minebot.api.command.CommandProvider;
import com.enderzombi102.minebot.api.command.CommandTypes;
import com.enderzombi102.minebot.api.MineBot;
import com.enderzombi102.minebot.api.exception.CommandAlreadyRegisteredException;
import com.enderzombi102.minebot.api.plugsys.Plugin;
import com.mojang.brigadier.arguments.StringArgumentType;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main implements Plugin {

	private final ArrayList<CommandProvider> providers = new ArrayList<>();

	@Override
	public void onLoad() {
		Logger logger = getLogger();
		logger.info("ExamplePlugin loaded!");
		providers.add( manager -> manager.TryRegisterCommand(
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
		));
	}

	@Override
	public void onUnload() {

	}

	@Override
	public List<CommandProvider> getProviders() {
		return providers;
	}
}
