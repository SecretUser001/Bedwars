package bkcraft.bedwars.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import bkcraft.bedwars.Main;

public class JoinHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
	Main.plugin.getGame().addPlayer(e.getPlayer());

	new BukkitRunnable() {
	    
	    @Override
	    public void run() {
		e.getPlayer().sendMessage(ChatColor.YELLOW + "Type " + ChatColor.BOLD + "/game start " + ChatColor.BOLD + "to start the game.");		
	    }
	}.runTaskLater(Main.plugin, 1);
    }

}
