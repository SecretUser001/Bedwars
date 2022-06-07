package bkcraft.bedwars.game.shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class Shop {

    public static boolean canBuy(Player player, BedwarsItem bedwarsItem) {
	return getCurrency(player).has(bedwarsItem.getCost());
    }

    public static boolean buy(Player player, BedwarsItem bedwarsItem) {
	if (!canBuy(player, bedwarsItem)) {
	    return false;
	}

	int iron = bedwarsItem.getCost().Iron;
	int gold = bedwarsItem.getCost().Gold;
	int diamonds = bedwarsItem.getCost().Diamond;
	int emeralds = bedwarsItem.getCost().Emerald;

	for (ItemStack itemStack : player.getInventory().getContents()) {
	    if (itemStack == null) {
		continue;
	    }

	    if (itemStack.getType() == Material.IRON_INGOT) {
		if (itemStack.getAmount() < iron) {
		    player.getInventory().remove(itemStack);
		    iron -= itemStack.getAmount();
		} else {
		    itemStack.setAmount(itemStack.getAmount() - iron);
		    iron = 0;
		}
	    } else if (itemStack.getType() == Material.GOLD_INGOT) {
		if (itemStack.getAmount() < gold) {
		    player.getInventory().remove(itemStack);
		    gold -= itemStack.getAmount();
		} else {
		    itemStack.setAmount(itemStack.getAmount() - gold);
		    gold = 0;
		}
	    } else if (itemStack.getType() == Material.DIAMOND) {
		if (itemStack.getAmount() < diamonds) {
		    player.getInventory().remove(itemStack);
		    diamonds -= itemStack.getAmount();
		} else {
		    itemStack.setAmount(itemStack.getAmount() - diamonds);
		    diamonds = 0;
		}
	    } else if (itemStack.getType() == Material.EMERALD) {
		if (itemStack.getAmount() < emeralds) {
		    player.getInventory().remove(itemStack);
		    emeralds -= itemStack.getAmount();
		} else {
		    itemStack.setAmount(itemStack.getAmount() - emeralds);
		    emeralds = 0;
		}
	    }
	}
	return true;
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
