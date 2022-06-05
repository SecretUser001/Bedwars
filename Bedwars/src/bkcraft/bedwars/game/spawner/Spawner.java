package bkcraft.bedwars.game.spawner;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;

import bkcraft.bedwars.Main;

public class Spawner {

	Location location;
	boolean enabled;
	
	HashMap<Material, Double> delay;
	HashMap<Material, Long> lastSpawn;
	
	BukkitTask scheduler;
	
	public Spawner(Location location) {
		this.location = location;
		this.enabled = false;
	}
	
	public void startSpawner() {
		this.scheduler = new SpawnerRunnable(this.location, this.delay, true).runTaskTimer(Main.plugin, 5, 5);
		this.enabled = true;
	}
	
	public void stopSpawner() {
		this.scheduler.cancel();
		this.enabled = false;
	}
	
}
