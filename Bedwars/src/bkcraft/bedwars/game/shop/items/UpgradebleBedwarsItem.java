package bkcraft.bedwars.game.shop.items;

import org.bukkit.inventory.ItemStack;

public interface UpgradebleBedwarsItem extends PermanentBedwarsItem {

    public ItemStack getCurrentItem();

    public Integer getUpgrades();

    public void setUpgrades(int upgrades);
}
