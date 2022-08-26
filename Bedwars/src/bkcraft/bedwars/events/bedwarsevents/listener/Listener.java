package bkcraft.bedwars.events.bedwarsevents.listener;

import bkcraft.bedwars.events.bedwarsevents.events.Event;
import bkcraft.bedwars.events.bedwarsevents.events.EventType;

public interface Listener {
    
    public boolean onEvent(Event event);
    
    public EventType getEventType();
    
}
