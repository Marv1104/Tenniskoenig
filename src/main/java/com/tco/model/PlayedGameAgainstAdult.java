package com.tco.model;

import java.sql.Time;

public class PlayedGameAgainstAdult {
    private String player;
    private java.sql.Time time;
    private int points;

    public PlayedGameAgainstAdult(String player, Time time, int points) {
        this.player = player;
        this.time = time;
        this.points = points;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
