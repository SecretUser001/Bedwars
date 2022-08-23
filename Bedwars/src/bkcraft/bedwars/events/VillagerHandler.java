package bkcraft.bedwars.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
	if (Main.plugin.getGame().getTeamManager().playerData.containsKey(event.getPlayer())
		&& event.getRightClicked() instanceof Villager) {
	    if (event.getRightClicked().getCustomName().equals(ChatColor.BLUE + "ITEM SHOP")) {
		GUI.openShop(event.getPlayer(), Category.values()[0]);
	    } else if (event.getRightClicked().getCustomName().equals(ChatColor.BLUE + "UPGRADES")) {
		GUI.openUpgrades(event.getPlayer());
	    }
	    event.setCancelled(true);
	}
    }

}
