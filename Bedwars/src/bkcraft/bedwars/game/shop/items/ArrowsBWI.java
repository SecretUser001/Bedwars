package bkcraft.bedwars.game.shop.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;

public class ArrowsBWI implements BedwarsItem{

	public static ItemStack item = new ItemStack(Material.ARROW, 6);
	public static Category category = Category.Ranged;
	public static Currency cost = new Currency(0, 2, 0, 0);

	@Override
	public ItemStack getItem() {
		return item;
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
	public void clicked(Player player) {
		if(Shop.buy(player, this)) {
			player.getInventory().addItem(item);
		} else {
			player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), cost));
		}
	}
}
