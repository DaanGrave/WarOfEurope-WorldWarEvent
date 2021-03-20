package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    private final EventPlugin eventPlugin;

    public ChatListener(EventPlugin eventPlugin) {
        this.eventPlugin = eventPlugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
    }
}
