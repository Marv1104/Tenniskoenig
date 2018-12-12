package com.tco.model;

public class PlayedGame {
    private String player1;
    private String player2;
    private String results;
    private int points;

    public PlayedGame(String player1, String player2, String results, int points) {
        this.player1 = player1;
        this.player2 = player2;
        this.results = results;
        this.points = points;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
