package com.tco.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Played implements Serializable {
    @ManyToOne
    private int gameID;
    @ManyToOne
    private int userID;
    private int points;
    private int playedID;

    public Played()    { }

    public Played(int userID, int points, int playedID) {
        this.userID = userID;
        this.points = points;
        this.playedID = playedID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPlayedID() {
        return playedID;
    }

    public void setPlayedID(int playerID) {
        this.playedID = playerID;
    }
}
