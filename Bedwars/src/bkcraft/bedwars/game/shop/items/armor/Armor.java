package bkcraft.bedwars.game.shop.items.armor;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Armor {

    public ItemStack getHelmet();

    public ItemStack getChestplate();
    
    public ItemStack getLeggings();
    
    public ItemStack getBoots();
    
    public void giveArmor(Player player);
    
    public int getUpgrade();
}
