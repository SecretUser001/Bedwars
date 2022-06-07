package bkcraft.bedwars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import bkcraft.bedwars.Main;

public class JoinHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
	Main.plugin.game.addPlayer(e.getPlayer());
    }

}
