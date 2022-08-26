package bkcraft.bedwars.game.shop.items.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class MagicMilkBWI implements BedwarsItem, Listener {

    public static ItemStack item = new ItemStack(Material.MILK_BUCKET, 1);
    public static String displayName = "Magic Milk";
    public static Category category = Category.Utils;
    public static String description = "Avoid triggering traps 30 seconds after consuming.";
    public static Currency cost = new Currency(0, 4, 0, 0);

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
    public void onMilkComsume(PlayerItemConsumeEvent event) {
	if(event.getItem().getType() == Material.MILK_BUCKET) {
	    event.getPlayer().getInventory().remove(event.getItem());
	}
    }
    
    public MagicMilkBWI clone() {
	return new MagicMilkBWI();
    }
}
