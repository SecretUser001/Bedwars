package bkcraft.bedwars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherHadler implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
	event.setCancelled(true);
    }
    
    
    
}
