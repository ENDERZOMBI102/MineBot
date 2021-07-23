package com.enderzombi102.minebot.manager;

import com.enderzombi102.minebot.MineBot;
import com.enderzombi102.minebot.api.Manager;
import com.enderzombi102.minebot.command.CommandManager;
import com.enderzombi102.minebot.command.CommandSource;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageManager implements Manager {

	private final Logger logger = LogManager.getLogger("MessageManager");

	public void init(JDA jda) {
		jda.addEventListener(this);
	}

	public void stop() {

	}

	@SubscribeEvent
	public void onMessage(MessageReceivedEvent evt) {
		if ( evt.getMessage().getContentRaw().startsWith("/") ) {
			logger.info(
					"Received message from {}: {}",
					evt.getAuthor().getName(),
					evt.getMessage().getContentRaw()
			);
			MineBot.getInstance()
					.<CommandManager>getManager("command")
					.Execute(
							new CommandSource(evt.getMessage()),
							evt.getMessage().getContentRaw().replaceFirst("/", "")
					);
		}
	}

}
