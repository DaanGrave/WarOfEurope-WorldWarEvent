package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangeListener implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){
        if (!EventPlugin.getInstance().game.started){
            event.setCancelled(true);
            if (event.getFoodLevel() != 20){
                event.setFoodLevel(20);
            }
        }
    }
}
