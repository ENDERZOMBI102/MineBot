package com.enderzombi102.minebot.plugsys;

import com.enderzombi102.minebot.api.exception.PluginException;
import net.dv8tion.jda.api.JDA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;

public class PluginManager implements com.enderzombi102.minebot.api.plugsys.PluginManager {

	private PluginClassLoader classLoader;
	private final ArrayList<LoadedPlugin> plugins = new ArrayList<>();
	private final Logger logger = LogManager.getLogger("PluginManager");

	@Override
	public void init(@NotNull JDA jda) {
		classLoader = new PluginClassLoader();
	}

	@Override
	public void stop() {

	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void loadPlugins(Path folder) {
		logger.info("Loading plugins..");
		folder.toFile().mkdir();
		File[] files = folder.toFile().listFiles( pathname -> pathname.getName().endsWith(".jar") );
		assert files != null;
		for ( File file : files ) {
			try {
				LoadPlugin(file);
			} catch (PluginException e) {
				logger.error("Error while loading plugin at " + file.getAbsolutePath(), e);
			}
		}
		for ( LoadedPlugin plugin : plugins ) {
			plugin.load();
		}
		logger.info( "Loaded {} plugins!", plugins.size() );
	}

	@Override
	public void LoadPlugin(File file) throws PluginException {
		try {
			logger.info( "Loading {}..", file.getPath() );
			LoadedPlugin plugin = new LoadedPlugin( classLoader, file );
			plugins.add( plugin );
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new PluginException(e);
		}

	}

	@Override
	public com.enderzombi102.minebot.api.plugsys.LoadedPlugin[] GetPlugins() {
		return plugins.toArray(com.enderzombi102.minebot.api.plugsys.LoadedPlugin[]::new);
	}


	static class PluginClassLoader extends URLClassLoader {

		public PluginClassLoader() {
			super( new URL[]{}, PluginClassLoader.class.getClassLoader() );
		}

		public void addUrl(URL url) {
			super.addURL(url);
		}

	}

}
