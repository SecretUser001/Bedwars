package bkcraft.bedwars.events.bedwarsevents;

import java.util.ArrayList;
import java.util.HashMap;

import bkcraft.bedwars.events.bedwarsevents.events.Event;
import bkcraft.bedwars.events.bedwarsevents.events.EventType;
import bkcraft.bedwars.events.bedwarsevents.listener.Listener;

public class EventHandler {

    private HashMap<EventType, ArrayList<Listener>> listeners;
    
    public EventHandler() {
	this.listeners = new HashMap<EventType, ArrayList<Listener>>();
    }
    
    public void addListener(Listener listener) {
	if(!listeners.containsKey(listener.getEventType())) {
	    listeners.put(listener.getEventType(), new ArrayList<Listener>());
	}
	
	listeners.get(listener.getEventType()).add(listener);
    }
 
    public boolean call(Event event) {
	if(!listeners.containsKey(event.getEventType())) {
	    listeners.put(event.getEventType(), new ArrayList<Listener>());
	}
	
	ArrayList<Listener> l = listeners.get(event.getEventType());
	
	for(Listener listener : l) {
	    listener.onEvent(event);
	}
	
	return !event.isCancelled();
    }
}
