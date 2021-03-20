package nl.warofeurope.event.utils.runnables;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.Bukkit;

public class AsyncRepeatingTask {
    public AsyncRepeatingTask(int ticks, Runnable runnable){
        Bukkit.getScheduler().runTaskTimerAsynchronously(EventPlugin.getInstance(), runnable, 0L, ticks);
    }
}
