package bkcraft.bedwars.game.shop.items.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class BlastProofGlasBWI implements BedwarsItem, Listener {

    public static ItemStack item = new ItemStack(Material.STAINED_GLASS, 4);
    public static String displayName = "Blast-Proof Glass";
    public static Category category = Category.Blocks;
    public static String description = "Immune to explosions.";
    public static Currency cost = new Currency(12, 0, 0, 0);

    public BlastProofGlasBWI() {
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(displayName);
	item.setItemMeta(meta);
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
    public Currency getCost() {
	return cost;
    }

    @Override
    public String getDescription() {
	return description;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void clicked(Player player) {
	if (Shop.buy(player, this)) {
	    ItemStack coloredGlass = new ItemStack(item);
	    coloredGlass
		    .setDurability(Main.plugin.game.teamManager.playerData.get(player).getTeam().getDyeColor().getData());
	    player.getInventory().addItem(coloredGlass);
	} else {
	    player.sendMessage(Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), cost));
	}
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
	for (Block block : event.blockList()) {
	    if (block.getType() == Material.STAINED_GLASS) {
		event.blockList().remove(block);
	    }
	}
    }

}
