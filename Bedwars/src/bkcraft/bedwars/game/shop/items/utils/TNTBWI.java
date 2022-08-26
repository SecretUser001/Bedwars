package bkcraft.bedwars.game.shop.items.utils;

import java.util.Iterator;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class TNTBWI implements BedwarsItem, Listener {

    public static ItemStack item = new ItemStack(Material.TNT, 1);
    public static String displayName = "TNT";
    public static Category category = Category.Utils;
    public static String description = "Instantly ignites, appropriate to explode things.";
    public static Currency cost = new Currency(0, 8, 0, 0);

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
    public void onPlace(BlockPlaceEvent e) {
	if (e.getBlock().getType() == Material.TNT) {
	    e.getBlock().setType(Material.AIR);
	    Entity tnt = e.getPlayer().getWorld().spawn(e.getBlock().getLocation().add(0.5, 0.5, 0.5), TNTPrimed.class);
	    ((TNTPrimed) tnt).setFuseTicks(50);
	}
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
	if (event.getEntity() instanceof TNTPrimed) {

	    Iterator<Block> iterator = event.blockList().iterator();
	    while (iterator.hasNext()) {
		if(Main.plugin.getGame().getBedwarsMap().isMap(iterator.next().getLocation())) {
		    iterator.remove();
		}
	    }

	    for (Entity entity : event.getEntity().getWorld().getEntities()) {
		if (entity.getLocation().distance(event.getEntity().getLocation()) < 10) {
		    Vector v = entity.getLocation().add(0, 1, 0).toVector()
			    .subtract(event.getEntity().getLocation().toVector());

		    double l = v.length();
		    v.normalize();
		    v.multiply(4 / l);

		    if (entity instanceof Player) {
			if ((((Player) entity).getGameMode() == GameMode.SURVIVAL)
				|| ((Player) entity).getGameMode() == GameMode.ADVENTURE) {
			    entity.setVelocity(entity.getVelocity().add(v.divide(new Vector(1, 5, 1))));
			} else {
			    entity.setVelocity(entity.getVelocity().add(v.divide(new Vector(3, 10, 3))));
			}
		    }
		}
	    }
	}
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
	if (event.getEntity() instanceof Player && event.getCause() == DamageCause.ENTITY_EXPLOSION) {
	    event.setDamage(0.0);
	}
    }
    
    public TNTBWI clone() {
	return new TNTBWI();
    }
}
