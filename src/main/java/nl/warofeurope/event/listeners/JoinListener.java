package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final EventPlugin eventPlugin;

    public JoinListener(EventPlugin eventPlugin) {
        this.eventPlugin = eventPlugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        player.setScoreboard(this.eventPlugin.scoreboardHandler.scoreboard);
        this.eventPlugin.scoreboardHandler.playerKills.putIfAbsent(player.getUniqueId(), 0);

        if (eventPlugin.game.started){
            player.setGameMode(GameMode.SPECTATOR);
        } else {
            if (!player.hasPermission("eventbypass")){
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setGameMode(GameMode.ADVENTURE);
                player.teleport(new Location(Bukkit.getWorld("world"), -39, 68, 51, 0, 0));
            }
            this.eventPlugin.scoreboardHandler.updateScoreboard();
        }
    }
}
