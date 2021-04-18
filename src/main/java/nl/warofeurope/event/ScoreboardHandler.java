package nl.warofeurope.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static nl.warofeurope.event.utils.Colors.color;
import static nl.warofeurope.event.utils.ScoreboardUtil.replaceScore;

public class ScoreboardHandler {
    public Scoreboard scoreboard;

    public ScoreboardHandler(){
        this.initialize();
    }

    public void updateScoreboard(){
        for (Player player : Bukkit.getOnlinePlayers()){
            if (player.getScoreboard() != this.scoreboard){
                player.setScoreboard(this.scoreboard);
            }
        }

        Objective objective = this.scoreboard.getObjective("sidebar");
        int playerRedLeft = this.scoreboard.getTeam("red").getEntries().size();
        int playerGreenLeft = this.scoreboard.getTeam("green").getEntries().size();

        replaceScore(objective, 1, color("&cRood: &6" + playerRedLeft));
        replaceScore(objective, 2, color("&aGroen: &6" + playerGreenLeft));
    }

    public void initialize(){
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective boardObject = this.scoreboard.registerNewObjective("sidebar", "dummy");
        boardObject.setDisplaySlot(DisplaySlot.SIDEBAR);
        boardObject.setDisplayName(color("&6&lWereldOorlog"));

        Team red = this.createTeam("red", ChatColor.RED);
        Team green = this.createTeam("green", ChatColor.GREEN);

        this.updateScoreboard();
    }

    private Team createTeam(String name, ChatColor color){
        Team team = this.scoreboard.registerNewTeam(name);
        team.setAllowFriendlyFire(false);
        team.setPrefix(color("&" + color.getChar()));
        team.setCanSeeFriendlyInvisibles(true);

        return team;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
