package bkcraft.bedwars.game.shop.items.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class FireballBWI implements BedwarsItem, Listener {

    public static ItemStack item = new ItemStack(Material.FIREBALL, 1);
    public static String displayName = "Fireball";
    public static Category category = Category.Utils;
    public static String description = "Right-click to launch! Great to knock back enemies walking on thin bridges.";
    public static Currency cost = new Currency(40, 0, 0, 0);
    public static int delay = 500;

    public HashMap<Player, Long> lastUse;

    public FireballBWI() {
	this.lastUse = new HashMap<Player, Long>();
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
    public void onInteract(PlayerInteractEvent event) {
	if (event.getPlayer().getItemInHand().getType() != Material.FIREBALL) {
	    return;
	}
	
	if(!((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))) {
	    return;
	}
	
	if(!lastUse.containsKey(event.getPlayer())) {
	    this.lastUse.put(event.getPlayer(), 0l);
	}
	
	if((System.currentTimeMillis() - this.lastUse.get(event.getPlayer())) < delay) {
	    return;
	}
	
	if(event.getPlayer().getItemInHand().getAmount() > 1) 
	    event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount());
	else
	    event.getPlayer().getInventory().remove(event.getPlayer().getItemInHand());
	
	
	event.setCancelled(true);
	shootFireball(event.getPlayer());
    }
    
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
	if (event.getEntity() instanceof Fireball) {
	    Iterator<Block> iterator = event.blockList().iterator();
	    while (iterator.hasNext()) {
		if(Main.plugin.game.bedwarsMap.isMap(iterator.next().getLocation())) {
		    iterator.remove();
		}
	    }
	}
    }

    public static void shootFireball(Player player) {
	Fireball fireball = player.launchProjectile(Fireball.class);
	Arrow arrow = player.launchProjectile(Arrow.class);
	Vector arrowDirection = arrow.getVelocity();
	Vector fireballDirection = fireball.getVelocity();
	fireball.setVelocity(new Vector(0, 0, 0));
	arrow.setVelocity(new Vector(0, 0, 0));
	fireball.setYield(2);
	new BukkitRunnable() {

	    @Override
	    public void run() {
		fireball.setVelocity(fireballDirection);
		arrow.setVelocity(arrowDirection);
		player.setFireTicks(0);
	    }
	}.runTaskLater(Main.plugin, 3);
    }
}
