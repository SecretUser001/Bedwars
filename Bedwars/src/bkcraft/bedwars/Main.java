package bkcraft.bedwars;

import java.io.File;
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
import bkcraft.bedwars.game.shop.items.FireballBWI;
import bkcraft.bedwars.world.FilePath;
import bkcraft.bedwars.world.MapManager;
import bkcraft.bedwars.world.MapUtils;

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
		this.getServer().getPluginManager().registerEvents(new DeathHandler(), this);
		this.getServer().getPluginManager().registerEvents(new FoodHandler(), this);
		this.getServer().getPluginManager().registerEvents(new VillagerHandler(), this);
		this.getServer().getPluginManager().registerEvents(new InventoryHandler(), this);
		this.getServer().getPluginManager().registerEvents(new FireballBWI(), this);

		this.getCommand("game").setExecutor(new GameCmd());

		FilePath.init();

		File mapFolder = new File(FilePath.MAPS_FOLDER);

		if (!mapFolder.exists()) {
			mapFolder.mkdirs();
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
