package bkcraft.bedwars.game;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;
import bkcraft.bedwars.game.shop.items.armor.Armor;
import bkcraft.bedwars.game.shop.items.armor.LeatherArmor;

public class PlayerData {

    public Player player;
    public Team team;
    public Armor armor;
    public Set<PermanentBedwarsItem> permanentItems;
    public boolean dead;

    public PlayerData(Player player) {
	this.player = player;
	this.team = Team.NONE;
	this.permanentItems = new HashSet<PermanentBedwarsItem>();
    }

    public PlayerData(Player player, Team team) {
	this.player = player;
	this.team = team;
	this.permanentItems = new HashSet<PermanentBedwarsItem>();
    }

    public void setTeam(Team team) {
	this.team = team;
	this.armor = new LeatherArmor(team);
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
}
