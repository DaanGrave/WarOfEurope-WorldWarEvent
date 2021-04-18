package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import nl.warofeurope.event.utils.runnables.SyncTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;

public class JoinListener implements Listener {
    private final EventPlugin eventPlugin;

    public JoinListener(EventPlugin eventPlugin) {
        this.eventPlugin = eventPlugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        player.setScoreboard(this.eventPlugin.scoreboardHandler.scoreboard);

        if (eventPlugin.game.started){
            player.setGameMode(GameMode.SPECTATOR);
        } else {
            if (!player.hasPermission("eventbypass")){
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setFireTicks(0);
                player.setGameMode(GameMode.ADVENTURE);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit event " + player.getName());

                Scoreboard scoreboard = EventPlugin.getInstance().scoreboardHandler.scoreboard;
                Team red = scoreboard.getTeam("red");
                Team green = scoreboard.getTeam("green");

                Location redSpawn = new Location(Bukkit.getWorld("world"), -194.5, 76, -1.5, -90, 0);
                Location greenSpawn = new Location(Bukkit.getWorld("world"), 330.5, 75, -8.5, 90, 0);

                Set<String> kingdoms = EventPlugin.getInstance().getConfig().getConfigurationSection("kingdoms").getKeys(false);
                for (String kingdom : kingdoms){
                    if (player.hasPermission("group." + kingdom)){
                        if (EventPlugin.getInstance().getConfig().getInt("kingdoms." + kingdom) == 1){
                            new SyncTask(() -> {
                                green.addEntry(player.getName());
                                player.teleport(greenSpawn);
                            });
                        } else {
                            new SyncTask(() -> {
                                red.addEntry(player.getName());
                                player.teleport(redSpawn);
                            });
                        }

                        break;
                    }
                }
            } else {
                player.setGameMode(GameMode.SPECTATOR);
                player.teleport(new Location(Bukkit.getWorld("world"), 71.5, 81, -11.5, 180, 0));
            }
        }
    }
}
