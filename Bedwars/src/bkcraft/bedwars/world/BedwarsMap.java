package bkcraft.bedwars.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import bkcraft.bedwars.game.Team;
import bkcraft.bedwars.game.spawner.DiamondSpawner;
import bkcraft.bedwars.game.spawner.EmeraldSpawner;
import bkcraft.bedwars.game.spawner.IslandSpawner;
import bkcraft.bedwars.game.spawner.Spawner;
import bkcraft.bedwars.world.schematic.Region;
import bkcraft.bedwars.world.schematic.SchematicReader;

public class BedwarsMap {

    public World world;
    public final int MAP_HEIGHT = 64;
    public final int CAGE_HEIGHT = 128;
    public final int SPAWN_HEIGHT = 131;

    String template;

    public int minX;
    public int minY;
    public int minZ;

    public int TEAM_COUNT;

    public ArrayList<Spawner> spawner;
    public HashMap<Team, IslandSpawner> islandSpawner;

    public HashMap<Team, Location> spawns;
    public HashMap<Team, Location[]> beds;

    public HashMap<Team, Location> shops;
    public HashMap<Team, Location> upgradeShops;

    public short[][][] blocks;

    public BedwarsMap(String worldName) {
	world = MapUtils.createEmpthy(worldName);

	this.TEAM_COUNT = 0;

	this.spawner = new ArrayList<Spawner>();
	this.islandSpawner = new HashMap<Team, IslandSpawner>();

	this.spawns = new HashMap<Team, Location>();
	this.beds = new HashMap<Team, Location[]>();

	this.shops = new HashMap<Team, Location>();
	this.upgradeShops = new HashMap<Team, Location>();
    }

    public Region addSchematic(File file, int height) {
	Region region;
	try {
	    region = SchematicReader.read(file);
	} catch (FileNotFoundException e) {
	    Bukkit.broadcastMessage("Schematic not found");
	    return null;
	} catch (IOException e) {
	    Bukkit.broadcastMessage("IO Exception while reading schematic");
	    e.printStackTrace();
	    return null;
	}

	MapUtils.placeRegion(this.world, region, -(region.width / 2), height, -(region.length / 2));

	return region;
    }

    public Region addSchematic(String path, int height) {
	return addSchematic(new File(path), height);
    }

    public void addTemplate(File file) {
	Region region = addSchematic(file, MAP_HEIGHT);

	Bukkit.broadcastMessage("" + (region == null));

	this.minX = -(region.width / 2);
	this.minY = MAP_HEIGHT;
	this.minZ = -(region.length / 2);

	this.blocks = region.getBlocks();

	// Removes file extension (.schem)
	this.template = file.getName().replaceFirst("[.][^.]+$", "");
	;
    }

    public void addCage() {
	addSchematic(FilePath.CAGE_SCHEM, CAGE_HEIGHT);
    }

    public boolean removeCage() {
	Region region;
	try {
	    region = SchematicReader.read(new File(FilePath.CAGE_SCHEM));
	} catch (IOException e) {
	    e.printStackTrace();
	    return false;
	}

	for (int x = 0; x < region.width; x++) {
	    for (int y = 0; y < region.length; y++) {
		for (int z = 0; z < region.length; z++) {
		    this.world.getBlockAt(x - (region.width / 2), y + CAGE_HEIGHT, z - (region.length / 2))
			    .setType(Material.AIR);
		}
	    }
	}
	return true;
    }

    public boolean isMap(Location location) {
	if (!location.getWorld().equals(this.world)) {
	    return false;
	}

	int locX = location.getBlockX();
	int locY = location.getBlockY();
	int locZ = location.getBlockZ();

	if ((locX - this.minX) > this.blocks.length || (locY - this.minY) > this.blocks[0].length
		|| (locZ - this.minZ) > this.blocks[0][0].length || (locX - this.minX) < 0 || (locY - this.minY) < 0
		|| (locZ - this.minZ < 0))
	    return false;

	return this.blocks[locX - this.minX][locY - this.minY][locZ - this.minZ] != 0;
    }

