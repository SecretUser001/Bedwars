package bkcraft.bedwars.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Game;

public class BlockHandler implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
	Game game = Main.plugin.getGame();
	if (event.getBlock().getType().equals(Material.BED_BLOCK)) {
	    if (game.getBedwarsMap().getBed(event.getBlock().getLocation())
		    .equals(game.getTeamManager().getPlayerData(event.getPlayer()).getTeam())) {
		event.setCancelled(true);
	    } else {
		event.getBlock().getDrops().clear();
		Bukkit.broadcastMessage("dr " + event.getBlock().getDrops());
		game.breakBed(game.getBedwarsMap().getBed(event.getBlock().getLocation()));
		event.setCancelled(false);
	    }
	} else if (game.getBedwarsMap().isMap(event.getBlock().getLocation())) {
	    event.setCancelled(true);
	}
    }

}
