package bkcraft.bedwars.game.shop.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bkcraft.bedwars.game.shop.items.BedwarsItem;
import bkcraft.bedwars.game.shop.items.ItemList;

public class GUI {

	public static List<Integer> itemSlots = new ArrayList<Integer>(
			Arrays.asList(19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43));

	public static ArrayList<Player> openShops = new ArrayList<Player>();
	public static HashMap<Category, HashMap<Integer, BedwarsItem>> slotNumbers = new HashMap<Category, HashMap<Integer, BedwarsItem>>();
	public static HashMap<Player, Category> openCategory = new HashMap<Player, Category>();

	public static void open(Player player, Category category) {
		openCategory.put(player, category);
		if (openShops.contains(player)) {
			Inventory inventory = player.getOpenInventory().getTopInventory();
			inventory.clear();

			fillCategorys(inventory, category);
			fillItems(inventory, category);
		} else {
			openShops.add(player);
			Inventory inventory = Bukkit.createInventory(player, 6 * 9, category.name());
			fillCategorys(inventory, category);
			fillItems(inventory, category);
			player.openInventory(inventory);
		}
	}

	@SuppressWarnings("deprecation")
	public static void fillCategorys(Inventory inventory, Category category) {
		for (int i = 0; i < 9; i++) {
			if (i < Category.values().length) {
				ItemStack item = new ItemStack(Category.values()[i].icon, 1);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Category.values()[i].name());
				item.setItemMeta(meta);
				inventory.setItem(i, item);

				inventory.setItem(i + 9,
						Category.values()[i].equals(category)
								? new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData())
								: new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData()));
			}
		}
	}

	public static void fillItems(Inventory inventory, Category category) {
		List<BedwarsItem> items = ItemList.getItems(category);

		if (!slotNumbers.containsKey(category)) {
			slotNumbers.put(category, new HashMap<Integer, BedwarsItem>());
		}

		for (Integer slot : itemSlots) {
			if (items.isEmpty()) {
				break;
			}

			slotNumbers.get(category).put(slot, items.get(0));
			inventory.setItem(slot, items.get(0).getItem());
			items.remove(0);
		}
	}
}
