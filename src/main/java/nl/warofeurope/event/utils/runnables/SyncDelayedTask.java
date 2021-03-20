package nl.warofeurope.event.utils.runnables;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.Bukkit;

public class SyncDelayedTask {
    public SyncDelayedTask(int ticks, Runnable runnable){
        Bukkit.getScheduler().runTaskLater(EventPlugin.getInstance(), runnable, ticks);
    }
}
