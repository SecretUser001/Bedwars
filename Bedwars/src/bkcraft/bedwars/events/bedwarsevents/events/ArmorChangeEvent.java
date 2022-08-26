package bkcraft.bedwars.events.bedwarsevents.events;

import org.bukkit.entity.Player;

import bkcraft.bedwars.game.shop.items.armor.Armor;

public class ArmorChangeEvent implements Event {

    private boolean cancelled;

    private Player player;

    Armor replaced;
    Armor replacement;
    
    public ArmorChangeEvent(Player player, Armor replaced, Armor replacement) {
	this.cancelled = false;

	this.player = player;
	
	this.replaced = replaced;
	this.replacement = replacement;
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
	return EventType.ARMOR_CHANGE_EVENT;
    }

    public Player getPlayer() {
	return this.player;
    }

    public Armor getReplaced() {
	return this.replaced;
    }

    public Armor getReplacement() {
	return this.replacement;
    }

}