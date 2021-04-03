package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

import static nl.warofeurope.event.utils.Colors.color;

public class DeathListener implements Listener {
    private final EventPlugin eventPlugin;

    public DeathListener(EventPlugin eventPlugin) {
        this.eventPlugin = eventPlugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player entity = event.getEntity();
        entity.setGameMode(GameMode.SPECTATOR);

        if (entity.getKiller() != null){
            event.setDeathMessage(color("&c" + entity.getName() + " &7is dood gegaan door &c" + entity.getKiller().getName() + "&7."));

            Player killerPlayer = entity.getKiller();
            UUID uniqueId = killerPlayer.getUniqueId();
            this.eventPlugin.scoreboardHandler.playerKills.putIfAbsent(uniqueId, 0);
            this.eventPlugin.scoreboardHandler.playerKills.replace(uniqueId, this.eventPlugin.scoreboardHandler.playerKills.getOrDefault(uniqueId, 1) + 1);
            this.eventPlugin.scoreboardHandler.updateScoreboard();
        } else {
            event.setDeathMessage(null);
        }
    }
}
