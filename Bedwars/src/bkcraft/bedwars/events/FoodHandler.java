package bkcraft.bedwars.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import bkcraft.bedwars.Main;

public class FoodHandler implements Listener {

	@EventHandler
	public void onFood(FoodLevelChangeEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (Main.plugin.game.teamManager.playerData.keySet().contains(player)) {
				event.setCancelled(true);
			}
		}
	}
}
