package bkcraft.bedwars.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import bkcraft.bedwars.Main;
import bkcraft.bedwars.game.Game;

public class GameCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		switch (args.length) {
		case 0:
			sender.sendMessage("/game");
			break;
		case 1:
			switch (args[0].toLowerCase()) {
			case "new":
				Main.plugin.game = new Game(Main.plugin.mapManager.createMap());
				for (Player player : Bukkit.getOnlinePlayers()) {
					Main.plugin.game.addPlayer(player);
				}
			case "start":
				Main.plugin.game.startCountdown();
			}
		}

		return false;
	}

}
