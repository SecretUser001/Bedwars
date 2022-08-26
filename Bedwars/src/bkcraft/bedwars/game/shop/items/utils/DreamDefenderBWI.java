package bkcraft.bedwars.game.shop.items.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class DreamDefenderBWI implements BedwarsItem, Listener {

    public static ItemStack item = new ItemStack(Material.MONSTER_EGG, 1);
    public static String displayName = "Dream Defender";
    public static Category category = Category.Utils;
    public static String description = "Iron Golem to help defend your base";
    public static Currency cost = new Currency(120, 0, 0, 0);

    @Override
    public ItemStack getItem() {
	return item;
    }

    @Override
    public String getName() {
	return displayName;
    }

    @Override
    public Category getCategory() {
	return category;
    }

    @Override
    public String getDescription() {
	return description;
    }

    @Override
    public Currency getCost() {
	return cost;
    }

    @Override
    public void clicked(Player player) {
	if (Shop.buy(player, this)) {
	    player.getInventory().addItem(item);
	} else {
	    player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), cost));
	}
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
	if (event.hasItem() && event.getItem().getType() == Material.MONSTER_EGG) {
	    event.setCancelled(true);
	}
    }

    public DreamDefenderBWI clone() {
	return new DreamDefenderBWI();
    }
    
}
