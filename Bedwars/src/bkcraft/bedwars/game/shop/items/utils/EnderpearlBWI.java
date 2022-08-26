package bkcraft.bedwars.game.shop.items.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class EnderpearlBWI implements BedwarsItem {

    public static ItemStack item = new ItemStack(Material.WATER_BUCKET, 1);
    public static String displayName = "Water Bucket";
    public static Category category = Category.Utils;
    public static String description = "Great to slow down approaching enemies. Can also protect against TNT.";
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
    
    public EnderpearlBWI clone() {
	return new EnderpearlBWI();
    }
}
