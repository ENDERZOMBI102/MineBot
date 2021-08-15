package com.enderzombi102.minebot;

import com.enderzombi102.minebot.api.Manager;
import com.enderzombi102.minebot.command.CommandManager;
import com.enderzombi102.minebot.event.EventManager;
import com.enderzombi102.minebot.plugsys.PluginManager;
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
	private boolean initialized = false;
	private final HashMap<String, Manager> managers = new HashMap<>() {{
		put( "command", new CommandManager() );
		put( "message", new MessageManager() );
		put( "plugin", new PluginManager() );
		put( "event", new EventManager() );
	}};

	public static void main(String[] argv) {
		instance = new MineBot();
		try {
			instance.start();
		} catch (RuntimeException ignored) { }
	}

	public static MineBot getInstance() {
		return instance;
	}

	public MineBot() {
		try {
			Files.deleteIfExists( Path.of("./logs/latest.log" ) );
		} catch (IOException ignored) { }
		logger = LogManager.getLogger("MineBot");
	}

	public void start() {
		logger.info("Starting MineBot v{}!", Constants.version);
		logger.info("Registering shutdown hook..");
		Runtime.getRuntime().addShutdownHook(
				new Thread( () -> {
					if (! initialized ) return;
					for (Manager manager : managers.values() ) {
						manager.stop();
					}
					jda.shutdownNow();
				})
		);
		String token;
		if ( ( token = System.getenv("TOKEN") ) == null ) {
			logger.fatal("The \"TOKEN\" environment variable is not defined!");
			System.exit(1);
		}
		logger.info("Initializing JDA..");
		try {
			this.jda = JDABuilder.createLight(token)
					.setEventManager( new AnnotatedEventManager() )
					.setActivity(
							Activity.of(Activity.ActivityType.DEFAULT, "Minecraft 1.17.1")
					)
					.build();
		} catch (LoginException e) {
			logger.fatal("Failed to initialize JDA!", e);
			System.exit(1);
		}
		logger.info("Initialized JDA!");
		logger.info("Initializing managers..");
		for ( Manager manager : managers.values() ) {
			try {
				manager.init(jda);
			} catch (Exception e) {
				logger.error("Failed to initialize {}", manager.getClass().getSimpleName() );
			}
		}
		initialized = true;
		logger.info("Initialized managers!");
		// loading plugins..
		( (PluginManager) getManager("plugin") ).loadPlugins( Path.of("./plugins") );
		// plugins loaded!
		logger.info("Initializing builtin commands..");
		( (CommandManager) getManager("command") ).initBuiltin();
		logger.info("Initialized builtin commands!");
		// TODO: FINISH
		// creating commands..
		//
		// commands created!

		if ( jda.getStatus() != JDA.Status.CONNECTED ) {
			logger.info("Waiting for JDA to finish starting..");
			try {
				jda.awaitReady();
			} catch (InterruptedException | IllegalStateException e) {
				logger.error(e);
			}
		}
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