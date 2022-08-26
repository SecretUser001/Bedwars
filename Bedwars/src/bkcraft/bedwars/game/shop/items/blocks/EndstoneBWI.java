package bkcraft.bedwars.game.shop.items.blocks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class EndstoneBWI implements BedwarsItem{

    public static ItemStack item = new ItemStack(Material.ENDER_STONE, 12);
    public static String displayName = "Endstone";
    public static Category category = Category.Blocks;
    public static String description = "Solid block to defend your bed.";
    public static Currency cost = new Currency(24, 0, 0, 0);
    
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
    public Currency getCost() {
	return cost;
    }

    @Override
    public String getDescription() {
	return description;
    }
    
    @Override
    public void clicked(Player player) {
	if (Shop.buy(player, this)) {
	    player.getInventory().addItem(item);
	} else {
	    player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), cost));
	}
    }
    
    public EndstoneBWI clone() {
	return new EndstoneBWI();
    }
}
