package bkcraft.bedwars.game.spawner;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;

import bkcraft.bedwars.Main;

public class IslandSpawner implements Spawner {

    private static HashMap<Material, Double> delay;
    private static HashMap<Material, Integer> maxMaterials;
    private Location location;
    public boolean enabled;
    private BukkitTask scheduler;
    
    
    public IslandSpawner(Location location) {
	this.location = location;
	this.enabled = false;
	
	delay = new HashMap<Material, Double>();
	delay.put(Material.IRON_INGOT, 1.);
	delay.put(Material.GOLD_INGOT, 5.);
	
	maxMaterials = new HashMap<Material, Integer>();
	maxMaterials.put(Material.IRON_INGOT, 128);
	maxMaterials.put(Material.GOLD_INGOT, 64);
    }

    @Override
    public void startSpawner() {
	this.scheduler = new SpawnerRunnable(this.location, delay, maxMaterials, false).runTaskTimer(Main.plugin, 5, 5);
	this.enabled = true;
    }

    @Override
    public void setDelay(HashMap<Material, Double> newDelay) {
	delay = newDelay;
    }

    @Override
    public void stopSpawner() {
	this.scheduler.cancel();
    }
}
