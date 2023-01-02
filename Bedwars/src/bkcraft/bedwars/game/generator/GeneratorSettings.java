package bkcraft.bedwars.game.generator;

import java.io.File;
import java.util.ArrayList;

import bkcraft.bedwars.world.FilePaths;

public class GeneratorSettings {
    private static ArrayList<GeneratorLevel> DEFAULTS_TEAM;
    private static ArrayList<GeneratorLevel> DEFAULTS_DIAMOND;
    private static ArrayList<GeneratorLevel> DEFAULTS_EMERALD;

    private ArrayList<GeneratorLevel> teamLevels;
    private ArrayList<GeneratorLevel> diamondLevels;
    private ArrayList<GeneratorLevel> emeraldLevels;

    public GeneratorSettings() {
	this.teamLevels = new ArrayList<GeneratorLevel>();
	this.diamondLevels = new ArrayList<GeneratorLevel>();
	this.emeraldLevels = new ArrayList<GeneratorLevel>();
    }

    public void addTeamLevel(GeneratorLevel generatorLevel) {
	this.teamLevels.add(generatorLevel);
    }

    public void addDiamondLevel(GeneratorLevel generatorLevel) {
	this.diamondLevels.add(generatorLevel);
    }

    public void addEmeraldLevel(GeneratorLevel generatorLevel) {
	this.emeraldLevels.add(generatorLevel);
    }

    public void finish() {
	if (!check(teamLevels))
	    this.teamLevels = DEFAULTS_TEAM;
	if (!check(diamondLevels))
	    this.diamondLevels = DEFAULTS_DIAMOND;
	if (!check(emeraldLevels))
	    this.emeraldLevels = DEFAULTS_EMERALD;
    }

    private boolean check(ArrayList<GeneratorLevel> levels) {	
	if (levels.size() == 0) {
	    return false;
	}
	    
	for (int i = 0; i < levels.size(); i++) {
	    if (levels.get(i).level != i) {
		return false;
	    }
	}
	
	return true;
    }

    public ArrayList<GeneratorLevel> getTeamLevels() {
	return this.teamLevels;
    }

    public ArrayList<GeneratorLevel> getDiamondLevels() {
	return this.diamondLevels;
    }

    public ArrayList<GeneratorLevel> getEmeraldLevels() {
	return this.emeraldLevels;
    }

    public static void init() {
	GeneratorSettings settings = new GeneratorSettings();
	GeneratorUtils.readFile(settings, new File(FilePaths.GENERATOR_DEFAULTS));

	DEFAULTS_TEAM = settings.teamLevels;
	DEFAULTS_DIAMOND = settings.diamondLevels;
	DEFAULTS_EMERALD = settings.emeraldLevels;	
    }
}
