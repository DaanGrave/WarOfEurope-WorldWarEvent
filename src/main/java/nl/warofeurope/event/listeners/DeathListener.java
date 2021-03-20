package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import nl.warofeurope.event.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Arrays;
import java.util.stream.Collectors;

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

        for (ScoreboardHandler.Teams teams : ScoreboardHandler.Teams.getValues()){
            teams.getPlayers().remove(entity);
        }

        if (entity.getKiller() != null){
            event.setDeathMessage(color("&c" + entity.getName() + " &7is dood gegaan door &c" + entity.getKiller().getName() + "&7."));

            for (ScoreboardHandler.Teams teams : ScoreboardHandler.Teams.getValues()){
                if (teams.getPlayers().contains(entity.getKiller())){
                    teams.addKill();
                    break;
                }
            }
            this.eventPlugin.scoreboardHandler.updateScoreboard();
        } else {
            event.setDeathMessage(null);
        }
    }

    private boolean didWin(ScoreboardHandler.Teams teams){
        return Arrays.stream(ScoreboardHandler.Teams.getValues()).filter(i -> teams.getPlayers().size() > 0).count() == 1;
    }
}
