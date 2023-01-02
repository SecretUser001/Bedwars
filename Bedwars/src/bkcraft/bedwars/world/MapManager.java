package bkcraft.bedwars.world;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import bkcraft.bedwars.Main;

public class MapManager {

    Main plugin;

    final String WORLD_PREFIX = "game";

    public ArrayList<String> usedWorlds;

    int worldNumber;

    public MapManager(Main plugin) {
	this.plugin = plugin;
	this.usedWorlds = new ArrayList<String>();

	this.worldNumber = 0;
    }

    public BedwarsMap createMap() {
	String worldName = newWorldName();
	this.usedWorlds.add(worldName);

	BedwarsMap map = new BedwarsMap(worldName);

	ArrayList<String> mapTemplates = getTemplateFiles();

	if (mapTemplates.size() == 0) {
	    Main.plugin.getLogger().log(Level.WARNING, "Cant find map templates");
	    return map;
	}

	File template = new File(mapTemplates.get(Main.random.nextInt(mapTemplates.size())));

	map.addTemplate(template);

	return map;
    }

    public String newWorldName() {
	this.worldNumber++;
	this.usedWorlds.add(WORLD_PREFIX + this.worldNumber);
	return WORLD_PREFIX + this.worldNumber;
    }

    public ArrayList<String> getTemplateFiles() {
	ArrayList<String> returnFiles = new ArrayList<String>();

	File mapFolder = new File(FilePaths.MAPS_FOLDER);

	for (String fileName : mapFolder.list()) {
	    if (fileName.endsWith(".schem")) {
		returnFiles.add(FilePaths.MAPS_FOLDER + fileName);
	    }
	}

	if (returnFiles.isEmpty()) {
	    Bukkit.broadcastMessage("No Maps");
	}

	return returnFiles;
    }
}
