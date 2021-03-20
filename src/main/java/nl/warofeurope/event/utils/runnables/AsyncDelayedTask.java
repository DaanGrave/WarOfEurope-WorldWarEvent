package nl.warofeurope.event.utils.runnables;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.Bukkit;

public class AsyncDelayedTask {
    public AsyncDelayedTask(int ticks, Runnable runnable){
        Bukkit.getScheduler().runTaskLaterAsynchronously(EventPlugin.getInstance(), runnable, ticks);
    }
}
