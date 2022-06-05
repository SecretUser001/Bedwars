package bkcraft.bedwars.game;

import org.bukkit.entity.Player;

public class PlayerData {

	public Player player;
	public Team team;
	public boolean dead;
	
	public PlayerData(Player player) {
		this.player = player;
		this.team = Team.NONE;
	}
	
	public PlayerData(Player player, Team team) {
		this.player = player;
		this.team = team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Team getTeam() {
		return this.team;
	}
}
