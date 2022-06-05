package bkcraft.bedwars.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import bkcraft.bedwars.Main;

public class BlockHandler implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getBlock().getType().equals(Material.BED_BLOCK)) {
			Bukkit.broadcastMessage("bbed");
			Bukkit.broadcastMessage(Main.plugin.game.bedwarsMap.getBed(event.getBlock().getLocation()).toString());
			Bukkit.broadcastMessage(
					Main.plugin.game.teamManager.playerData.get(event.getPlayer()).getTeam().toString());
			Bukkit.broadcastMessage(String.valueOf(Main.plugin.game.bedwarsMap.getBed(event.getBlock().getLocation())
					.equals(Main.plugin.game.teamManager.playerData.get(event.getPlayer()).getTeam())));

			if (Main.plugin.game.bedwarsMap.getBed(event.getBlock().getLocation())
					.equals(Main.plugin.game.teamManager.playerData.get(event.getPlayer()).getTeam())) {
				Bukkit.broadcastMessage("own bed");
				event.setCancelled(true);
			} else {
				event.getBlock().getDrops().clear();
				Bukkit.broadcastMessage("dr " + event.getBlock().getDrops());
				Main.plugin.game.breakBed(Main.plugin.game.bedwarsMap.getBed(event.getBlock().getLocation()));
				event.setCancelled(false);
			}
		} else if (Main.plugin.game.bedwarsMap.isMap(event.getBlock().getLocation())) {
			event.setCancelled(true);
		}
	}

}
