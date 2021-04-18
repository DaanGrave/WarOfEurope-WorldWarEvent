package nl.warofeurope.event.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static nl.warofeurope.event.utils.Colors.color;

public class ChatListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (!player.hasPermission("litebans.mutechat.bypass") && !player.hasPermission("eventbypass")){
            event.setCancelled(true);
            player.sendMessage(color("&cDe chat staat uit tijdens het event!"));
        }
    }
}
