package bkcraft.bedwars.game;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.events.bedwarsevents.events.PlayerRespawnEvent;
import bkcraft.bedwars.game.shop.items.PermanentBedwarsItem;
import bkcraft.bedwars.game.shop.items.UpgradebleBedwarsItem;
import bkcraft.bedwars.game.shop.upgrades.UpgradeManager;
import bkcraft.bedwars.game.title.LegacyTitle;
import bkcraft.bedwars.world.BedwarsMap;

public class Game {

    private BedwarsMap bedwarsMap;
    private TeamManager teamManager;
    private UpgradeManager upgradeManager;
    
    public boolean started;

    public Game(BedwarsMap bedwarsMap) {
	this.bedwarsMap = bedwarsMap;
	this.bedwarsMap.addCage();
	this.bedwarsMap.readInformation();

	this.teamManager = new TeamManager(this.bedwarsMap.TEAM_COUNT);
	this.upgradeManager = new UpgradeManager();
	
	this.started = false;
    }

    public void addPlayer(Player player) {
	if (!started) {
	    player.getEnderChest().clear();
	    player.getInventory().clear();
	    
	    player.getInventory().setHelmet(null);
	    player.getInventory().setChestplate(null);
	    player.getInventory().setLeggings(null);
	    player.getInventory().setBoots(null);
	    
	    player.setHealth(20d);
	    player.setSaturation(20f);
	    player.setVelocity(new Vector(0, 0, 0));
	    player.teleport(new Location(bedwarsMap.world, 0.5, bedwarsMap.SPAWN_HEIGHT, 0.5));
	    player.setGameMode(GameMode.ADVENTURE);

	    this.teamManager.addPlayer(player);
	} else {
	    player.getInventory().clear();

	    player.setHealth(20d);
	    player.setSaturation(20f);
	    player.setVelocity(new Vector(0, 0, 0));
	    player.teleport(new Location(bedwarsMap.world, 0.5, bedwarsMap.SPAWN_HEIGHT, 0.5));
	    player.setGameMode(GameMode.SPECTATOR);
	    
	    this.teamManager.addPlayer(player, Team.DEAD);
	}
    }

    public void start() {
	this.started = true;

	this.bedwarsMap.removeCage();

	this.bedwarsMap.startSpawner();
	this.bedwarsMap.spawnVillager(this.bedwarsMap.shops, ChatColor.BLUE + "ITEM SHOP");
	this.bedwarsMap.spawnVillager(this.bedwarsMap.upgradeShops, ChatColor.BLUE + "UPGRADES");

	this.teamManager.createTeams();
	this.teamManager.createBeds();

	for (Player player : this.teamManager.playerData.keySet()) {
	    respawn(player);
	}
    }

    public void startCountdown() {
	new StartCountdownRunnable(this).runTaskTimer(Main.plugin, 0, 20);
    }

    public void breakBed(Team team) {
	this.teamManager.removeBed(team);

	for (Player player : this.teamManager.getPlayer()) {
	    player.sendMessage(ChatColor.BOLD + "" + ChatColor.WHITE + "BED DESTRUCTION > "
		    + (this.teamManager.getTeam(team).contains(player) ? (ChatColor.GRAY + "Your bed ")
			    : (team.getPrefix()) + " Bed ")
		    + ChatColor.GRAY + "was destroyed");
	}

	for (Player player : this.teamManager.getTeam(team)) {
	    LegacyTitle title = new LegacyTitle();
	    title.send(player, ChatColor.RED + "YOUR BED WAS DESTROYED", "", 0, 2 * 20, 5);
	    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1f, 1f);
	}
    }

    public void kill(Player player) {
	player.setGameMode(GameMode.SPECTATOR);
	player.setHealth(20d);
	player.teleport(new Location(this.bedwarsMap.world, 0.5, this.bedwarsMap.CAGE_HEIGHT, 0.5));
	if (this.teamManager.beds.get(this.teamManager.playerData.get(player).getTeam())) {
	    new RespawnRunnable(this, player).runTaskTimer(Main.plugin, 0, 20);
	}
    }

    public void respawn(Player player) {
	PlayerData data = this.teamManager.playerData.get(player);

	player.setGameMode(GameMode.SURVIVAL);
	player.setVelocity(new Vector(0, 0, 0));
	player.teleport(this.bedwarsMap.spawns.get(this.teamManager.playerData.get(player).getTeam()));

	player.getInventory().clear();
	player.getActivePotionEffects().clear();
	
	data.getArmor().giveArmor(player);

	for (PermanentBedwarsItem item : this.teamManager.playerData.get(player).permanentItems) {
	    item.respawn(player);
	}

	for (UpgradebleBedwarsItem item : this.teamManager.playerData.get(player).upgradebleItems.values()) {
	    item.respawn(player);
	}
	
	this.teamManager.addPotionEffects(player);
	
	Main.plugin.getEventHandler().call(new PlayerRespawnEvent(player));
    }

    public BedwarsMap getBedwarsMap() {
        return bedwarsMap;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public UpgradeManager getUpgradeManager() {
        return upgradeManager;
    }

}
