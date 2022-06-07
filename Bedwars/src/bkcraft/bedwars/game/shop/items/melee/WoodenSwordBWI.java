package bkcraft.bedwars.game.shop.items.melee;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;

public class WoodenSwordBWI implements PermanentBedwarsItem{


    public static ItemStack item = new ItemStack(Material.WOOD_SWORD, 1);
    public static String displayName = "Wooden Sword";
    public static Category category = Category.Melee;
    public static String description = "Permanent Wooden Sword";
    public static Currency cost = new Currency(0, 0, 0, 0);

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
    public void respawn(Player player) {
	player.getInventory().addItem(item);
    }

    @Override
    public void clicked(Player player) {
	if(Main.plugin.game.teamManager.playerData.get(player).permanentItems.contains(this)) {
	    player.sendMessage(Messages.CANT_BUY_ALREADY_PURCHASED);
	    return;
	}
	
	if (Shop.buy(player, this)) {
	    Main.plugin.game.teamManager.playerData.get(player).permanentItems.add(this);
	} else {
	    player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), cost));
	}
    }
    
}
