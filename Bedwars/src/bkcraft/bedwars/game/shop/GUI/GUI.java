package bkcraft.bedwars.game.shop.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Messages;
import bkcraft.bedwars.game.shop.Shop;
import bkcraft.bedwars.game.shop.items.BedwarsItem;
import bkcraft.bedwars.game.shop.items.ItemList;
import bkcraft.bedwars.game.shop.items.UpgradebleBedwarsItem;
import bkcraft.bedwars.game.shop.upgrades.Upgrade;

public class GUI {

    public static List<Integer> shopItemSlots = new ArrayList<Integer>(
	    Arrays.asList(19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43));

    public static List<Integer> upgradesItemSlots = new ArrayList<Integer>(Arrays.asList(11, 12, 13, 20, 21, 22));

    public static ArrayList<Player> openShops = new ArrayList<Player>();
    public static ArrayList<Player> openUpgrades = new ArrayList<Player>();
    public static HashMap<Category, HashMap<Integer, BedwarsItem>> shopSlotNumbers = new HashMap<Category, HashMap<Integer, BedwarsItem>>();
    public static HashMap<Integer, Upgrade> upgradesSlotNumbers = new HashMap<>();
    public static HashMap<Player, Category> openCategory = new HashMap<Player, Category>();

    public static void openShop(Player player, Category category) {
	openCategory.put(player, category);
	if (openShops.contains(player)) {
	    Inventory inventory = player.getOpenInventory().getTopInventory();
	    inventory.clear();

	    fillCategorys(inventory, category);
	    fillItems(inventory, category, player);
	} else {
	    openShops.add(player);
	    Inventory inventory = Bukkit.createInventory(player, 6 * 9, category.name());
	    fillCategorys(inventory, category);
	    fillItems(inventory, category, player);
	    player.openInventory(inventory);
	}
    }

    public static void openUpgrades(Player player) {
	if (openUpgrades.contains(player)) {
	    Inventory inventory = player.getOpenInventory().getTopInventory();
	    inventory.clear();

	    fillUpgrades(inventory, player);
	} else {
	    openUpgrades.add(player);

	    Inventory inventory = Bukkit.createInventory(player, 6 * 9, "UPGRADES");

	    fillUpgrades(inventory, player);

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

    public static void fillItems(Inventory inventory, Category category, Player player) {
	List<BedwarsItem> items = ItemList.getItems(category);
	List<UpgradebleBedwarsItem> listedUpgradebleItems = ItemList.getUpgradebleItems(category);

	List<UpgradebleBedwarsItem> upgradebleItems = new ArrayList<UpgradebleBedwarsItem>();

	for (UpgradebleBedwarsItem item : listedUpgradebleItems) {
	    if (Main.plugin.getGame().getTeamManager().getPlayerData(player).upgradebleItems
		    .containsKey(item.getItemName())) {
		upgradebleItems.add(Main.plugin.getGame().getTeamManager().getPlayerData(player).upgradebleItems
			.get(item.getItemName()));
	    } else {
		upgradebleItems.add(item);
	    }
	}

	items.addAll(upgradebleItems);

	if (!shopSlotNumbers.containsKey(category)) {
	    shopSlotNumbers.put(category, new HashMap<Integer, BedwarsItem>());
	}

	for (Integer slot : shopItemSlots) {
	    if (items.isEmpty()) {
		break;
	    }

	    BedwarsItem bwItem = items.get(0);

	    shopSlotNumbers.get(category).put(slot, bwItem);

	    ItemStack item = bwItem.getItem();
	    ItemMeta meta = item.getItemMeta();

	    meta.setDisplayName(Shop.canBuy(player, bwItem) ? (ChatColor.GREEN + bwItem.getName())
		    : (ChatColor.RED + bwItem.getName()));
	    List<String> cost = new ArrayList<String>(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.WHITE));
	    cost.addAll(Messages.splitString(bwItem.getCost().toString(), ChatColor.WHITE));
	    List<String> description = Messages.splitString(bwItem.getDescription(), ChatColor.GRAY);
	    List<String> canBuy = Messages
		    .splitString(
			    Shop.canBuy(player, bwItem) ? ChatColor.YELLOW + "Click to purchase!"
				    : Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), bwItem.getCost()),
			    ChatColor.RED);

	    ArrayList<String> lore = new ArrayList<String>();
	    lore.addAll(cost);
	    lore.add("");
	    lore.addAll(description);
	    lore.add("");
	    lore.addAll(canBuy);

	    meta.setLore(lore);

	    item.setItemMeta(meta);

	    inventory.setItem(slot, item);
	    items.remove(0);
	}
    }

    public static void fillUpgrades(Inventory inventory, Player player) {
	List<Upgrade> upgrades = new ArrayList<Upgrade>();
	upgrades.addAll(Main.plugin.getGame().getUpgradeManager().getUpgrades().values());

	for (Integer slot : upgradesItemSlots) {
	    if (upgrades.isEmpty()) {
		break;
	    }

	    Upgrade upgrade = upgrades.get(0);

	    upgradesSlotNumbers.put(slot, upgrade);

	    ItemStack item = upgrade.getItem();
	    ItemMeta meta = item.getItemMeta();

	    int nextUpgrade = Main.plugin.getGame().getTeamManager().getUpgrade(player, upgrade.getUpgrade()) + 1;
	    boolean isMaxed = nextUpgrade > upgrade.getMaxUpgrade();

	    meta.setDisplayName(isMaxed ? (ChatColor.GREEN + upgrade.getName())
		    : Shop.canUpgrade(player, upgrade) ? (ChatColor.YELLOW + upgrade.getName())
			    : (ChatColor.RED + upgrade.getName()));

	    List<String> description = Messages.splitString(upgrade.getDescription(), ChatColor.GRAY);

	    List<String> cost = new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.WHITE));

	    int c = isMaxed ? nextUpgrade - 1 : nextUpgrade;

	    cost.addAll(Messages.splitString(upgrade.getCost(c).toString(), ChatColor.WHITE));

	    List<String> canBuy = Messages.splitString(isMaxed ? ChatColor.GREEN + "UNLOCKED"
		    : Shop.canUpgrade(player, upgrade) ? ChatColor.YELLOW + "Click to purchase!"
			    : Messages.CANT_BUY_NO_CURRENCY(Shop.getCurrency(player), upgrade.getCost(nextUpgrade)),
		    ChatColor.RED);

	    ArrayList<String> lore = new ArrayList<String>();
	    lore.addAll(description);
	    lore.add("");
	    lore.addAll(cost);
	    lore.add("");
	    lore.addAll(canBuy);

	    meta.setLore(lore);

	    item.setItemMeta(meta);

	    inventory.setItem(slot, item);
	    upgrades.remove(0);
	}

    }

    public static void refresh(Player player) {
	openShop(player, openCategory.get(player));
    }

}
