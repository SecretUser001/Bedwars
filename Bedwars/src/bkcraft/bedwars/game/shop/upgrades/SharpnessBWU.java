package bkcraft.bedwars.game.shop.upgrades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.events.bedwarsevents.EventHandler;
import bkcraft.bedwars.events.bedwarsevents.events.Event;
import bkcraft.bedwars.events.bedwarsevents.events.ShopItemPreprocessEvent;
import bkcraft.bedwars.events.bedwarsevents.listener.ShopItemPreprocessListener;
import bkcraft.bedwars.game.TeamManager;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;

public class SharpnessBWU implements Upgrade {

    public static ItemStack item = new ItemStack(Material.IRON_SWORD);
    public static TeamUpgrade upgrade;
    public static String name = "Sharpened Swords";
    public static String description = "Your team permanently gains Sharpness I on all swords.";
    public static String tierDescription = "Sharpness";
    public static Integer maxUpgrade = 2;
    public static ArrayList<Currency> cost = new ArrayList<Currency>(Arrays.asList(new Currency(0, 0, 8, 0), new Currency(0, 0, 16, 0)));
    public static Set<Material> swords = new HashSet<Material>(Arrays.asList(Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD));
    
    public SharpnessBWU() {
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
	if(level > maxUpgrade) {
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
	if(Shop.canUpgrade(player, this)) {
	    Shop.upgrade(player, this);
	    
	    TeamManager teamManager = Main.plugin.getGame().getTeamManager();
	    
	    int level = teamManager.getUpgrade(player, getUpgrade());
	    
	    for(Player p : teamManager.getTeam(player)) {
		for(PermanentBedwarsItem permanentItem : teamManager.getPlayerData(player).permanentItems) {
		    if(swords.contains(permanentItem.getItem().getType())) {
			permanentItem.getItem().addEnchantment(Enchantment.DAMAGE_ALL, level);
		    }
		}
		
		for(ItemStack item : p.getInventory()) {
		    if(item == null) {
			continue;
		    }
		    
		    if(swords.contains(item.getType())) {
			item.addEnchantment(Enchantment.DAMAGE_ALL, level);
		    }
		}
	    }
	}
    }
    
    public void registerListeners() {
	EventHandler eventHandler = Main.plugin.getEventHandler();
	
	eventHandler.addListener(new ShopItemPreprocessListener() {
	    
	    @Override
	    public boolean onEvent(Event e) {
		ShopItemPreprocessEvent event = (ShopItemPreprocessEvent) e;
		
		int level = Main.plugin.getGame().getTeamManager().getUpgrade(event.getPlayer(), getUpgrade());
		
		if(level > 0 && event.getItem().getCategory() == Category.Melee) {
		    event.getItem().getItem().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, level);
		    return true;
		}
		
		return true;
	    }
	    
	});
    }
    
    public String getTierDescription() {
	return tierDescription;
    }
}
