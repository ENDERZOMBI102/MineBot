package com.enderzombi102.minebot.plugsys;

import com.enderzombi102.minebot.api.plugsys.Plugin;
import com.enderzombi102.minebot.api.exception.PluginException;

import static com.enderzombi102.minebot.plugsys.PluginManager.PluginClassLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.jar.JarFile;

public class LoadedPlugin implements com.enderzombi102.minebot.api.plugsys.LoadedPlugin {

	private final Path pluginJar;
	private final String mainClass;
	private final Plugin instance;
	private final String name;

	@SuppressWarnings({"unchecked", "deprecation"})
	LoadedPlugin(PluginClassLoader loader, File file) throws IOException, ClassNotFoundException,
			PluginException, InstantiationException, IllegalAccessException {
		pluginJar = file.toPath();
		JarFile jarFile = new JarFile(file);
		// get plugin name from jar name
		name = Path.of( jarFile.getName() ).getFileName().toString().replace(".jar", "");
		// get plugin main class from manifest
		mainClass = jarFile.getManifest().getMainAttributes().getValue("PluginClass");
		// add jar to class loader and load class
		loader.addUrl( file.toURI().toURL() );
		Class<?> clazz = loader.loadClass(mainClass);
		// check if implements Plugin
		if (! Plugin.class.isAssignableFrom(clazz) )
			// doesn't implement it
			throw new PluginException("Main class doesn't implement \"" + Plugin.class.getName() + "\"" );
		// it does, instantiate it and finish preliminary loading
		instance = ( ( Class<? extends Plugin > ) clazz ).newInstance();
	}

	public void load() {
		instance.onLoad();
	}

	public void unload() {
		instance.onUnload();
	}

	@Override
	public String GetName() {
		return name;
	}

	@Override
	public Path GetPath() {
		return pluginJar;
	}

	@Override
	public Plugin GetInstance() {
		return instance;
	}

	@Override
	public String GetMainClass() {
		return mainClass;
	}
}
