package nl.warofeurope.event.utils.runnables;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.Bukkit;

public class AsyncTask {
    public AsyncTask(Runnable runnable){
        Bukkit.getScheduler().runTaskAsynchronously(EventPlugin.getInstance(), runnable);
    }
}
