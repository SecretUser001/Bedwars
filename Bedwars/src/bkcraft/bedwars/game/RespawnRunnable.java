package bkcraft.bedwars.game;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import bkcraft.bedwars.game.title.LegacyTitle;

public class RespawnRunnable extends BukkitRunnable {

	Game game;
	Player player;
	int countdown;

	public RespawnRunnable(Game game, Player player) {
		this.game = game;
		this.player = player;
		this.countdown = 5;
	}

	@Override
	public void run() {
		if (this.countdown == 0) {
			this.game.respawn(this.player);
			this.cancel();
			return;
		}

		String message = ChatColor.YELLOW + "You will respawn in " + ChatColor.RED + this.countdown + ChatColor.YELLOW
				+ " seconds!";

		player.sendMessage(message);

		LegacyTitle title = new LegacyTitle();
		title.send(player, ChatColor.RED + "YOU DIED!", message, 0, 20, 0);

		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);

		this.countdown--;
	}
}
