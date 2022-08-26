package bkcraft.bedwars.game.shop.upgrades;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.events.bedwarsevents.EventHandler;
import bkcraft.bedwars.events.bedwarsevents.events.ArmorChangeEvent;
import bkcraft.bedwars.events.bedwarsevents.events.Event;
import bkcraft.bedwars.events.bedwarsevents.listener.ArmorChangeListener;
import bkcraft.bedwars.game.TeamManager;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.items.armor.Armor;

public class ProtectionBWU implements Upgrade {

    public static ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
    public static TeamUpgrade upgrade = TeamUpgrade.PROTECTION;
    public static String name = "Reinforced Armor";
    public static String description = "Your team permanently gains Protection on all armor pieces!";
    public static String tierDescription = "Protection";
    public static Integer maxUpgrade = 4;
    public static ArrayList<Currency> cost = new ArrayList<Currency>(Arrays.asList(new Currency(0, 0, 5, 0),
	    new Currency(0, 0, 10, 0), new Currency(0, 0, 20, 0), new Currency(0, 0, 30, 0)));
    public static ArrayList<Material> armors = new ArrayList<Material>(Arrays.asList(Material.LEATHER_BOOTS,
	    Material.GOLD_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS));

    public ProtectionBWU() {
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

	    for (Player p : teamManager.getTeam(player)) {
		for (ItemStack item : p.getInventory().getArmorContents()) {
		    item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		}

		Armor armor = teamManager.getPlayerData(player).getArmor();

		armor.getBoots().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		armor.getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		armor.getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		armor.getHelmet().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);

	    }
	}
    }

    public void registerListeners() {
	EventHandler eventHandler = Main.plugin.getEventHandler();

	eventHandler.addListener(new ArmorChangeListener() {

	    @Override
	    public boolean onEvent(Event e) {
		ArmorChangeEvent event = (ArmorChangeEvent) e;

		int level = Main.plugin.getGame().getTeamManager().getUpgrade(event.getPlayer(), getUpgrade());

		if (level > 0) {

		    Armor armor = event.getReplacement();

		    armor.getBoots().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		    armor.getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		    armor.getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		    armor.getHelmet().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		}

		return true;
	    }

	});
    }
    
    public String getTierDescription() {
	return tierDescription;
    }
}