package bkcraft.bedwars.game.shop.upgrades;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.Team;

public interface Upgrade {

    public ItemStack getItem();
    
    public String getName();
    
    public String getDescription();
        
    public Integer getMaxUpgrade();
    
    public boolean canUpgrade(Team team);
    
    public boolean upgrade(Player player);
    
}
