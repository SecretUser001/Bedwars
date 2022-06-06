package bkcraft.bedwars.game.shop.items.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import bkcraft.bedwars.game.Team;

public class LeatherArmor implements Armor {

    public ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
    public ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
    public ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
    public ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    public static int upgrade = 0;

    public LeatherArmor(Team team) {
	LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
	meta.setColor(team.getDyeColor().getColor());

	helmet.setItemMeta(meta);
	chestplate.setItemMeta(meta);
	leggings.setItemMeta(meta);
	boots.setItemMeta(meta);
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
