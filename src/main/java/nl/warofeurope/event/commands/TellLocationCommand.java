package nl.warofeurope.event.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import nl.warofeurope.event.EventPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static nl.warofeurope.event.utils.Colors.color;

@CommandAlias("tl|telllocation")
@Description("Vertel je locatie")
public class TellLocationCommand extends BaseCommand {
    @Default
    public void tl(CommandSender sender){
        if (!(sender instanceof Player))
            return;
        Player player = (Player) sender;
        String playerName = player.getName();

        Scoreboard scoreboard = EventPlugin.getInstance().scoreboardHandler.scoreboard;
        Team red = scoreboard.getTeam("red");
        Team green = scoreboard.getTeam("green");

        Location location = player.getLocation();
        int blockX = location.getBlockX();
        int blockY = location.getBlockY();
        int blockZ = location.getBlockZ();

        try {
            if (red.getEntries().contains(playerName)){
                red.getEntries().forEach(entryName -> {
                    Player teamPlayer = Bukkit.getPlayer(entryName);
                    teamPlayer.sendMessage(color("&e" + player.getName() + ": " + blockX + ", " + blockY + ", " + blockZ));
                });
            } else if (green.getEntries().contains(playerName)){
                green.getEntries().forEach(entryName -> {
                    Player teamPlayer = Bukkit.getPlayer(entryName);
                    teamPlayer.sendMessage(color("&e" + player.getName() + ": " + blockX + ", " + blockY + ", " + blockZ));
                });
            } else {
                player.sendMessage(color("&cJe zit niet in een team."));
            }
        } catch (NullPointerException e){
            player.sendMessage(color("&cEr ging wat fout tijdens het versturen van jouw locatie."));
        }
    }
}
