package bkcraft.bedwars.game.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Generator {

    World world;
    Location location;
    ArrayList<GeneratorLevel> generatorLevels;
    HashMap<Material, Long> lastDropped = new HashMap<Material, Long>();
    
    int level = 0;
    
    boolean dropNaturally;
    
    public Generator(Location location, GeneratorType type) {
	world = location.getWorld();
	this.location = location.add(0.5, 0, 0.5);
	
	if(type == GeneratorType.TEAM) {
	    this.dropNaturally = true;
	} else if (type == GeneratorType.DIAMOND) {
	    this.dropNaturally = false;
	} else if (type == GeneratorType.EMERALD) {
	    this.dropNaturally = false;
	}
    }
    
    public Generator(Location location) {
	world = location.getWorld();
	this.location = location.add(0.5, 0, 0.5);
    }
    
    public ArrayList<GeneratorLevel> getGeneratorLevels() {
	return this.generatorLevels;
    }
    
    public GeneratorLevel getCurrentLevel() {
	return this.generatorLevels.get(level);
    }
    
    public void setDelays(ArrayList<GeneratorLevel> generatorLevels) {
	this.generatorLevels = generatorLevels;
	for(Entry<Material, Integer> entry : this.generatorLevels.get(level).delay.entrySet()) {
	    lastDropped.put(entry.getKey(), 0L);
	}
    }
    
    public void tick(long time) {
	updateDisplay(time);
	dropItems(time);
    }
    
    public void dropItems(long time) {
	for(Entry<Material, Long> entry : lastDropped.entrySet()) {
	    if((time - entry.getValue()) > getCurrentLevel().delay.get(entry.getKey())) {
		dropItem(entry.getKey());
		entry.setValue(time);
	    }
	}
    }
    
    private void dropItem(Material material) {
	ItemStack itemStack = new ItemStack(material, 1);
	if(dropNaturally) {
	    world.dropItemNaturally(location, itemStack).setCustomName("GENERATOR");
	} else {
	    Item item = world.dropItem(location, itemStack);
	    item.setCustomName("GENERATOR");
	    item.setVelocity(new Vector(0, 0, 0));
	}
    }
    
    private void updateDisplay(long time) {
	// Update time on hologram, if enabled
    }
    
}
