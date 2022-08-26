package bkcraft.bedwars.game.shop.items.potions;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class InvisibilityPotionBWI implements BedwarsItem, Listener {

    public static ItemStack item = new ItemStack(Material.POTION, 1);
    public static String displayName = "Invisibility Potion (30 seconds)";
    public static Category category = Category.Potions;
    public static String description = "Complete Invisibility (0:30).";
    public static Currency cost = new Currency(0, 0, 0, 2);

    public static HashMap<Player, Long> drunkPotion = new HashMap<Player, Long>();
    public static BukkitTask task = null;

    public InvisibilityPotionBWI() {
	PotionMeta meta = (PotionMeta) item.getItemMeta();
	meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 30, 1), false);
	meta.setDisplayName(displayName);
	item.setItemMeta(meta);

	if (task == null) {
	    task = new BukkitRunnable() {

		@Override
		public void run() {
		    for (Entry<Player, Long> entry : drunkPotion.entrySet()) {
			if (System.currentTimeMillis() - entry.getValue() > 30 * 1000) {
			    Main.plugin.getGame().getTeamManager().getPlayerData(entry.getKey()).getArmor().giveArmor(entry.getKey());
			    drunkPotion.remove(entry.getKey());
			}
		    }
		}
	    }.runTaskTimer(Main.plugin, 10, 10);
	}
    }

    @Override
    public ItemStack getItem() {
	return item;
    }

    @Override
    public String getName() {
	return displayName;
    }

    @Override
    public Category getCategory() {
	return category;
    }

    @Override
    public String getDescription() {
	return description;
    }

    @Override
    public Currency getCost() {
	return cost;
    }

    @Override
    public void clicked(Player player) {
	if (Shop.buy(player, this)) {
	    player.getInventory().addItem(item);
	} else {
	    player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), cost));
	}
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
	if (!(event.getEntity() instanceof Player))
	    return;

	Player player = (Player) event.getEntity();

	if (drunkPotion.containsKey(player)) {
	    Main.plugin.getGame().getTeamManager().getPlayerData(player).getArmor().giveArmor(player);
	    drunkPotion.remove(player);
	    event.setDamage(0);
	}
    }

    @EventHandler
    public void onPotionConsume(PlayerItemConsumeEvent event) {
	Bukkit.broadcastMessage("pevent");

	if (!(event.getItem().getType() == Material.POTION))
	    return;

	boolean invis = false;

	for (PotionEffect potionEffect : ((PotionMeta) event.getItem().getItemMeta()).getCustomEffects()) {
	    Bukkit.broadcastMessage("pe");
	    Bukkit.broadcastMessage(potionEffect.getType().toString());
	    if (potionEffect.getType().equals(PotionEffectType.INVISIBILITY))
		;
	    invis = true;
	}

	if (!invis)
	    return;

	Bukkit.broadcastMessage("pevent 2");

	drunkPotion.put(event.getPlayer(), System.currentTimeMillis());

	event.getPlayer().getInventory().setHelmet(null);
	event.getPlayer().getInventory().setChestplate(null);
	event.getPlayer().getInventory().setLeggings(null);
	event.getPlayer().getInventory().setBoots(null);

	Bukkit.broadcastMessage("pevent ra");

    }
    
    public InvisibilityPotionBWI clone() {
	return new InvisibilityPotionBWI();
    }
}
