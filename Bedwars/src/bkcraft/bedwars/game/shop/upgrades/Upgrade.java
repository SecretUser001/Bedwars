package bkcraft.bedwars.game.shop.upgrades;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.shop.Currency;

public interface Upgrade {
    
    public Currency getCost(int level);
    
    public ItemStack getItem();
    
    public String getName();
    
    public String getDescription();
        
    public String getTierDescription();
    
    public Integer getMaxUpgrade();
    
    public TeamUpgrade getUpgrade();
    
    public void clicked(Player player);
    
    public void registerListeners();    
}
