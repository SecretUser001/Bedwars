package bkcraft.bedwars.game.shop.items;

import org.bukkit.inventory.ItemStack;

public interface UpgradebleBedwarsItem extends PermanentBedwarsItem{

    public String getItemName();
    
    public BedwarsItem getBedwarsItem(int upgrade);
    
    public ItemStack getItem();
    
    public boolean upgrade(int upgrade);
}
