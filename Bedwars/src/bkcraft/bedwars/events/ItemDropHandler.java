package bkcraft.bedwars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;
import bkcraft.bedwars.game.shop.items.UpgradebleBedwarsItem;

public class ItemDropHandler implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
	for (PermanentBedwarsItem item : Main.plugin.getGame().getTeamManager()
		.getPlayerData(event.getPlayer()).permanentItems) {
	    if (item.getItem().equals(event.getItemDrop().getItemStack())) {
		event.setCancelled(true);
	    }
	}

	for (UpgradebleBedwarsItem item : Main.plugin.getGame().getTeamManager()
		.getPlayerData(event.getPlayer()).upgradebleItems.values()) {
	    if (item.getItem().equals(event.getItemDrop().getItemStack())) {
		event.setCancelled(true);
	    }
	}
    }
}
