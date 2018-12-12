package com.tco.services;

public enum GamePoints {
    MATCHPLAYED(5),
    MATCHWON(4),
    GIRLAGAINSTBOY(2),
    TEAMMATCH(10),
    MIN15AGAINSTADULT(2);

    public int points;

    GamePoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
