package bkcraft.bedwars.game.shop.items.tools;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;

public class IronAxeBWI implements PermanentBedwarsItem {

    public static ItemStack item = new ItemStack(Material.IRON_AXE);
    public static String name = "Iron Axe";
    public static Category category = Category.Tools;
    public static String description = "Permanent Axe";
    public static Currency cost = new Currency(0, 3, 0, 0);
    public static int upgrades = 2;

    public AxeBWI axe;

    public IronAxeBWI(AxeBWI axe) {
	this.axe = axe;

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

	if (!this.axe.upgrade(upgrades)) {
	    player.sendMessage(Messages.CANT_BUY_ALREADY_PURCHASED);
	    return;
	}
	
	Shop.buy(player, this);

	removeItem(player);
	respawn(player);
    }
    
    @Override
    public void respawn(Player player) {
	player.getInventory().addItem(getItem());
    }
    
    private static void removeItem(Player player) {
	for (ItemStack itemStack : player.getInventory()) {
	    if (itemStack == null)
		return;
	    
	    if (itemStack.getType().equals(item.getType()) && itemStack.hasItemMeta()
		    && itemStack.getItemMeta().getDisplayName().equals(name)) {
		player.getInventory().remove(itemStack);
	    }
	}
    }
}
