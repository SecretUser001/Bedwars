package bkcraft.bedwars.game.shop.upgrades;

import java.util.HashMap;

public class UpgradeManager {

    private HashMap<TeamUpgrade, Upgrade> upgrades;
    
    public UpgradeManager() {
	this.upgrades = new HashMap<>();
	
	this.upgrades.put(TeamUpgrade.SHARPNESS, new SharpnessBWU());
    }
    
    public HashMap<TeamUpgrade, Upgrade> getUpgrades() {
	return this.upgrades;
    }
    
}
