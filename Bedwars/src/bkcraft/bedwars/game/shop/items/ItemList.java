package bkcraft.bedwars.game.shop.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bkcraft.bedwars.game.shop.GUI.Category;

public class ItemList {

	public static List<BedwarsItem> items = new ArrayList<>(Arrays.asList(new WoolBWI(), new ClayBWI(), new StoneSwordBWI(), new ChainArmorBWI(),
			new ShearsBWI(), new ArrowsBWI(), new GoldenAppleBWI(), new FireballBWI()));
	
	public static List<BedwarsItem> getItems(Category category) {
		List<BedwarsItem> returnList = new ArrayList<BedwarsItem>();
				
		for(BedwarsItem item : items) {
			if(item.getCategory() == category) {
				returnList.add(item);
			}
		}
		
		return returnList;
	}
	
}
