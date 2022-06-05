package bkcraft.bedwars.game.shop.items.potions;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Currency;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.BedwarsItem;

public class JumpPotionBWI implements BedwarsItem{

    public static ItemStack item = new ItemStack(Material.POTION, 1);
    public static String displayName = "Jump V Potion (45 seconds)";
    public static Category category = Category.Potions;
    public static String description = "";
    public static Currency cost = new Currency(0, 0, 0, 1);

    public JumpPotionBWI() {
	PotionMeta meta = (PotionMeta) item.getItemMeta();
	meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 45, 1), false);
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
    
}
