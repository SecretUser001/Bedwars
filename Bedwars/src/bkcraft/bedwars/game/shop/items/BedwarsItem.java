package bkcraft.bedwars.game.shop.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.GUI.Category;

public interface BedwarsItem {

	public ItemStack getItem();
	
	public Category getCategory();
	
	public Currency getCost();
	
	public void clicked(Player player);
}
