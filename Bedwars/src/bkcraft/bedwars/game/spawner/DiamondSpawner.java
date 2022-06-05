package bkcraft.bedwars.game.spawner;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;

import bkcraft.bedwars.Main;

public class DiamondSpawner extends Spawner{

	public DiamondSpawner(Location location) {
		super(location);
		

		this.delay = new HashMap<Material, Double>();
		this.delay.put(Material.DIAMOND, 20.);		
	}
	
	@Override
	public void startSpawner() {
		this.scheduler = new SpawnerRunnable(this.location, this.delay, false).runTaskTimer(Main.plugin, 5, 5);
		this.enabled = true;
	}
}
