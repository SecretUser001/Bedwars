package bkcraft.bedwars.events;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.GUI.GUI;

public class VillagerHandler implements Listener {

    @EventHandler
    public void onVillagerClick(PlayerInteractEntityEvent event) {
	if (Main.plugin.game.teamManager.playerData.containsKey(event.getPlayer())
		&& event.getRightClicked() instanceof Villager) {
	    GUI.open(event.getPlayer(), Category.Blocks);
	    event.setCancelled(true);
	}
    }

}
