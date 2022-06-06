package bkcraft.bedwars.game.shop.items.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import bkcraft.bedwars.game.Team;

public class ChainArmor implements Armor {

    public ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
    public ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
    public ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    public ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
    public static int upgrade = 1;

    public ChainArmor(Team team) {
	LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
	meta.setColor(team.getDyeColor().getColor());

	helmet.setItemMeta(meta);
	chestplate.setItemMeta(meta);
    }

    @Override
    public ItemStack getHelmet() {
	return helmet;
    }

    @Override
    public ItemStack getChestplate() {
	return chestplate;
    }

    @Override
    public ItemStack getLeggings() {
	return leggings;
    }

    @Override
    public ItemStack getBoots() {
	return boots;
    }

    @Override
    public void giveArmor(Player player) {
	player.getInventory().setHelmet(getHelmet());
	player.getInventory().setChestplate(getChestplate());
	player.getInventory().setLeggings(getLeggings());
	player.getInventory().setBoots(getBoots());
    }
    
    @Override
    public int getUpgrade() {
	return upgrade;
    }

}
