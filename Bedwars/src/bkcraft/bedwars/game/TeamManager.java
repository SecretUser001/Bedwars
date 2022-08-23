package bkcraft.bedwars.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import bkcraft.bedwars.game.shop.upgrades.TeamUpgrade;

public class TeamManager {

    public int teamCount;
    public HashMap<Player, PlayerData> playerData;
    public ArrayList<Team> TEAM_ORDER = new ArrayList<Team>(
	    Arrays.asList(Team.BLUE, Team.YELLOW, Team.RED, Team.GREEN));
    public HashMap<Team, Boolean> beds;
    public Set<Team> teams;
    
    private HashMap<Team, HashMap<TeamUpgrade, Integer>> upgrades;
    
    public TeamManager(int teamCount) {
	this.teamCount = teamCount;
	this.playerData = new HashMap<Player, PlayerData>();
	this.beds = new HashMap<Team, Boolean>();
	this.teams = new HashSet<Team>();
	this.upgrades = new HashMap<Team, HashMap<TeamUpgrade, Integer>>();
    }

    public void addPlayer(Player player) {
	this.addPlayer(player, Team.NONE);
    }

    public void addPlayer(Player player, Team team) {
	if (!this.playerData.containsKey(player)) {
	    this.playerData.put(player, new PlayerData(player));
	}
	this.playerData.get(player).setTeam(team);
    }

    public void createTeams() {
	ArrayList<Player> players = new ArrayList<>(this.playerData.keySet());
	Collections.shuffle(players);

	while (!players.isEmpty()) {
	    for (Team team : TEAM_ORDER.subList(0, this.teamCount - 1)) {
		if (!players.isEmpty()) {
		    this.playerData.get(players.get(0)).setTeam(team);
		    this.teams.add(team);
		    players.remove(0);
		}
	    }
	}
	
	for(Team team : this.teams) {
	    this.upgrades.put(team, new HashMap<TeamUpgrade, Integer>());
	}
    }

    public ArrayList<Player> getTeam(Team team) {
	ArrayList<Player> returnList = new ArrayList<Player>();
	for (Entry<Player, PlayerData> entry : this.playerData.entrySet()) {
	    if (entry.getValue().getTeam().equals(team)) {
		returnList.add(entry.getKey());
	    }
	}
	return returnList;
    }

    public ArrayList<Player> getTeam(Player player) {
	return this.getTeam(this.playerData.get(player).getTeam());
    }

    public void setTeam(Player player, Team team) {
	this.playerData.get(player).setTeam(team);
	player.setDisplayName(team.getPrefix() + player.getName());
    }

    public Set<Player> getPlayer() {
	return this.playerData.keySet();
    }

    public void createBeds() {
	for (Team team : TEAM_ORDER.subList(0, this.teamCount - 1)) {
	    this.beds.put(team, true);
	}
    }

    public void removeBed(Team team) {
	this.beds.put(team, false);
    }
    
    public int getUpgrade(Team team, TeamUpgrade upgrade) {
	return this.upgrades.get(team).get(upgrade);
    }
    
    public int getUpgrade(Player player, TeamUpgrade upgrade) {
	Team team = this.playerData.get(player).team;
	
	if(!this.upgrades.get(team).containsKey(upgrade)) {
	    this.upgrades.get(team).put(upgrade, 0);
	}
	
	return this.upgrades.get(this.playerData.get(player).team).get(upgrade);
    }
    
    public void setUpgradeLevel(Team team, TeamUpgrade upgrade, int level) {
	this.upgrades.get(team).put(upgrade, level);
    }
    
    public void setUpgradeLevel(Player player, TeamUpgrade upgrade, int level) {
	Bukkit.broadcastMessage("TEAMS: ");
	for(Team team : this.teams) {
	    Bukkit.broadcastMessage(team.toString());
	}
	
	Bukkit.broadcastMessage("");
	
	Bukkit.broadcastMessage(this.playerData.get(player).team.toString());
	
	this.upgrades.get(this.playerData.get(player).team).put(upgrade, level);
    }
    
    public PlayerData getPlayerData(Player player) {
	return this.playerData.get(player);
    }
    
}
