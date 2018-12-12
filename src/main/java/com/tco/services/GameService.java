package com.tco.services;

import com.tco.model.Game;
import com.tco.model.Played;
import com.tco.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GameService {
    private static SessionFactory factory;
    private Game game;
    private UserService userService = new UserService();

    public void setGame(Game game) {
        this.game = game;
    }

    public void getPointsTeammatch() {
        addPlayedGame(game.getGameID(), game.getPlayer1Team1(), GamePoints.TEAMMATCH.points);
        addPlayedGame(game.getGameID(), game.getPlayer2Team2(), GamePoints.TEAMMATCH.points);
        addPlayedGame(game.getGameID(), game.getPlayer3Team1(), GamePoints.TEAMMATCH.points);
        addPlayedGame(game.getGameID(), game.getPlayer4Team2(), GamePoints.TEAMMATCH.points);
    }

    public void getPointsOnevsOne() {
        if (player1won()) {
            int pointsPlayer1 = GamePoints.MATCHPLAYED.points + GamePoints.MATCHWON.points;
            // if winning player is girl and played against a boy = extrapoints
            if (userService.getUserByID(game.getPlayer1Team1()).isGeschlechtw() && !userService.getUserByID(game.getPlayer2Team2()).isGeschlechtw()) {
                pointsPlayer1 += GamePoints.GIRLAGAINSTBOY.points;
            }
            addPlayedGame(game.getGameID(),game.getPlayer1Team1(), pointsPlayer1);
            int pointsPlayer2 = GamePoints.MATCHPLAYED.points;
            // if losing player is girl and played against a boy = extrapoints
            if (userService.getUserByID(game.getPlayer2Team2()).isGeschlechtw() && !userService.getUserByID(game.getPlayer1Team1()).isGeschlechtw()) {
                pointsPlayer2 += GamePoints.GIRLAGAINSTBOY.points;
            }
            addPlayedGame(game.getGameID(),game.getPlayer2Team2(), pointsPlayer2);
        }

        if (!player1won()) {
            int pointsPlayer2 = GamePoints.MATCHPLAYED.points + GamePoints.MATCHWON.points;
            // if winning player is girl and played against a boy = extrapoints
            if (userService.getUserByID(game.getPlayer2Team2()).isGeschlechtw() && !userService.getUserByID(game.getPlayer1Team1()).isGeschlechtw()) {
                pointsPlayer2 += GamePoints.GIRLAGAINSTBOY.points;
            }
            addPlayedGame(game.getGameID(),game.getPlayer2Team2(), pointsPlayer2);
            int pointsPlayer1 = GamePoints.MATCHPLAYED.points;
            // if losing player is girl and played against a boy = extrapoints
            if (userService.getUserByID(game.getPlayer1Team1()).isGeschlechtw() && !userService.getUserByID(game.getPlayer2Team2()).isGeschlechtw()) {
                pointsPlayer1 += GamePoints.GIRLAGAINSTBOY.points;
            }
            addPlayedGame(game.getGameID(),game.getPlayer1Team1(), pointsPlayer1);
        }
    }

    public void getPointsVsAdult() {
        int pointsPlayer = (toMins(game.getPlayTime().toString())%15) * GamePoints.MIN15AGAINSTADULT.points;
        addPlayedGame(game.getGameID(), game.getPlayer1Team1(), pointsPlayer);
    }

    private boolean player1won() {
        return game.getGameSetTeam1() > game.getGameSetTeam2();
    }

    private Integer addPlayedGame(int gameID, int userID, int points) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer playedID = null;

        try {
            tx = session.beginTransaction();
            Played played = new Played(gameID, userID, points);
            playedID = (Integer) session.save(played);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return playedID;
    }

    private static int toMins(String s) {
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        return hoursInMins + mins;
    }
}
