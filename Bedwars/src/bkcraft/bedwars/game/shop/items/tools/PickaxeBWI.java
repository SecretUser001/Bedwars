package bkcraft.bedwars.game.shop.items.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;
import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;
import bkcraft.bedwars.game.shop.items.UpgradebleBedwarsItem;

public class PickaxeBWI implements UpgradebleBedwarsItem {

    public static String name = "Pickaxe";
    public List<PermanentBedwarsItem> upgrades = new ArrayList<PermanentBedwarsItem>(Arrays.asList(new WoodenPickaxeBWI(this),
	    new GoldPickaxeBWI(this), new IronPickaxeBWI(this), new DiamondPickaxeBWI(this)));
    public int upgrade = 0;
    public boolean bought;
    
    @Override
    public String getItemName() {
	return name;
    }
    
    @Override
    public BedwarsItem getBedwarsItem(int upgrade) {
	return upgrades.get(upgrade);
    }

    @Override
    public ItemStack getItem() {
	return upgrades.get(upgrade).getItem();
    }
    
    int getNextUpgrade() {
	if(!bought) {
	    return 0;
	}
	
	if(this.upgrade < (this.upgrades.size() - 1)) {
	    return this.upgrade + 1;
	}
	return this.upgrade;
    }

    
    @Override
    public boolean upgrade(int upgrade) {
	if (upgrade <= this.upgrade) {
	    return false;
	}
	this.upgrade = upgrade;
	return true;
    }
    
    @Override
    public void respawn(Player player) {
	upgrades.get(upgrade).respawn(player);
    }

    @Override
    public String getName() {
	return upgrades.get(getNextUpgrade()).getName();
    }

    @Override
    public Category getCategory() {
	return upgrades.get(getNextUpgrade()).getCategory();
    }

    @Override
    public Currency getCost() {
	return upgrades.get(getNextUpgrade()).getCost();
    }

    @Override
    public String getDescription() {
	return upgrades.get(getNextUpgrade()).getDescription();
    }

    @Override
    public void clicked(Player player) {
	upgrades.get(getNextUpgrade()).clicked(player);
    }
}
