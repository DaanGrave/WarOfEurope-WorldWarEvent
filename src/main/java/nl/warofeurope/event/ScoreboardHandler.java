package nl.warofeurope.event;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;
import java.util.stream.Collectors;

import static nl.warofeurope.event.utils.Colors.color;
import static nl.warofeurope.event.utils.ScoreboardUtil.replaceScore;

public class ScoreboardHandler {
    public Scoreboard scoreboard;
    public Map<UUID, Integer> playerKills;

    public ScoreboardHandler(){
        this.initialize();
        this.playerKills = new HashMap<>();
    }

    public void updateScoreboard(){
        final int[] start = {17};
        Objective objective = this.scoreboard.getObjective("sidebar");

        if (this.playerKills == null)
            this.playerKills = new HashMap<>();
        List<Map.Entry<UUID, Integer>> players = this.playerKills.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).collect(Collectors.toList());
        for (Map.Entry<UUID, Integer> player : players) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getKey());
            replaceScore(objective, start[0], color("&6" + offlinePlayer.getName() + "&f: &e" + player.getValue()));
            start[0]--;
        }
    }

    public void initialize(){
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective boardObject = this.scoreboard.registerNewObjective("sidebar", "dummy");
        boardObject.setDisplaySlot(DisplaySlot.SIDEBAR);
        boardObject.setDisplayName(color("&6&lFFA Event"));

        this.updateScoreboard();
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
