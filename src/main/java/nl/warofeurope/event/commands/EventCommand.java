package nl.warofeurope.event.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import nl.warofeurope.event.EventPlugin;
import nl.warofeurope.event.models.Game;
import nl.warofeurope.event.utils.runnables.AsyncTask;
import nl.warofeurope.event.utils.runnables.SyncDelayedTask;
import nl.warofeurope.event.utils.runnables.SyncTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static nl.warofeurope.event.utils.Colors.color;

@CommandAlias("event")
@CommandPermission("event.event")
@Description("Main event command")
public class EventCommand extends BaseCommand {
    private final EventPlugin eventPlugin;

    public EventCommand(EventPlugin eventPlugin) {
        this.eventPlugin = eventPlugin;
    }

    @Subcommand("start")
    @CommandAlias("startevent")
    @CommandPermission("event.startevent")
    public void start(CommandSender sender){
        this.broadcast("&4&l[Event] &cDe WereldOorlog begint over &4&l1 &cminuut!");
        new SyncDelayedTask(20 * 30, () -> {
            this.broadcast("&4&l[Event] &cDe WereldOorlog begint over &4&l30 &cseconden!");
            new SyncDelayedTask(20 * 10, () -> {
                this.broadcast("&4&l[Event] &cDe WereldOorlog begint over &4&l10 &cseconden!");
                new SyncDelayedTask(20 * 5, () -> {
                    this.broadcast("&4&l[Event] &cDe WereldOorlog begint over &4&l5 &cseconden!");
                    new SyncDelayedTask(20 * 3, () -> {
                        this.broadcast("&4&l[Event] &cDe WereldOorlog begint over &4&l3 &cseconden!");
                        new SyncDelayedTask(20 * 2, () -> {
                            this.broadcast("&4&l[Event] &cDe WereldOorlog begint over &4&l2 &cseconden!");
                            new SyncDelayedTask(20, () -> {
                                this.broadcast("&4&l[Event] &cDe WereldOorlog begint over &4&l1 &cseconden!");
                                new SyncDelayedTask(20, () -> {
                                    new SyncDelayedTask(100, () -> {
                                        this.eventPlugin.game.started = true;
                                    });
                                    this.broadcast("&4&l[Event] &cDe WereldOorlog is begonnen! &4&lSUCCES!");

                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg flag __global__ -w world pvp allow");
                                });
                            });
                        });
                    });
                });
            });
        });
    }

    private void broadcast(String message){
        message = color(message);
        for (Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(message);
        }
    }

    @Subcommand("reset")
    @CommandAlias("resetevent")
    @CommandPermission("event.reset")
    public void reset(CommandSender sender){
        this.eventPlugin.game = new Game(this.eventPlugin);
        sender.sendMessage(color("&aGame gereset"));

        for (Player player : Bukkit.getOnlinePlayers()){
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setGameMode(GameMode.ADVENTURE);
        }
    }

    @Subcommand("players")
    @CommandPermission("event.players")
    public void players(CommandSender sender){
        new AsyncTask(() -> {
            Set<String> kingdoms = EventPlugin.getInstance().getConfig().getConfigurationSection("kingdoms").getKeys(false);
            Map<String, Integer> playerCounts = new HashMap<>();

            for (Player player : Bukkit.getOnlinePlayers()){
                if (!player.hasPermission("eventbypass")){
                    for (String kingdom : kingdoms){
                        if (player.hasPermission("group." + kingdom)){
                            playerCounts.putIfAbsent(kingdom, 0);
                            playerCounts.replace(kingdom, playerCounts.get(kingdom) + 1);
                            break;
                        }
                    }
                }
            }

            playerCounts.forEach((kingdom, players) -> {
                if (players > 0){
                    sender.sendMessage(color("&c" + kingdom + ": &f" + players));
                }
            });
        });
    }

    @Subcommand("reloadteams")
    @Description("Reload teams")
    public void reloadTeams(CommandSender sender){
        EventPlugin.getInstance().reloadConfig();
        sender.sendMessage(color("&aConfig reloaded"));

        EventPlugin.getInstance().scoreboardHandler.initialize();
        Scoreboard scoreboard = EventPlugin.getInstance().scoreboardHandler.scoreboard;
        Team red = scoreboard.getTeam("red");
        Team green = scoreboard.getTeam("green");

        new AsyncTask(() -> {
            Location redSpawn = new Location(Bukkit.getWorld("world"), -194.5, 76, -1.5, -90, 0);
            Location greenSpawn = new Location(Bukkit.getWorld("world"), 330.5, 75, -8.5, 90, 0);

            Set<String> kingdoms = EventPlugin.getInstance().getConfig().getConfigurationSection("kingdoms").getKeys(false);
            for (Player player : Bukkit.getOnlinePlayers()){
                if (!player.hasPermission("eventbypass") && !player.getGameMode().equals(GameMode.SPECTATOR)){
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
                }
            }
            new SyncDelayedTask(10, () -> {
                EventPlugin.getInstance().scoreboardHandler.updateScoreboard();
                sender.sendMessage(color("&aScoreboard is gereload"));
            });
        });
    }
}
