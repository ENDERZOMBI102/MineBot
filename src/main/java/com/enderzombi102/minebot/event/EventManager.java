package com.enderzombi102.minebot.event;

import com.enderzombi102.minebot.api.event.Event;
import com.enderzombi102.minebot.api.event.EventListener;
import com.enderzombi102.minebot.api.event.Ignore;
import net.dv8tion.jda.api.JDA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class EventManager implements com.enderzombi102.minebot.api.event.EventManager {

	private static final MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
	// ( evt,( plugin, listeners ) )
	private final HashMap< String, HashMap< String, ArrayList<MethodHandle> > > listeners = new HashMap<>();
	private final Logger logger = LogManager.getLogger("EventManager");

	@Override
	public void init(@NotNull JDA jda) {

	}

	@Override
	public void stop() {

	}

	@Override
	public void invoke(Event evt) {

	}

	@Override
	public void register(EventListener listener) {
		// TODO: FINISH
		for ( Method method : listener.getClass().getMethods() ) {
			// ignore method annotated with @Ignore or that are private/protected
			if ( method.isAnnotationPresent(Ignore.class) || !Modifier.isPublic( method.getModifiers() ) )
				continue;
			if ( method.getReturnType() != Void.class ) {
				logger.warn(
						"Return type for event listener {} ( from \"{}\" ) isn't void, will be skipped.",
						method.getName(),
						method.getDeclaringClass().getName()
				);
				continue;
			}
			Class<?> evtType = method.getParameterTypes()[0];
		}
	}
}
