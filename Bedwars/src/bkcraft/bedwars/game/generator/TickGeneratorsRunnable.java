package bkcraft.bedwars.game.generator;

import org.bukkit.scheduler.BukkitRunnable;

public class TickGeneratorsRunnable extends BukkitRunnable {

    GeneratorManager manager;
    
    public TickGeneratorsRunnable(GeneratorManager manager) {
	this.manager = manager;
    }
    
    @Override
    public void run() {
	this.manager.tick();
    }

}
