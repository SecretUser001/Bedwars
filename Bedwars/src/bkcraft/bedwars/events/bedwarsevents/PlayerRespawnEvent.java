package bkcraft.bedwars.events.bedwarsevents;

import org.bukkit.entity.Player;

import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class PlayerRespawnEvent implements Event {

    private boolean cancelled;

    private Player player;
    private BedwarsItem item;

    public PlayerRespawnEvent(Player player) {
	this.cancelled = false;

	this.player = player;
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
