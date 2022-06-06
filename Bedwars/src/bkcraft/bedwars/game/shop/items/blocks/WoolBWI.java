package bkcraft.bedwars.game.shop.items.blocks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class WoolBWI implements BedwarsItem {

    public static ItemStack item = new ItemStack(Material.WOOL, 16);
    public static String displayName = "Wool";
    public static Category category = Category.Blocks;
    public static String description = "Great for bridging across islands. Turns into you team's color.";
    public static Currency cost = new Currency(4, 0, 0, 0);

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

    @SuppressWarnings("deprecation")
    @Override
    public void clicked(Player player) {
	if (Shop.buy(player, this)) {
	    ItemStack coloredWool = new ItemStack(item);
	    coloredWool.setDurability(Main.plugin.game.teamManager.playerData.get(player).getTeam().getDyeColor().getData());
	    player.getInventory().addItem(coloredWool);
	} else {
	    player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), cost));
	}
    }
}