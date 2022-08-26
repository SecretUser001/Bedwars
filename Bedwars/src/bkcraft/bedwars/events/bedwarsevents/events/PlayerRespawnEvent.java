package bkcraft.bedwars.events.bedwarsevents.events;

import org.bukkit.entity.Player;

public class PlayerRespawnEvent implements Event {

    private Player player;

    public PlayerRespawnEvent(Player player) {
	this.player = player;
    }

    @Override
    public void setCancelled(boolean cancel) {}

    @Override
    public boolean isCancelled() {
	return false;
    }

    public EventType getEventType() {
	return EventType.PLAYER_RESPAWN_EVENT;
    }

    public Player getPlayer() {
	return this.player;
    }
}
