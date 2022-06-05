package bkcraft.bedwars.game;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import bkcraft.bedwars.game.title.LegacyTitle;

public class StartCountdownRunnable extends BukkitRunnable{

	Game game;
	int countdown;
	
	public StartCountdownRunnable(Game game) {
		this.game = game;
		this.countdown = 10;
	}
	
	@Override
	public void run() {
		if(this.countdown == 0) {
			this.game.start();
			this.cancel();
		}
		
		if(this.countdown == 10 || this.countdown <= 5) {
			for(Player player : this.game.teamManager.getPlayer()) {
				player.sendMessage(ChatColor.YELLOW + "The Game starts in " + ChatColor.RED + this.countdown + ChatColor.YELLOW + " seconds!");
				
				LegacyTitle title = new LegacyTitle();
				title.send(player, ChatColor.RED + String.valueOf(this.countdown), "", 0, 20, 0);
				
				player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
			}
		}
		this.countdown--;
	}

}
