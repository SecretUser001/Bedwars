package bkcraft.bedwars.game.shop.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.GUI.Category;

public interface BedwarsItem {

    public ItemStack getItem();

    public String getName();

    public Category getCategory();

    public Currency getCost();

    public String getDescription();

    public void clicked(Player player);
    
    public BedwarsItem clone();
    
}
