package bkcraft.bedwars.game.shop.items.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.Team;
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

    public static Long lenght = 30L;

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
    public void eggBridge(PlayerEggThrowEvent event) {
	Egg egg = event.getEgg();
	Long thrown = System.currentTimeMillis();

	DyeColor color = Main.plugin.game.teamManager.playerData.get(event.getPlayer()).getTeam().getDyeColor();

	BukkitRunnable eggTimer = new BukkitRunnable() {
	    @SuppressWarnings("deprecation")
	    @Override
	    public void run() {
		if (egg.isDead() || System.currentTimeMillis() - thrown > lenght) {
		    this.cancel();
		    return;
		}

		if (egg.getLocation().getBlock().getType() == Material.AIR) {
		    egg.getLocation().getBlock().setType(Material.WOOL);
		    egg.getLocation().getBlock().setData(color.getData());
		}
	    }
	};
	eggTimer.runTaskTimer(Main.plugin, 2L, 2L);
    }
}
