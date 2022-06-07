package bkcraft.bedwars.game.shop.items.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class SpongeBWI implements BedwarsItem, Listener {

    public static ItemStack item = new ItemStack(Material.SPONGE, 4);
    public static String displayName = "Sponge";
    public static Category category = Category.Utils;
    public static String description = "Great for soaking up water.";
    public static Currency cost = new Currency(0, 6, 0, 0);

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
    public void onBlockPlace(BlockPlaceEvent event) {
	new BukkitRunnable() {
	    
	    @Override
	    public void run() {
		event.getBlock().setType(Material.AIR);
	    }
	}.runTaskLater(Main.plugin, 2);
    }
    
}
