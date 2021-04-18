package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;

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
        Scoreboard scoreboard = this.eventPlugin.scoreboardHandler.scoreboard;
        scoreboard.getTeam("red").removeEntry(entity.getName());
        scoreboard.getTeam("green").removeEntry(entity.getName());

        if (entity.getKiller() != null){
            event.setDeathMessage(color("&c" + entity.getName() + " &7is dood gegaan door &c" + entity.getKiller().getName() + "&7."));

            this.eventPlugin.scoreboardHandler.updateScoreboard();
        } else {
            event.setDeathMessage(null);
        }
    }
}
