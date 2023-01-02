package bkcraft.bedwars.game.generator;

import java.util.HashMap;

import org.bukkit.Material;

public class GeneratorLevel {

    GeneratorType type;
    int level;
    HashMap<Material, Integer> delay;
    HashMap<Material, Integer> cost;
    int time;
    
    public GeneratorLevel(GeneratorType type, int level, HashMap<Material, Integer> delay, HashMap<Material, Integer> cost, int time) {
	this.type = type;
	this.level = level;
	this.delay = delay;
	this.cost = cost;
	this.time = time;
    }
}
