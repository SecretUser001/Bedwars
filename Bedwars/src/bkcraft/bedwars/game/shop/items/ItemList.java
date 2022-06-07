package bkcraft.bedwars.game.shop.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bkcraft.bedwars.game.shop.GUI.Category;
import bkcraft.bedwars.game.shop.items.armor.ChainArmorBWI;
import bkcraft.bedwars.game.shop.items.armor.DiamondArmorBWI;
import bkcraft.bedwars.game.shop.items.armor.IronArmorBWI;
import bkcraft.bedwars.game.shop.items.blocks.BlastProofGlasBWI;
import bkcraft.bedwars.game.shop.items.blocks.ClayBWI;
import bkcraft.bedwars.game.shop.items.blocks.EndstoneBWI;
import bkcraft.bedwars.game.shop.items.blocks.LadderBWI;
import bkcraft.bedwars.game.shop.items.blocks.OakWoodPlanksBWI;
import bkcraft.bedwars.game.shop.items.blocks.ObsidianBWI;
import bkcraft.bedwars.game.shop.items.blocks.WoolBWI;
import bkcraft.bedwars.game.shop.items.melee.DiamondSwordBWI;
import bkcraft.bedwars.game.shop.items.melee.IronSwordBWI;
import bkcraft.bedwars.game.shop.items.melee.KnockbackStickBWI;
import bkcraft.bedwars.game.shop.items.melee.StoneSwordBWI;
import bkcraft.bedwars.game.shop.items.potions.InvisibilityPotionBWI;
import bkcraft.bedwars.game.shop.items.potions.JumpPotionBWI;
import bkcraft.bedwars.game.shop.items.potions.SpeedPotionBWI;
import bkcraft.bedwars.game.shop.items.ranged.ArrowsBWI;
import bkcraft.bedwars.game.shop.items.ranged.BowBWI;
import bkcraft.bedwars.game.shop.items.ranged.BowPowerBWI;
import bkcraft.bedwars.game.shop.items.ranged.BowPunchBWI;
import bkcraft.bedwars.game.shop.items.tools.AxeBWI;
import bkcraft.bedwars.game.shop.items.tools.PickaxeBWI;
import bkcraft.bedwars.game.shop.items.tools.ShearsBWI;
import bkcraft.bedwars.game.shop.items.utils.BedbugBWI;
import bkcraft.bedwars.game.shop.items.utils.DreamDefenderBWI;
import bkcraft.bedwars.game.shop.items.utils.FireballBWI;
import bkcraft.bedwars.game.shop.items.utils.GoldenAppleBWI;

public class ItemList {

    public static List<BedwarsItem> items = new ArrayList<>(Arrays.asList(new ChainArmorBWI(), new IronArmorBWI(),
	    new DiamondArmorBWI(), new WoolBWI(), new ClayBWI(), new BlastProofGlasBWI(), new EndstoneBWI(),
	    new LadderBWI(), new OakWoodPlanksBWI(), new ObsidianBWI(), new StoneSwordBWI(), new IronSwordBWI(),
	    new DiamondSwordBWI(), new KnockbackStickBWI(), new SpeedPotionBWI(), new JumpPotionBWI(),
	    new InvisibilityPotionBWI(), new ArrowsBWI(), new BowBWI(), new BowPowerBWI(), new BowPunchBWI(),
	    new ShearsBWI(), new GoldenAppleBWI(), new BedbugBWI(), new DreamDefenderBWI(), new FireballBWI()));

    public static List<UpgradebleBedwarsItem> upgradebleItems = new ArrayList<UpgradebleBedwarsItem>(
	    Arrays.asList(new AxeBWI(), new PickaxeBWI()));

    public static List<BedwarsItem> getItems(Category category) {
	List<BedwarsItem> returnList = new ArrayList<BedwarsItem>();

	for (BedwarsItem item : items) {
	    if (item.getCategory() == category) {
		returnList.add(item);
	    }
	}

	return returnList;
    }

    public static List<UpgradebleBedwarsItem> getUpgradebleItems(Category category) {
	List<UpgradebleBedwarsItem> returnList = new ArrayList<UpgradebleBedwarsItem>();

	for (UpgradebleBedwarsItem item : upgradebleItems) {
	    if (item.getCategory() == category) {
		returnList.add(item);
	    }
	}

	return returnList;
    }
    
}
