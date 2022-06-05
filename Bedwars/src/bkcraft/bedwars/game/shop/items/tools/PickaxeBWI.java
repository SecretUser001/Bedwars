package bkcraft.bedwars.game.shop.items.tools;

import java.util.ArrayList;
import java.util.Arrays;

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
import bkcraft.bedwars.game.shop.items.UpgradebleBedwarsItem;

public class PickaxeBWI implements UpgradebleBedwarsItem {

    public static ArrayList<ItemStack> item = new ArrayList<ItemStack>(
	    Arrays.asList(new ItemStack(Material.WOOD_PICKAXE), new ItemStack(Material.IRON_PICKAXE),
		    new ItemStack(Material.GOLD_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE)));
    public static ArrayList<String> displayName = new ArrayList<String>(Arrays.asList("Wooden Pickaxe", "Iron Pickaxe", "Gold Pickaxe", "Diamond Pickaxe"));
    public static ArrayList<Integer> efficiencyLvl = new ArrayList<>(Arrays.asList(1, 1, 2, 1));
    public static Category category = Category.Tools;
    public static String description = "Permanent Pickaxe";
    public static ArrayList<Currency> cost = new ArrayList<Currency>(Arrays.asList(new Currency(10, 0, 0, 0),
	    new Currency(10, 0, 0, 0), new Currency(0, 3, 0, 0), new Currency(0, 6, 0, 0)));
    public int upgrades = 0;

    public PickaxeBWI() {
	for (int i = 0; i < item.size(); i++) {
	    ItemMeta meta = item.get(i).getItemMeta();
	    meta.addEnchant(Enchantment.DIG_SPEED, efficiencyLvl.get(i), false);
	    item.get(i).setItemMeta(meta);
	}
    }

    @Override
    public ItemStack getItem() {
	return item.get(upgrades);
    }

    @Override
    public String getName() {
	return displayName.get(upgrades + 1);
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
	if (upgrades + 1 < cost.size()) {
	    return cost.get(upgrades + 1);
	}
	return null;
    }

    @Override
    public ItemStack getCurrentItem() {
	return item.get(upgrades);
    }

    @Override
    public Integer getUpgrades() {
	return this.upgrades;
    }

    @Override
    public void setUpgrades(int upgrades) {
	this.upgrades = upgrades;
    }

    @Override
    public void respawn(Player player) {
	if (this.upgrades != 0)
	    this.upgrades--;
	player.getInventory().addItem(getCurrentItem());
    }

    @Override
    public void clicked(Player player) {
	if (Main.plugin.game.teamManager.playerData.get(player).permanentItems.contains(this)) {
	    player.sendMessage(Messages.CANT_BUY_ALREADY_PURCHASED);
	    return;
	}

	if (Shop.buy(player, this)) {
	    Main.plugin.game.teamManager.playerData.get(player).permanentItems.add(this);
	} else {
	    player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), getCost()));
	}
    }

}
