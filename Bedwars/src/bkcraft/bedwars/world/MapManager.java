package bkcraft.bedwars.world;

import java.io.File;
import java.util.ArrayList;

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
			
		File mapFolder = new File(FilePath.MAPS_FOLDER);
			
		for(String fileName : mapFolder.list()) {
			if(fileName.endsWith(".schem")) {
				returnFiles.add(FilePath.MAPS_FOLDER + fileName);
			}
		}
		
		if(returnFiles.isEmpty()) {
			Bukkit.broadcastMessage("No Maps");
		}
				
		return returnFiles;
	}
}
