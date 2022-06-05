package bkcraft.bedwars.game.shop.items;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;

public class FireballBWI implements BedwarsItem, Listener{

	public static ItemStack item = new ItemStack(Material.FIREBALL, 1);
	public static Category category = Category.Utils;
	public static Currency cost = new Currency(40, 0, 0, 0);

	@Override
	public ItemStack getItem() {
		return item;
	}
	
	@Override
	public Category getCategory() {
		return category;
	}
	
	@Override
	public Currency getCost() {
		return cost;
	}
	
	@Override
	public void clicked(Player player) {
		if(Shop.buy(player, this)) {
			player.getInventory().addItem(item);
		} else {
			player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), cost));
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem().getType() == Material.FIREBALL) {
			Fireball fireball = event.getPlayer().launchProjectile(Fireball.class);
			fireball.setIsIncendiary(false);
			event.getPlayer().launchProjectile(Arrow.class);
		}
	}
	
	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		if(event.getEntity() instanceof Fireball) {
			for(Block block : event.blockList()) {
				if(Main.plugin.game.bedwarsMap.isMap(block.getLocation())) {
					event.blockList().remove(block);
				}
			}
		}
	}
}
