package bkcraft.bedwars.events.bedwarsevents;

import org.bukkit.entity.Player;

import bkcraft.bedwars.game.shop.upgrades.TeamUpgrade;

public class TeamUpgradeEvent implements Event {

    private boolean cancelled;

    private Player player;
    private TeamUpgrade upgrade;

    public TeamUpgradeEvent(Player player, TeamUpgrade upgrade) {
	this.cancelled = false;

	this.player = player;
	this.upgrade = upgrade;
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
	return EventType.TEAM_UPGRADE_EVENT;
    }

    public Player getPlayer() {
	return this.player;
    }

    public TeamUpgrade getUpgradeType() {
	return this.upgrade;
    }

}
