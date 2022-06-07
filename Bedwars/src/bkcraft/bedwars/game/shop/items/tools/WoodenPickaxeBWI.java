package bkcraft.bedwars.game.shop.items.tools;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;

public class WoodenPickaxeBWI implements PermanentBedwarsItem {

    public static ItemStack item = new ItemStack(Material.WOOD_PICKAXE);
    public static String name = "Wooden Pickaxe";
    public static Category category = Category.Tools;
    public static String description = "Permanent Pickaxe";
    public static Currency cost = new Currency(10, 0, 0, 0);
    public static int upgrades = 0;

    public PickaxeBWI pickaxe;

    public WoodenPickaxeBWI(PickaxeBWI pickaxe) {
	this.pickaxe = pickaxe;

	ItemMeta meta = item.getItemMeta();
	meta.addEnchant(Enchantment.DIG_SPEED, 1, false);
	meta.setDisplayName(name);
	item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItem() {
	return item;
    }

    @Override
    public String getName() {
	return name;
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
	if (!Shop.canBuy(player, this)) {
	    player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), getCost()));
	}
	
	Shop.buy(player, this);
	
	Main.plugin.game.teamManager.playerData.get(player).upgradebleItems.put(pickaxe.getItemName(), pickaxe);
	pickaxe.bought = true;
	
	respawn(player);
    }
    
    @Override
    public void respawn(Player player) {
	player.getInventory().addItem(getItem());
    }
}
