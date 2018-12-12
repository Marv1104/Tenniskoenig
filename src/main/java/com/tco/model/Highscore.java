package com.tco.model;

public class Highscore {
    private String name;
    private int position;
    private int points;
    private int matchesPlayed;

    public Highscore() {
    }

    public Highscore(String name, int points, int matchesPlayed) {
        this.name = name;
        this.points = points;
        this.matchesPlayed = matchesPlayed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }
}
