package com.tco.model;

public class PlayedTeamgame {
    private String player1team1;
    private String player3team1;
    private String player2team2;
    private String player4team2;
    private int points;

    public PlayedTeamgame(String player1team1, String player3team1, String player2team2, String player4team2, int points) {
        this.player1team1 = player1team1;
        this.player3team1 = player3team1;
        this.player2team2 = player2team2;
        this.player4team2 = player4team2;
        this.points = points;
    }

    public String getPlayer1team1() {
        return player1team1;
    }

    public void setPlayer1team1(String player1team1) {
        this.player1team1 = player1team1;
    }

    public String getPlayer3team1() {
        return player3team1;
    }

    public void setPlayer3team1(String player3team1) {
        this.player3team1 = player3team1;
    }

    public String getPlayer2team2() {
        return player2team2;
    }

    public void setPlayer2team2(String player2team2) {
        this.player2team2 = player2team2;
    }

    public String getPlayer4team2() {
        return player4team2;
    }

    public void setPlayer4team2(String player4team2) {
        this.player4team2 = player4team2;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
