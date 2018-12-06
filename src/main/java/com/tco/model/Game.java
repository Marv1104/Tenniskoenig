package com.tco.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
public class Game implements Serializable {
    private int gameID;
    private int player1Team1;
    private int player2Team2;
    private int player3Team1;
    private int player4Team2;
    private String results;
    private int gameSetTeam1;
    private int gameSetTeam2;
    private Date gameDate;
    private Time playTime;

    @Id
    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayer1Team1() {
        return player1Team1;
    }

    public void setPlayer1Team1(int player1Team1) {
        this.player1Team1 = player1Team1;
    }

    public int getPlayer2Team2() {
        return player2Team2;
    }

    public void setPlayer2Team2(int player2Team2) {
        this.player2Team2 = player2Team2;
    }

    public int getPlayer3Team1() {
        return player3Team1;
    }

    public void setPlayer3Team1(int player3Team1) {
        this.player3Team1 = player3Team1;
    }

    public int getPlayer4Team2() {
        return player4Team2;
    }

    public void setPlayer4Team2(int player4Team2) {
        this.player4Team2 = player4Team2;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public int getGameSetTeam1() {
        return gameSetTeam1;
    }

    public void setGameSetTeam1(int gameSetTeam1) {
        this.gameSetTeam1 = gameSetTeam1;
    }

    public int getGameSetTeam2() {
        return gameSetTeam2;
    }

    public void setGameSetTeam2(int gameSetTeam2) {
        this.gameSetTeam2 = gameSetTeam2;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Time getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Time playTime) {
        this.playTime = playTime;
    }
}
