package bkcraft.bedwars.events.bedwarsevents.listener;

import bkcraft.bedwars.events.bedwarsevents.events.Event;
import bkcraft.bedwars.events.bedwarsevents.events.EventType;

public class ShopItemPreprocessListener implements Listener{

    private EventType eventType = EventType.SHOP_ITEM_PREPROCESS_EVENT;
    
    @Override
    public boolean onEvent(Event event) {
	return false;
    }

    @Override
    public EventType getEventType() {
	return eventType;
    }

}
