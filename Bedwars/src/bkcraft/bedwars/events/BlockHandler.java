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
	    if (Main.plugin.game.bedwarsMap.getBed(event.getBlock().getLocation())
		    .equals(Main.plugin.game.teamManager.playerData.get(event.getPlayer()).getTeam())) {
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
