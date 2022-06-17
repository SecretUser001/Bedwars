package bkcraft.bedwars.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import bkcraft.bedwars.game.shop.upgrades.TeamUpgrade;

public class UpgradeManager {

    public HashMap<Team, HashMap<TeamUpgrade, Integer>> upgrades;

    public UpgradeManager(Set<Team> teams) {
	this.upgrades = new HashMap<>();

	HashMap<TeamUpgrade, Integer> upgrades = new HashMap<TeamUpgrade, Integer>();

	for (TeamUpgrade upgrade : TeamUpgrade.values()) {
	    upgrades.put(upgrade, 0);
	}

	for (Team team : teams) {
	    this.upgrades.put(team, upgrades);
	}
    }

    public void setUpgrade(Team team, TeamUpgrade upgrade, Integer value) {
	this.upgrades.get(team).put(upgrade, value);
    }
    
    public Integer getUpgrade(Team team, TeamUpgrade upgrade) {
	return this.upgrades.get(team).get(upgrade);
    }
}
