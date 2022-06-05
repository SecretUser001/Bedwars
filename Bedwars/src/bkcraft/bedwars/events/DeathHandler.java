package bkcraft.bedwars.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import bkcraft.bedwars.Main;

public class DeathHandler implements Listener {

	@EventHandler
	public void onDeath(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (event.getCause().equals(DamageCause.VOID) || player.getHealth() - event.getDamage() <= 0) {
				if (Main.plugin.game.teamManager.playerData.keySet().contains(player)) {
					for (Player p : Main.plugin.game.teamManager.playerData.keySet()) {
						p.sendMessage(Main.plugin.game.teamManager.playerData.get(player).getTeam().getPrefix()
								+ player.getName() + ChatColor.YELLOW + " died.");
					}
					Main.plugin.game.kill(player);
					event.setCancelled(true);
				}
			}
		}
	}
}
