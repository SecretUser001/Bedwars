package bkcraft.bedwars.game.shop.items.utils;

import java.util.ArrayList;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class BridgeeggBWI implements BedwarsItem, Listener {

    public static ItemStack item = new ItemStack(Material.EGG, 1);
    public static String displayName = "Bridge Egg";
    public static Category category = Category.Utils;
    public static String description = "This egg creates a bridge in its trail after being thrown.";
    public static Currency cost = new Currency(0, 0, 0, 1);

    public static int lenght = (int) (1.5 * 1000);
    public static double blockProb = 0.5;
    
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
    public void onEggThrow(ProjectileLaunchEvent event) {
	if (!(event.getEntity() instanceof Egg)) {
	    return;
	}

	Egg egg = (Egg) event.getEntity();
	Long thrown = System.currentTimeMillis();

	DyeColor color = Main.plugin.getGame().getTeamManager().getPlayerData((Player) event.getEntity().getShooter()).getTeam()
		.getDyeColor();

	BukkitRunnable eggTimer = new BukkitRunnable() {
	    @SuppressWarnings("deprecation")
	    @Override
	    public void run() {
		if (egg.isDead() || System.currentTimeMillis() - thrown > lenght) {
		    this.cancel();
		    return;
		}

		if (System.currentTimeMillis() - thrown < 100) {
		    return;
		}

		new BukkitRunnable() {

		    Location eggLocation = egg.getLocation().add(0, -2, 0);
		    
		    ArrayList<Location> eggLocations = new ArrayList<>();

		    @Override
		    public void run() {
			eggLocations.add(eggLocation);

			if (Main.random.nextDouble() < blockProb)
			    eggLocations.add(eggLocation.clone().add(-1, 0, 1));
			if (Main.random.nextDouble() < blockProb)
			    eggLocations.add(eggLocation.clone().add(0, 0, 1));
			if (Main.random.nextDouble() < blockProb)
			    eggLocations.add(eggLocation.clone().add(1, 0, 1));
			if (Main.random.nextDouble() < blockProb)
			    eggLocations.add(eggLocation.clone().add(-1, 0, 0));
			if (Main.random.nextDouble() < blockProb)
			    eggLocations.add(eggLocation.clone().add(1, 0, 0));
			if (Main.random.nextDouble() < blockProb)
			    eggLocations.add(eggLocation.clone().add(-1, 0, -1));
			if (Main.random.nextDouble() < blockProb)
			    eggLocations.add(eggLocation.clone().add(0, 0, -1));
			if (Main.random.nextDouble() < blockProb)
			    eggLocations.add(eggLocation.clone().add(1, 0, -1));

			for (Location location : eggLocations) {
			    if (!Main.plugin.getGame().getBedwarsMap().isMap(eggLocation)) {
				if (location.getBlock().getType() == Material.AIR) {
				    location.getBlock().setType(Material.WOOL);
				    location.getBlock().setData(color.getData());
				}
			    }
			}
		    }
		}.runTaskLater(Main.plugin, 5l);
	    }
	};
	eggTimer.runTaskTimer(Main.plugin, 1L, 1L);
    }
    
    public BridgeeggBWI clone() {
	return new BridgeeggBWI();
    }
}
