package bkcraft.bedwars.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.TeamManager;

public class ChatHandler implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
	TeamManager teamManager = Main.plugin.game.teamManager;
	if (teamManager.getPlayer().contains(event.getPlayer())) {
	    for (Player player : teamManager.getTeam(event.getPlayer())) {
		player.sendMessage(teamManager.playerData.get(player).team.getPrefix() + event.getPlayer().getName()
			+ " > " + ChatColor.WHITE + event.getMessage());
	    }
	    event.setCancelled(true);
	}
    }

}
