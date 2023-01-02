package bkcraft.bedwars.world;

import bkcraft.bedwars.Main;

public class FilePaths {

    public static String DATA_FOLDER;
    public static String SCHEMATICS_FOLDER;
    public static String MAPS_FOLDER;
    public static String CAGE_SCHEM;
    public static String GENERATOR_DEFAULTS;
    
    public static void init() {
	DATA_FOLDER = Main.plugin.getDataFolder().getAbsolutePath();
	SCHEMATICS_FOLDER = DATA_FOLDER + "/schematics/";
	MAPS_FOLDER = SCHEMATICS_FOLDER + "/maps/";
	CAGE_SCHEM = SCHEMATICS_FOLDER + "/cage.schem";
	
	GENERATOR_DEFAULTS = DATA_FOLDER + "/generator_defaults.txt";
    }
}