    public void readInformation() {
	File file = new File(FilePath.MAPS_FOLDER + "/" + this.template + ".txt");
	BufferedReader reader;

	try {
	    reader = new BufferedReader(new FileReader(file));

	    for (String line; (line = reader.readLine()) != null;) {
		String[] args = line.split(",");
		switch (args[0]) {
		case "TEAMS":
		    this.TEAM_COUNT = Integer.parseInt(args[1]);
		    break;
		case "SPAWNER":
		    switch (args[1]) {
		    case "EMERALD":
			this.spawner.add(new EmeraldSpawner(new Location(this.world, Integer.parseInt(args[2]),
				Integer.parseInt(args[3]), Integer.parseInt(args[4]))));
			break;
		    case "DIAMOND":
			this.spawner.add(new DiamondSpawner(new Location(this.world, Integer.parseInt(args[2]),
				Integer.parseInt(args[3]), Integer.parseInt(args[4]))));
			break;
		    }
		}

		if (Team.contains(args[0])) {
		    Team team = Team.valueOf(args[0]);

		    switch (args[1]) {
		    case "SPAWN":
			this.spawns.put(team, new Location(this.world, Integer.parseInt(args[2]) + 0.5,
				Integer.parseInt(args[3]), Integer.parseInt(args[4]) + 0.5));
			break;
		    case "SPAWNDIREC":
			this.spawns.get(team).setYaw(getSpawnDirec(Integer.parseInt(args[2])));
			break;
		    case "SPAWNER":
			this.islandSpawner.put(team, new IslandSpawner(new Location(this.world,
				Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]))));
			break;
		    case "BEDHEAD":
			if (!this.beds.containsKey(team)) {
			    this.beds.put(team, new Location[2]);
			}

			Location[] bedlocsh = this.beds.get(team);
			bedlocsh[0] = new Location(this.world, Integer.parseInt(args[2]), Integer.parseInt(args[3]),
				Integer.parseInt(args[4]));
			this.beds.put(team, bedlocsh);

			break;
		    case "BEDFOOT":
			if (!this.beds.containsKey(team)) {
			    this.beds.put(team, new Location[2]);
			}

			Location[] bedlocsf = this.beds.get(team);
			bedlocsf[1] = new Location(this.world, Integer.parseInt(args[2]), Integer.parseInt(args[3]),
				Integer.parseInt(args[4]));
			this.beds.put(team, bedlocsf);

			break;
		    case "SHOP":
			this.shops.put(team, new Location(this.world, Integer.parseInt(args[2]),
				Integer.parseInt(args[3]), Integer.parseInt(args[4])).add(0.5, 0, 0.5));
			break;
		    case "SHOPDIREC":
			this.shops.get(team).setYaw(getSpawnDirec(Integer.parseInt(args[2])));
			break;
		    case "UPGRADES":
			this.upgradeShops.put(team, new Location(this.world, Integer.parseInt(args[2]),
				Integer.parseInt(args[3]), Integer.parseInt(args[4])).add(0.5, 0, 0.5));
			break;
		    case "UPGRADESDIREC":
			this.upgradeShops.get(team).setYaw(getSpawnDirec(Integer.parseInt(args[2])));
			break;
		    }
		}

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Integer getSpawnDirec(Integer configDirec) {
	if (configDirec == 0) {
	    return -90;
	} else if (configDirec == 1) {
	    return 0;
	} else if (configDirec == 2) {
	    return 90;
	} else if (configDirec == 3) {
	    return 180;
	}
	return null;
    }

    public Team getBed(Location loc) {
	for (Entry<Team, Location[]> entry : this.beds.entrySet()) {
	    if (entry.getValue()[0].equals(loc) || entry.getValue()[1].equals(loc)) {
		return entry.getKey();
	    }
	}
	return null;
    }

    public void startSpawner() {
	for (Spawner spawner : this.spawner) {
	    spawner.startSpawner();
	}

	for (Entry<Team, IslandSpawner> entry : this.islandSpawner.entrySet()) {
	    entry.getValue().startSpawner();
	}
    }

    public void spawnVillager(HashMap<Team, Location> villagers, String name) {
	for (Entry<Team, Location> entry : villagers.entrySet()) {
	    Villager villager = entry.getValue().getWorld().spawn(entry.getValue(), Villager.class);
	    villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255));
	    villager.setProfession(Profession.FARMER);
	    villager.setNoDamageTicks(Integer.MAX_VALUE);
	    villager.setCustomName(name);
	}
    }
}
