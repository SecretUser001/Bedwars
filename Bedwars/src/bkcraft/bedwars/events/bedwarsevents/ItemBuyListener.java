package bkcraft.bedwars.events.bedwarsevents;

public class ItemBuyListener implements Listener{

    private EventType eventType = EventType.ITEM_BUY_EVENT;
    
    @Override
    public boolean onEvent(Event event) {
	return false;
    }

    @Override
    public EventType getEventType() {
	return eventType;
    }

}
