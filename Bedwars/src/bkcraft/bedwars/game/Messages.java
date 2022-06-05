package bkcraft.bedwars.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;

import bkcraft.bedwars.game.shop.Currency;

public class Messages {

	public static String CANT_BUY_NO_CURRENCY(Currency money, Currency cost) {
		HashMap<String, Integer> difference = money.differenceTo(cost);
		ArrayList<String> needs = new ArrayList<String>();
		for(Entry<String, Integer> entry : difference.entrySet()) needs.add(entry.getValue() + " more " + entry.getKey());
		return ChatColor.RED + "You don't have enough " + String.join(", ", difference.keySet()) + "! Need " + String.join(", ", needs) + "!";
	}
	
}
