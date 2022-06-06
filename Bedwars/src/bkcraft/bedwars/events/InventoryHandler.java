package bkcraft.bedwars.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.GUI.GUI;

public class InventoryHandler implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
	GUI.openShops.remove(event.getPlayer());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
	if (GUI.openShops.contains((Player) event.getWhoClicked())) {
	    Player player = (Player) event.getWhoClicked();
	    event.setCancelled(true);
	    if (!(event.getInventory().getItem(event.getRawSlot()).getType() == Material.AIR)) {
		event.getWhoClicked().getWorld().playSound(event.getWhoClicked().getLocation(), Sound.ORB_PICKUP, .5f, 1f);
		if (event.getSlot() < 9) {
		    GUI.open(player, Category
			    .valueOf(event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()));
		} else if (GUI.itemSlots.contains(event.getSlot())) {
		    GUI.slotNumbers.get(GUI.openCategory.get(player)).get(event.getSlot()).clicked(player);
		}
	    }
	} else if (event.getSlotType() == SlotType.ARMOR) {
	    event.setCancelled(true);
	}
    }
}
