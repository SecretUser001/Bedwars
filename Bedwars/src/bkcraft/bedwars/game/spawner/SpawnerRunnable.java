package bkcraft.bedwars.game.spawner;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpawnerRunnable extends BukkitRunnable {

    public HashMap<Material, Double> delay;
    public HashMap<Material, Long> lastSpawn;
    public HashMap<Material, Integer> maxMaterials;
    Location location;

    boolean dropNaturally;

    public SpawnerRunnable(Location location, HashMap<Material, Double> delay, HashMap<Material, Integer> maxMaterials,
	    boolean dropNaturally) {
	this.location = location.add(0.5, 0, 0.5);
	this.delay = delay;
	this.maxMaterials = maxMaterials;
	
	this.dropNaturally = dropNaturally;

	this.lastSpawn = new HashMap<Material, Long>();

	for (Entry<Material, Double> entry : delay.entrySet()) {
	    this.lastSpawn.put(entry.getKey(), System.currentTimeMillis());
	}
    }

    @Override
    public void run() {
	for (Entry<Material, Long> entry : this.lastSpawn.entrySet()) {
	    if (!(maxMaterials.containsKey(entry.getKey())
		    || getCount(entry.getKey()) > maxMaterials.get(entry.getKey())))
		return;

	    if (System.currentTimeMillis() - this.delay.get(entry.getKey()) > entry.getValue()) {
		this.lastSpawn.put(entry.getKey(), entry.getValue() + (long) (this.delay.get(entry.getKey()) * 1000));
		if (this.dropNaturally) {
		    this.location.getWorld().dropItemNaturally(this.location, new ItemStack(entry.getKey()));
		} else {
		    Item item = this.location.getWorld().dropItem(this.location, new ItemStack(entry.getKey()));
		    item.setVelocity(new Vector(0, 0, 0));
		}
	    }
	}
    }

    public Integer getCount(Material material) {
	Integer count = 0;
	for (Entity entity : location.getWorld().getEntities()) {
	    if (!(entity instanceof Item))
		continue;

	    Item item = (Item) entity;

	    if (item.getItemStack().getType() == material)
		count += item.getItemStack().getAmount();
	}

	return count;
    }
}
