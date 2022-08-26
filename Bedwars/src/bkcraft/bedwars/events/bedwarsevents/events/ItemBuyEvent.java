package bkcraft.bedwars.events.bedwarsevents.events;

import org.bukkit.entity.Player;

import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class ItemBuyEvent implements Event {

    private boolean cancelled;

    private Player player;
    private BedwarsItem item;

    public ItemBuyEvent(Player player, BedwarsItem item) {
	this.cancelled = false;

	this.player = player;
	this.item = item;
    }

    @Override
    public void setCancelled(boolean cancel) {
	this.cancelled = cancel;
    }

    @Override
    public boolean isCancelled() {
	return this.cancelled;
    }

    public EventType getEventType() {
	return EventType.ITEM_BUY_EVENT;
    }

    public Player getPlayer() {
	return this.player;
    }

    public BedwarsItem getItem() {
	return this.item;
    }

    public void setItem(BedwarsItem item) {
	this.item = item;
    }

}
