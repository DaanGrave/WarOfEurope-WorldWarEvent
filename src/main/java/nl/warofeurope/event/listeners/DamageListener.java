package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {
    @EventHandler
    public void onDamageRandom(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            if (!EventPlugin.getInstance().game.started){
                event.setCancelled(true);
            }
        }
    }
}
