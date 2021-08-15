package com.enderzombi102.minebot.api.event;

import com.enderzombi102.minebot.api.Manager;

public interface EventManager extends Manager {

	void invoke(Event evt);

	void register(EventListener listener);



}
