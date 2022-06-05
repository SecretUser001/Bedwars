package bkcraft.bedwars.game.shop.items.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;

public class DiamondArmorBWI implements PermanentBedwarsItem {

    public static ItemStack item = new ItemStack(Material.DIAMOND_BOOTS, 1);
    public static String displayName = "Permanent Diamond Armor";
    public static Category category = Category.Armor;
    public static String description = "";
    public static Currency cost = new Currency(0, 0, 0, 6);

    public DiamondArmorBWI() {
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(displayName);
	item.setItemMeta(meta);
    }
    
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
    public void respawn(Player player) {
	player.getInventory().getBoots().setType(Material.DIAMOND_BOOTS);
	player.getInventory().getLeggings().setType(Material.DIAMOND_LEGGINGS);
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
