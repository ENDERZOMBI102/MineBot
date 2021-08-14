package com.enderzombi102.minebot;

import com.enderzombi102.minebot.api.Manager;
import com.enderzombi102.minebot.command.CommandManager;
import com.enderzombi102.minebot.manager.MessageManager;
import com.enderzombi102.minebot.util.Constants;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public final class MineBot implements com.enderzombi102.minebot.api.MineBot {

	private JDA jda;
	private static MineBot instance;
	private final Logger logger;
	private final HashMap<String, Manager> managers = new HashMap<>() {{
		put( "command", new CommandManager() );
		put( "message", new MessageManager() );
	}};

	public static void main(String[] argv) {
		instance = new MineBot(argv);
		try {
			instance.start();
		} catch (RuntimeException ignored) { }
	}

	public static MineBot getInstance() {
		return instance;
	}

	public MineBot(String[] argv) {
		try {
			Files.deleteIfExists( Path.of("./logs/latest.log" ) );
		} catch (IOException ignored) { }
		logger = LogManager.getLogger("MineBot");
	}

	@Override
	public void start() {
		logger.info("Starting MineBot v{}!", Constants.version);
		String token;
		if ( ( token = System.getenv("TOKEN") ) == null ) {
			logger.warn("The \"TOKEN\" environment variable is not defined! Using stdin as input!");
			Config.useStdin = true;
			throw new RuntimeException();
		}
		logger.info("Initializing JDA object..");
		try {
			this.jda = JDABuilder.createLight(token)
					.setEventManager( new AnnotatedEventManager() )
					.setActivity(
							Activity.of(Activity.ActivityType.DEFAULT, "Minecraft 1.17.1")
					)
					.build();
		} catch (LoginException e) {
			logger.fatal("Failed to initialize JDA object!", e);
			throw new RuntimeException();
		}
		logger.info("Initialized JDA object!");
		logger.info("Initializing managers..");
		for ( Manager manager : managers.values() ) {
			try {
				manager.init(jda);
			} catch (Exception e) {
				logger.error("Failed to initialize {}", manager.getClass().getSimpleName() );
			}
		}
		logger.info("Initialized managers!");
	}

	@Override
	public JDA getJda() {
		return jda;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Manager> T getManager(String name) {
		return (T) managers.get(name);
	}

}