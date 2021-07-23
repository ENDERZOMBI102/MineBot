package com.enderzombi102.minebot;

import com.enderzombi102.minebot.api.Manager;
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
import java.util.ArrayList;

public final class MineBot implements com.enderzombi102.minebot.api.MineBot {

	private JDA jda;
	private static MineBot instance;
	private final Logger logger;
	private final ArrayList<Manager> managers = new ArrayList<>();

	public static void main(String[] argv) {
		instance = new MineBot(argv);
		try {
			instance.start();
		} catch (RuntimeException e) {
			return;
		}
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

		logger.info("Initialized managers!");
	}

	public JDA getJda() {
		return jda;
	}

}