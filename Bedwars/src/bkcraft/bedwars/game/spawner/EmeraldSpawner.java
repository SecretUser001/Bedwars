package bkcraft.bedwars.game.spawner;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;

import bkcraft.bedwars.Main;

public class EmeraldSpawner extends Spawner {

	public EmeraldSpawner(Location location) {
		super(location);

		this.delay = new HashMap<Material, Double>();
		this.delay.put(Material.EMERALD, 20.);
	}

	@Override
	public void startSpawner() {
		this.scheduler = new SpawnerRunnable(this.location, this.delay, false).runTaskTimer(Main.plugin, 5, 5);
		this.enabled = true;
	}
}
