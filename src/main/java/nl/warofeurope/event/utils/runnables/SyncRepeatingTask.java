package nl.warofeurope.event.utils.runnables;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.Bukkit;

public class SyncRepeatingTask {
    public SyncRepeatingTask(int ticks, Runnable runnable){
        Bukkit.getScheduler().runTaskTimer(EventPlugin.getInstance(), runnable, 0L, ticks);
    }
}
