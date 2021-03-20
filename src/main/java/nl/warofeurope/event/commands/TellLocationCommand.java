package nl.warofeurope.event.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import nl.warofeurope.event.EventPlugin;
import nl.warofeurope.event.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static nl.warofeurope.event.utils.Colors.color;

@CommandAlias("tl|telllocation")
@Description("Vertel je locatie")
public class TellLocationCommand extends BaseCommand {
    private final EventPlugin eventPlugin;

    public TellLocationCommand(EventPlugin eventPlugin) {
        this.eventPlugin = eventPlugin;
    }

    @Default
    public void tl(CommandSender sender){
        if (!(sender instanceof Player))
            return;
        Player player = (Player) sender;
        ScoreboardHandler.Teams teams = this.getPlayer(player);

        Location location = player.getLocation();

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        String message = color("&e" + player.getName() + ": " + x + ", " + y + ", " + z);

        if (teams != null){
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                if (teams.getPlayers().contains(onlinePlayer)){
                    onlinePlayer.sendMessage(message);
                }
            }
        }

    }

    private ScoreboardHandler.Teams getPlayer(Player player){
        for (ScoreboardHandler.Teams teams : ScoreboardHandler.Teams.getValues()){
            if (teams.getPlayers().contains(player)){
                return teams;
            }
        }

        return null;
    }
}
