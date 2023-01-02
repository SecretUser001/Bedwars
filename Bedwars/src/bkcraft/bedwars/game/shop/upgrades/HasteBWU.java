package bkcraft.bedwars.game.shop.upgrades;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.TeamManager;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;

public class HasteBWU implements Upgrade {

    public static ItemStack item = new ItemStack(Material.GOLD_PICKAXE);
    public static TeamUpgrade upgrade = TeamUpgrade.HASTE;
    public static String name = "Maniac Miner";
    public static String description = "All players of your team permanently gain haste!";
    public static String tierDescription = "Haste";
    public static Integer maxUpgrade = 2;
    public static ArrayList<Currency> cost = new ArrayList<Currency>(Arrays.asList(new Currency(0, 0, 4, 0),
	    new Currency(0, 0, 6, 0)));

    public HasteBWU() {
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(name);
	item.setItemMeta(meta);
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public String getDescription() {
	return description;
    }

    @Override
    public Integer getMaxUpgrade() {
	return maxUpgrade;
    }

    @Override
    public ItemStack getItem() {
	return item;
    }

    @Override
    public Currency getCost(int level) {
	if (level > maxUpgrade) {
	    return null;
	}

	return cost.get(level - 1);
    }

    @Override
    public TeamUpgrade getUpgrade() {
	return upgrade;
    }

    @Override
    public void clicked(Player player) {
	if (Shop.upgrade(player, this)) {
	    TeamManager teamManager = Main.plugin.getGame().getTeamManager();

	    int level = teamManager.getUpgrade(player, getUpgrade());

	    teamManager.addPermanentEffect(player, PotionEffectType.FAST_DIGGING, level);
	    
	    for (Player p : teamManager.getTeam(player)) {
		teamManager.addPotionEffects(p);
	    }
	}
    }

    public void registerListeners() {
	
    }
    
    public String getTierDescription() {
	return tierDescription;
    }
}