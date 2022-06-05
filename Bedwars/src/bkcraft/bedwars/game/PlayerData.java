package bkcraft.bedwars.game;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;

public class PlayerData {

    public Player player;
    public Team team;
    public ArrayList<PermanentBedwarsItem> permanentItems;
    public boolean dead;

    public PlayerData(Player player) {
	this.player = player;
	this.team = Team.NONE;
	this.permanentItems = new ArrayList<PermanentBedwarsItem>();
    }

    public PlayerData(Player player, Team team) {
	this.player = player;
	this.team = team;
	this.permanentItems = new ArrayList<PermanentBedwarsItem>();
    }

    public void setTeam(Team team) {
	this.team = team;
    }

    public Team getTeam() {
	return this.team;
    }
}
