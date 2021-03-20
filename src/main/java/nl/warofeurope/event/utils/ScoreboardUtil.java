package nl.warofeurope.event.utils;

import org.bukkit.scoreboard.Objective;

import java.util.ConcurrentModificationException;

public class ScoreboardUtil {
    public static String getEntryFromScore(Objective o, int score) {
        try {
            if (o == null) {
                return null;
            }
            if (!hasScoreTaken(o, score)) {
                return null;
            }
            for (String s : o.getScoreboard().getEntries()) {
                if (o.getScore(s).getScore() == score) {
                    return o.getScore(s).getEntry();
                }
            }
        } catch (ConcurrentModificationException ignored){}
        return null;
    }

    public static boolean hasScoreTaken(Objective o, int score) {
        try {
            for (String s : o.getScoreboard().getEntries()) {
                if (o.getScore(s).getScore() == score) {
                    return true;
                }
            }
            return false;
        } catch (ConcurrentModificationException e){
            return true;
        }
    }

    public static void replaceScore(Objective o, int score, String name) {
        try {
            if (o.getScoreboard() == null)
                return;
            if (hasScoreTaken(o, score))
            {
                if (getEntryFromScore(o, score).equalsIgnoreCase(name)) return;
                if (!getEntryFromScore(o, score).equalsIgnoreCase(name)) o.getScoreboard().resetScores(getEntryFromScore(o, score));
            }
            o.getScore(name).setScore(score);
        } catch (ConcurrentModificationException ignored){}
    }
}
