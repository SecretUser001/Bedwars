package bkcraft.bedwars.game;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum Team {

    NONE("", DyeColor.WHITE), RED(ChatColor.RED + "[RED] ", DyeColor.RED), BLUE(ChatColor.BLUE + "[BLUE] ", DyeColor.BLUE),
    GREEN(ChatColor.GREEN + "[GREEN]", DyeColor.GREEN), YELLOW(ChatColor.YELLOW + "[YELLOW] ", DyeColor.YELLOW),
    AQUA(ChatColor.AQUA + "[AQUA] ", DyeColor.CYAN), WHITE(ChatColor.WHITE + "[WHITE] ", DyeColor.WHITE), PINK(ChatColor.LIGHT_PURPLE + "[PINK] ", DyeColor.PINK),
    GRAY(ChatColor.GRAY + "[GRAY] ", DyeColor.GRAY), DEAD(ChatColor.ITALIC + "" + ChatColor.GRAY + "[SPECTATOR] ", DyeColor.WHITE);

    String prefix;
    DyeColor color;

    Team(String prefix, DyeColor color) {
	this.prefix = prefix;
	this.color = color;
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
    
    public DyeColor getColor() {
	return this.color;
    }
}
