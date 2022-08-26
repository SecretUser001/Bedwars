package bkcraft.bedwars.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.events.bedwarsevents.events.ArmorChangeEvent;
import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;
import bkcraft.bedwars.game.shop.items.UpgradebleBedwarsItem;
import bkcraft.bedwars.game.shop.items.armor.Armor;
import bkcraft.bedwars.game.shop.items.armor.LeatherArmor;
import bkcraft.bedwars.game.shop.items.melee.WoodenSwordBWI;

public class PlayerData {

    public Player player;
    public Team team;
    private Armor armor;
    public ArrayList<PermanentBedwarsItem> permanentItems;
    public HashMap<String, UpgradebleBedwarsItem> upgradebleItems;
    public boolean dead;

    public PlayerData(Player player) {
	this.player = player;
	this.team = Team.NONE;
	this.permanentItems = new ArrayList<PermanentBedwarsItem>();
	this.upgradebleItems = new HashMap<String, UpgradebleBedwarsItem>();

	this.permanentItems.add(new WoodenSwordBWI());
    }

    public PlayerData(Player player, Team team) {
	this.player = player;
	this.team = team;
	this.permanentItems = new ArrayList<PermanentBedwarsItem>();
	this.upgradebleItems = new HashMap<String, UpgradebleBedwarsItem>();

	this.permanentItems.add(new WoodenSwordBWI());
    }

    public void setTeam(Team team) {
	this.team = team;

	if (team != Team.NONE) {
	    this.setArmor(new LeatherArmor(team));
	}
    }

    public Team getTeam() {
	return this.team;
    }

    public boolean upgradeArmor(Armor armor) {
	if (armor.getUpgrade() > this.armor.getUpgrade()) {
	    this.armor = armor;
	    return true;
	}
	return false;
    }

    public void setArmor(Armor armor) {
	if (Main.plugin.getEventHandler().call(new ArmorChangeEvent(player, this.armor, armor))) {
	    this.armor = armor;
	}
    }

    public Armor getArmor() {
	return this.armor;
    }
}
