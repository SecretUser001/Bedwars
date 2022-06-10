package bkcraft.bedwars.game.spawner;

import java.util.HashMap;

import org.bukkit.Material;

public interface Spawner {

	public void startSpawner();

	public void stopSpawner();
	
	public void setDelay(HashMap<Material, Double> delay);
	
}
