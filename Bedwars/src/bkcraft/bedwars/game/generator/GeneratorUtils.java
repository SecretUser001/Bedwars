package bkcraft.bedwars.game.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Material;

public class GeneratorUtils {
        
    public static void readFile(GeneratorSettings settings, File file) {
	BufferedReader reader;

	try {
	    reader = new BufferedReader(new FileReader(file));
	    
	    for (String line; (line = reader.readLine()) != null;) {
		String[] parts = line.split(";");
				
		if(!(parts[0].equals("GENERATOR"))) {
		    continue;
		}
				
		//GENERATOR;type;LEVEL;level;delays;buyprice;time
				
		GeneratorType type = GeneratorType.valueOf(parts[1]);
		int level = Integer.parseInt(parts[3]);
		HashMap<Material, Integer> delays = new HashMap<Material, Integer>();
		HashMap<Material, Integer> cost = new HashMap<Material, Integer>();
		int time = Integer.parseInt(parts[6]);
				
		for(String item : parts[4].split(",")) {
		    //Converting ticks to milliseconds	(*50)
		    delays.put(Material.valueOf(item.split(":")[0]), Integer.parseInt(item.split(":")[1]) * 50);
		}
		
		if(!parts[5].equals("0")) {
		    for(String item : parts[5].split(",")) {
			cost.put(Material.valueOf(item.split(":")[0]), Integer.parseInt(item.split(":")[1]));
		    }
		}
		
		if(type == GeneratorType.TEAM) {
		    settings.addTeamLevel(new GeneratorLevel(type, level, delays, cost, time));
		} else if(type == GeneratorType.DIAMOND) {
		    settings.addDiamondLevel(new GeneratorLevel(type, level, delays, cost, time));
		} else if(type == GeneratorType.EMERALD) {
		    settings.addEmeraldLevel(new GeneratorLevel(type, level, delays, cost, time));
		}
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
