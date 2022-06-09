package bkcraft.bedwars;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import bkcraft.bedwars.commands.GameCmd;
import bkcraft.bedwars.events.BlockHandler;
import bkcraft.bedwars.events.ChatHandler;
import bkcraft.bedwars.events.DeathHandler;
import bkcraft.bedwars.events.FoodHandler;
import bkcraft.bedwars.events.InventoryHandler;
import bkcraft.bedwars.events.JoinHandler;
import bkcraft.bedwars.events.VillagerHandler;
import bkcraft.bedwars.game.Game;
import bkcraft.bedwars.game.shop.items.potions.InvisibilityPotionBWI;
import bkcraft.bedwars.game.shop.items.utils.BedbugBWI;
import bkcraft.bedwars.game.shop.items.utils.BridgeeggBWI;
import bkcraft.bedwars.game.shop.items.utils.DreamDefenderBWI;
import bkcraft.bedwars.game.shop.items.utils.FireballBWI;
import bkcraft.bedwars.game.shop.items.utils.MagicMilkBWI;
import bkcraft.bedwars.game.shop.items.utils.SpongeBWI;
import bkcraft.bedwars.game.shop.items.utils.TNTBWI;
import bkcraft.bedwars.game.shop.items.utils.WaterbucketBWI;
import bkcraft.bedwars.world.FilePath;
import bkcraft.bedwars.world.MapManager;
import bkcraft.bedwars.world.MapUtils;
import bkcraft.bedwars.world.ResourceExtractor;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static Random random;

    public MapManager mapManager;
    public Game game;

    @Override
    public void onEnable() {
	plugin = this;
	random = new Random();

	this.getServer().getPluginManager().registerEvents(new JoinHandler(), this);
	this.getServer().getPluginManager().registerEvents(new ChatHandler(), this);
	this.getServer().getPluginManager().registerEvents(new BlockHandler(), this);
	this.getServer().getPluginManager().registerEvents(new FoodHandler(), this);
	this.getServer().getPluginManager().registerEvents(new VillagerHandler(), this);
	this.getServer().getPluginManager().registerEvents(new InventoryHandler(), this);

	this.getServer().getPluginManager().registerEvents(new InvisibilityPotionBWI(), this);
	
	this.getServer().getPluginManager().registerEvents(new BedbugBWI(), this);
	this.getServer().getPluginManager().registerEvents(new DreamDefenderBWI(), this);
	this.getServer().getPluginManager().registerEvents(new FireballBWI(), this);
	this.getServer().getPluginManager().registerEvents(new TNTBWI(), this);
	this.getServer().getPluginManager().registerEvents(new WaterbucketBWI(), this);
	this.getServer().getPluginManager().registerEvents(new BridgeeggBWI(), this);
	this.getServer().getPluginManager().registerEvents(new MagicMilkBWI(), this);
	this.getServer().getPluginManager().registerEvents(new SpongeBWI(), this);
	
	this.getServer().getPluginManager().registerEvents(new DeathHandler(), this);
	
	this.getCommand("game").setExecutor(new GameCmd());

	FilePath.init();

	File mapFolder = new File(FilePath.MAPS_FOLDER);

	if (!mapFolder.exists()) {
	    ResourceExtractor resourceExtractor = new ResourceExtractor(this, getDataFolder(), "resources/", null);
	    try {
		resourceExtractor.extract();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	this.mapManager = new MapManager(this);
	this.game = new Game(this.mapManager.createMap());

    }

    @Override
    public void onDisable() {
	for (String worldName : this.mapManager.usedWorlds) {
	    if (Bukkit.getWorld(worldName) != null) {
		File worldFolder = Bukkit.getWorld(worldName).getWorldFolder();
		Bukkit.unloadWorld(worldName, false);
		MapUtils.deleteFolderContent(worldFolder);
	    }
	}
    }
}
