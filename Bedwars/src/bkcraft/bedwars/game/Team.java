package bkcraft.bedwars.game;

import org.bukkit.ChatColor;

public enum Team {

	NONE(""), RED(ChatColor.RED + "[RED] "), BLUE(ChatColor.BLUE + "[BLUE] "), GREEN(ChatColor.GREEN + "[GREEN]"),
	YELLOW(ChatColor.YELLOW + "[YELLOW] "), AQUA(ChatColor.AQUA + "[AQUA] "), WHITE(ChatColor.WHITE + "[WHITE] "),
	PINK(ChatColor.LIGHT_PURPLE + "[PINK] "), GRAY(ChatColor.GRAY + "[GRAY] "),
	DEAD(ChatColor.ITALIC + "" + ChatColor.GRAY + "[SPECTATOR] ");

	String prefix;

	Team(String prefix) {
		this.prefix = prefix;
	}

	public static boolean contains(String name) {
		for (Team team : Team.values()) {
			if (team.name().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public String getPrefix() {
		return this.prefix;
	}
}
