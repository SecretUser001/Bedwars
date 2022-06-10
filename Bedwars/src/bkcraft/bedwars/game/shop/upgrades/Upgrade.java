package bkcraft.bedwars.game.shop.upgrades;

import org.bukkit.inventory.ItemStack;

public interface Upgrade {

    public ItemStack getItem();
    
    public String getName();
    
    public String getDescription();
    
    public TeamUpgrade getUpgrade();
    
    public Integer getMaxUpgrade();
    
    public Integer getCurrentUpgrade();
    
    public boolean hasNext();
        
    public boolean upgrade();
    
}
