package bkcraft.bedwars.game.shop.upgrades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Team;
import bkcraft.bedwars.game.shop.Currency;

public class SharpnessBWU implements Upgrade {

    public static ItemStack item = new ItemStack(Material.IRON_SWORD);
    public static TeamUpgrade upgrade;
    public static String name = "Sharpened Swords";
    public static String description = "Your team permanently gains Sharpness I on all swords.";
    public static Integer maxUpgrade = 1;
    public static ArrayList<Currency> cost = new ArrayList<Currency>(Arrays.asList(new Currency(0, 0, 8, 0)));
    public static Set<Material> swords = new HashSet<Material>(Arrays.asList(Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD));
    
    public SharpnessBWU() {
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(name);
	item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItem() {
	return item;
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
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean canUpgrade(Team team) {
	return Main.plugin.game.upgradeManager.getUpgrade(team, upgrade) < maxUpgrade;
    }

    @Override
    public boolean upgrade(Player player) {
	Team team = Main.plugin.game.teamManager.playerData.get(player).getTeam();
	
	if (!canUpgrade(team))
	    return false;

	int afterUpgrade = Main.plugin.game.upgradeManager.getUpgrade(team, upgrade) + 1;
	
	Main.plugin.game.upgradeManager.setUpgrade(team, upgrade,
		afterUpgrade);
	
	for(Player tPlayer : Main.plugin.game.teamManager.getTeam(team)) {
	    for(ItemStack itemStack : tPlayer.getInventory()) {
		if(swords.contains(itemStack.getType())) {
		    ItemMeta meta = itemStack.getItemMeta();
		    meta.addEnchant(Enchantment.DAMAGE_ALL, afterUpgrade, false);
		    itemStack.setItemMeta(meta);
		}
	    }
	}
	
	return true;
    }

}
