package bkcraft.bedwars.events.bedwarsevents;

public interface Event {    
    
    public void setCancelled(boolean cancel);
    
    public boolean isCancelled();
    
    public EventType getEventType();
    
}
