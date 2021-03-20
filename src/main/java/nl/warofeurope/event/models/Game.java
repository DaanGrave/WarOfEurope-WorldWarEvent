package nl.warofeurope.event.models;

import nl.warofeurope.event.EventPlugin;
import nl.warofeurope.event.ScoreboardHandler;

import java.util.HashMap;
import java.util.Map;

public class Game {
    public boolean started;
    public Map<ScoreboardHandler.Teams, Integer> playersLeft;

    public Game(EventPlugin eventPlugin){
        this.started = false;
        eventPlugin.scoreboardHandler.initialize();

        this.playersLeft = new HashMap<>();
        for (ScoreboardHandler.Teams teams : ScoreboardHandler.Teams.getValues()) {
            this.playersLeft.putIfAbsent(teams, 0);
        }
    }
}
