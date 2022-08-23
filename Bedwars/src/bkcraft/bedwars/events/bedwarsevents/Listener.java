package bkcraft.bedwars.events.bedwarsevents;

public interface Listener {
    
    public boolean onEvent(Event event);
    
    public EventType getEventType();
    
}
