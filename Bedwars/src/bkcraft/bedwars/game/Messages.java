package bkcraft.bedwars.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;

import bkcraft.bedwars.game.shop.Currency;

public class Messages {

    public static int CHARS_PER_LINE = 32;
    
    public static String CANT_BUY_NO_CURRENCY(Currency money, Currency cost) {
	HashMap<String, Integer> difference = money.differenceTo(cost);
	ArrayList<String> needs = new ArrayList<String>();
	for (Entry<String, Integer> entry : difference.entrySet())
	    needs.add(entry.getValue() + " more " + entry.getKey());
	return ChatColor.RED + "You don't have enough " + String.join(", ", difference.keySet()) + "! Need "
		+ String.join(", ", needs) + "!";
    }

    public static String CANT_BUY_ALREADY_PURCHASED = ChatColor.RED + "You have already purchased this";
    
    public static List<String> splitString(String string, ChatColor chatColor) {
	List<String> returnList = new ArrayList<String>();
	
	if(string == "") {
	    return returnList;
	}
	
	ArrayList<String> words = new ArrayList<String>(Arrays.asList(string.split(" ")));
	
	String line = chatColor + words.get(0);
	words.remove(0);
	
	while(words.size() != 0) {
	    if((line + " " + words.get(0)).length() < CHARS_PER_LINE) {
		line += (" " + words.get(0));
		words.remove(0);
	    } else {
		returnList.add(line);
		line = chatColor + words.get(0);
		words.remove(0);
	    }
	}
	
	returnList.add(line);
	
	return returnList;
    }
    
}
