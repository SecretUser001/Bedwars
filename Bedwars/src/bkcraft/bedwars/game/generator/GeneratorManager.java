package bkcraft.bedwars.game.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Team;

public class GeneratorManager {
  
    GeneratorSettings settings;
    
    HashMap<Team, Generator> teamGenerators;
    ArrayList<Generator> diamondGenerators;
    ArrayList<Generator> emeraldGenerators;
    
    BukkitTask runnable;
    
    public GeneratorManager() {
	this.settings = new GeneratorSettings();
	this.teamGenerators = new HashMap<Team, Generator>();
	this.diamondGenerators = new ArrayList<Generator>();
	this.emeraldGenerators = new ArrayList<Generator>();
    }
    
    public GeneratorSettings getSettings() {
	return this.settings;
    }
    
    public void applySettings() {
	this.settings.finish();
	this.setTeamDelays(this.settings.getTeamLevels());
	this.setDiamondDelays(this.settings.getDiamondLevels());
	this.setEmeraldDelays(this.settings.getEmeraldLevels());
    }
    
    public void run() {
	this.runnable = new TickGeneratorsRunnable(this).runTaskTimer(Main.plugin, 1, 1);
    }
    
    public void tick() {
	long time = System.currentTimeMillis();
	
	for(Entry<Team, Generator> entry : teamGenerators.entrySet()) {
	    entry.getValue().tick(time);
	}
	
	for(Generator generator : diamondGenerators) {
	    generator.tick(time);
	}
	
	for(Generator generator : emeraldGenerators) {
	    generator.tick(time);
	}
    }
    
    public void addIslandGenerator(Team team, Location location) {
	this.teamGenerators.put(team, new Generator(location));
    }
    
    public void addDiamondGenerator(Location location) {
	this.diamondGenerators.add(new Generator(location));
    }
    
    public void addEmeraldGenerator(Location location) {
	this.emeraldGenerators.add(new Generator(location));
    }
    
    private void setTeamDelays(ArrayList<GeneratorLevel> generatorLevels) {
	for(Entry<Team, Generator> entry : teamGenerators.entrySet()) {
	    entry.getValue().setDelays(generatorLevels);
	}
    }
    
    private void setDiamondDelays(ArrayList<GeneratorLevel> generatorLevels) {
	for(Generator generator : diamondGenerators) {
	    generator.setDelays(generatorLevels);
	}
    }
    
    private void setEmeraldDelays(ArrayList<GeneratorLevel> generatorLevels) {
	for(Generator generator : emeraldGenerators) {
	    generator.setDelays(generatorLevels);
	}
    }
}
