package com.enderzombi102.minebot.api.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * Utility class to better handle brigadier type parameters, prefer to use methods from here.
 */
public final class CommandTypes {
	private CommandTypes() {}

	public static LiteralArgumentBuilder<Message> literal(final String literal) {
		return LiteralArgumentBuilder.literal(literal);
	}

	public static <T> RequiredArgumentBuilder<Message, T> argument(final String name, final ArgumentType<T> type) {
		return RequiredArgumentBuilder.argument( name, type );
	}
}
