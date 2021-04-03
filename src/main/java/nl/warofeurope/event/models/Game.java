package nl.warofeurope.event.models;

import nl.warofeurope.event.EventPlugin;

public class Game {
    public boolean started;

    public Game(EventPlugin eventPlugin){
        this.started = false;
        eventPlugin.scoreboardHandler.initialize();
    }
}
