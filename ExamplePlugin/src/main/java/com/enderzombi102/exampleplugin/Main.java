package com.enderzombi102.exampleplugin;

import com.enderzombi102.minebot.api.CommandManager;
import com.enderzombi102.minebot.api.CommandTypes;
import com.enderzombi102.minebot.api.MineBot;
import com.enderzombi102.minebot.api.plugsys.Plugin;
import com.mojang.brigadier.arguments.StringArgumentType;

public class Main implements Plugin {
	@Override
	public void onLoad() {
		CommandManager comManager = MineBot.getInstance().getManager("command");
		assert comManager != null;
		comManager.RegisterCommand(
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

	@Override
	public void onUnload() {

	}
}
