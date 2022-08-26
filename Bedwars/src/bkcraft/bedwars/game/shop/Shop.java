package bkcraft.bedwars.game.shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.events.bedwarsevents.events.ItemBuyEvent;
import bkcraft.bedwars.events.bedwarsevents.events.TeamUpgradeEvent;
import bkcraft.bedwars.game.shop.items.BedwarsItem;
import bkcraft.bedwars.game.shop.upgrades.Upgrade;

public class Shop {

    public static boolean canBuy(Player player, BedwarsItem bedwarsItem) {
	return getCurrency(player).has(bedwarsItem.getCost());
    }

    public static boolean buy(Player player, BedwarsItem bedwarsItem) {
	if (!canBuy(player, bedwarsItem)) {
	    return false;
	}

	if (Main.plugin.getEventHandler().call(new ItemBuyEvent(player, bedwarsItem))) {
	    removeCurrency(player, bedwarsItem.getCost());
	    return true;
	}

	return false;
    }

    public static boolean canUpgrade(Player player, Upgrade upgrade) {
	int level = Main.plugin.getGame().getTeamManager().getUpgrade(player, upgrade.getUpgrade());
	return level < upgrade.getMaxUpgrade() && getCurrency(player).has(upgrade.getCost(level + 1));
    }

    public static boolean upgrade(Player player, Upgrade upgrade) {
	if (!canUpgrade(player, upgrade)) {
	    return false;
	}
	
	int level = Main.plugin.getGame().getTeamManager().getUpgrade(player, upgrade.getUpgrade());
	
	if(Main.plugin.getEventHandler().call(new TeamUpgradeEvent(player, upgrade.getUpgrade()))) {
	    removeCurrency(player, upgrade.getCost(level + 1));
	    
	    Main.plugin.getGame().getTeamManager().setUpgradeLevel(player, upgrade.getUpgrade(), level + 1);
	    
	    return true;
	}
	
	return false;
    }

    private static void removeCurrency(Player player, Currency currency) {
	int iron = currency.getIron();
	int gold = currency.getGold();
	int diamonds = currency.getDiamonds();
	int emeralds = currency.getEmeralds();

	for (ItemStack itemStack : player.getInventory().getContents()) {
	    if (itemStack == null) {
		continue;
	    }

	    if (itemStack.getType() == Material.IRON_INGOT) {
		if (itemStack.getAmount() <= iron) {
		    player.getInventory().remove(itemStack);
		    iron -= itemStack.getAmount();
		} else {
		    itemStack.setAmount(itemStack.getAmount() - iron);
		    iron = 0;
		}
	    } else if (itemStack.getType() == Material.GOLD_INGOT) {
		if (itemStack.getAmount() <= gold) {
		    player.getInventory().remove(itemStack);
		    gold -= itemStack.getAmount();
		} else {
		    itemStack.setAmount(itemStack.getAmount() - gold);
		    gold = 0;
		}
	    } else if (itemStack.getType() == Material.DIAMOND) {
		if (itemStack.getAmount() <= diamonds) {
		    player.getInventory().remove(itemStack);
		    diamonds -= itemStack.getAmount();
		} else {
		    itemStack.setAmount(itemStack.getAmount() - diamonds);
		    diamonds = 0;
		}
	    } else if (itemStack.getType() == Material.EMERALD) {
		if (itemStack.getAmount() <= emeralds) {
		    player.getInventory().remove(itemStack);
		    emeralds -= itemStack.getAmount();
		} else {
		    itemStack.setAmount(itemStack.getAmount() - emeralds);
		    emeralds = 0;
		}
	    }
	}
    }

    public static Currency getCurrency(Player player) {
	int iron = 0;
	int gold = 0;
	int diamonds = 0;
	int emeralds = 0;

	for (ItemStack itemStack : player.getInventory().getContents()) {
	    if (itemStack == null) {
		continue;
	    }

	    if (itemStack.getType() == Material.IRON_INGOT) {
		iron += itemStack.getAmount();
	    } else if (itemStack.getType() == Material.GOLD_INGOT) {
		gold += itemStack.getAmount();
	    } else if (itemStack.getType() == Material.DIAMOND) {
		diamonds += itemStack.getAmount();
	    } else if (itemStack.getType() == Material.EMERALD) {
		emeralds += itemStack.getAmount();
	    }
	}

	return new Currency(iron, gold, diamonds, emeralds);
    }
}
