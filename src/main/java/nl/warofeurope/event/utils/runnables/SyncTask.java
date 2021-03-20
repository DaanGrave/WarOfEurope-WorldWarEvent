package nl.warofeurope.event.utils.runnables;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.Bukkit;

public class SyncTask {
    public SyncTask(Runnable runnable){
        Bukkit.getScheduler().runTask(EventPlugin.getInstance(), runnable);
    }
}
