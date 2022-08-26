package bkcraft.bedwars.game.shop.GUI;

import org.bukkit.Material;

public enum Category {

    Blocks(Material.HARD_CLAY), Melee(Material.GOLD_SWORD), Armor(Material.CHAINMAIL_BOOTS),
    Tools(Material.STONE_PICKAXE), Ranged(Material.BOW), Potions(Material.POTION), Utils(Material.TNT);

    Material icon;

    Category(Material icon) {
	this.icon = icon;
    }

}
