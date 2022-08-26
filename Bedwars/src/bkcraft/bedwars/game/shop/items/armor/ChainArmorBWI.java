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

public class ChainArmorBWI implements PermanentBedwarsItem {

    public static ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
    public static String displayName = "Permanent Chainmail Armor";
    public static Category category = Category.Armor;
    public static String description = "";
    public static Currency cost = new Currency(30, 0, 0, 0);
    
    public ChainArmorBWI() {	
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
	player.getInventory().getBoots().setType(Material.CHAINMAIL_BOOTS);
	player.getInventory().getLeggings().setType(Material.CHAINMAIL_LEGGINGS);
    }

    @Override
    public void clicked(Player player) {
	Armor armor = new ChainArmor(Main.plugin.getGame().getTeamManager().getPlayerData(player).getTeam());
	if(Main.plugin.getGame().getTeamManager().getPlayerData(player).getArmor().getUpgrade() >= armor.getUpgrade()) {
	    player.sendMessage(Messages.CANT_BUY_ALREADY_PURCHASED);
	    return;
	}
	
	if (Shop.buy(player, this)) {
	    Main.plugin.getGame().getTeamManager().getPlayerData(player).setArmor(armor);
	    armor.giveArmor(player);
	} else {
	    player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), cost));
	}
    }
    
    public ChainArmorBWI clone() {
	return new ChainArmorBWI();
    }
}
