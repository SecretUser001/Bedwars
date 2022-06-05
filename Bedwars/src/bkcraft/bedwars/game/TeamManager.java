package bkcraft.bedwars.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.entity.Player;

public class TeamManager {

	public int teamCount;
	public HashMap<Player, PlayerData> playerData;
	public ArrayList<Team> TEAM_ORDER = new ArrayList<Team>(Arrays.asList(Team.BLUE, Team.YELLOW, Team.RED, Team.GREEN));
	public HashMap<Team, Boolean> beds;
	
	public TeamManager(int teamCount) {
		this.teamCount = teamCount;
		this.playerData = new HashMap<Player, PlayerData>();
		this.beds = new HashMap<Team, Boolean>();
	}
	
	public void addPlayer(Player player) {
		this.addPlayer(player, Team.NONE);
	}
	
	public void addPlayer(Player player, Team team) {
		if(!this.playerData.containsKey(player)) {
			this.playerData.put(player, new PlayerData(player));
		}
		this.playerData.get(player).setTeam(team);
	}
	
/*	public void createTeams() {
		int layer = 0;
		boolean noTeam;
		for(Entry<Player, PlayerData> entry : this.playerData.entrySet()) {
			if(entry.getValue().getTeam().equals(Team.NONE)) {
				noTeam = true;
				while(noTeam) {
					for(Team team : TEAM_ORDER.subList(0, this.teamCount - 1)) {
						if(this.getTeam(team).size() == layer) {
							entry.getValue().setTeam(team);
							noTeam = false;
							break;
						}
					}
					layer++;
				}
			}
		}
	} */
	
	public void createTeams() {
		ArrayList<Player> players = new ArrayList<>(this.playerData.keySet());
		
		while(!players.isEmpty()) {
			for(Team team : TEAM_ORDER.subList(0, this.teamCount - 1)) {
				if(!players.isEmpty()) {
					this.playerData.get(players.get(0)).setTeam(team);
					players.remove(0);
				}
			}
		}
	}
	
	public ArrayList<Player> getTeam(Team team) {
		ArrayList<Player> returnList = new ArrayList<Player>();
		for(Entry<Player, PlayerData> entry : this.playerData.entrySet()) {
			if(entry.getValue().getTeam().equals(team)) {
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
		for(Team team : TEAM_ORDER.subList(0, this.teamCount - 1)) {
			this.beds.put(team, true);
		}
	}
	
	public void removeBed(Team team) {
		this.beds.put(team, false);
	}
}
