package bkcraft.bedwars.game.spawner;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;

public class IslandSpawner extends Spawner {

	public IslandSpawner(Location location) {
		super(location);

		this.delay = new HashMap<Material, Double>();
		this.delay.put(Material.IRON_INGOT, 1.);
		this.delay.put(Material.GOLD_INGOT, 5.);
	}
}
