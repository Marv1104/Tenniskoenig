package com.tco.services;

import com.tco.GlobalVars;
import com.tco.model.Game;
import com.tco.model.Played;
import com.tco.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameService {
    private static SessionFactory factory;
    private Game game;
    private UserService userService = new UserService();

    public GameService() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }

    /**
     * Spielauswertung
     *
     * @param teammatch gibt an, ob teammatch
     */
    public void evaluateGame(boolean teammatch, boolean adult) {
        if (teammatch) {
            getPointsTeammatch();
        } else if (adult) {
            getPointsVsAdult();
        } else {
            getPointsOnevsOne();
        }
    }

    /**
     * SPiel instanz einsetzen
     */
    public void setGame(int player1Team1, int player2Team2, int player3Team1, int player4Team2, String results,
                        int gameSetTeam1, int gameSetTeam2, Date gameDate, Time playTime) {
        this.game = new Game(player1Team1, player2Team2, player3Team1, player4Team2, results,
                gameSetTeam1, gameSetTeam2, gameDate, playTime);
        addGame(game);
    }

    public void setGame(int player1Team1, int player2Team2, String results,
                        int gameSetTeam1, int gameSetTeam2, Date gameDate, Time playTime) {
        this.game = new Game(player1Team1, player2Team2, results,
                gameSetTeam1, gameSetTeam2, gameDate, playTime);
        addGame(game);
    }

    /**
     * Punkte für ein Teammatch vergeben
     */
    public void getPointsTeammatch() {
        addPlayedGame(game.getGameID(), game.getPlayer1Team1(), GamePoints.TEAMMATCH.points);
        addPlayedGame(game.getGameID(), game.getPlayer2Team2(), GamePoints.TEAMMATCH.points);
        addPlayedGame(game.getGameID(), game.getPlayer3Team1(), GamePoints.TEAMMATCH.points);
        addPlayedGame(game.getGameID(), game.getPlayer4Team2(), GamePoints.TEAMMATCH.points);
    }

    /**
     * Punkte für 1 gegen 1 vergeben
     */
    public void getPointsOnevsOne() {
        if (player1won()) {
            int pointsPlayer1 = GamePoints.MATCHPLAYED.points + GamePoints.MATCHWON.points;
            // if winning player is girl and played against a boy = extrapoints
            if (userService.getUserByID(game.getPlayer1Team1()).isGeschlechtw() && !userService.getUserByID(game.getPlayer2Team2()).isGeschlechtw()) {
                pointsPlayer1 += GamePoints.GIRLAGAINSTBOY.points;
            }
            addPlayedGame(game.getGameID(), game.getPlayer1Team1(), pointsPlayer1);
            int pointsPlayer2 = GamePoints.MATCHPLAYED.points;
            // if losing player is girl and played against a boy = extrapoints
            if (userService.getUserByID(game.getPlayer2Team2()).isGeschlechtw() && !userService.getUserByID(game.getPlayer1Team1()).isGeschlechtw()) {
                pointsPlayer2 += GamePoints.GIRLAGAINSTBOY.points;
            }
            addPlayedGame(game.getGameID(), game.getPlayer2Team2(), pointsPlayer2);
        }

        if (!player1won()) {
            int pointsPlayer2 = GamePoints.MATCHPLAYED.points + GamePoints.MATCHWON.points;
            // if winning player is girl and played against a boy = extrapoints
            if (userService.getUserByID(game.getPlayer2Team2()).isGeschlechtw() && !userService.getUserByID(game.getPlayer1Team1()).isGeschlechtw()) {
                pointsPlayer2 += GamePoints.GIRLAGAINSTBOY.points;
            }
            addPlayedGame(game.getGameID(), game.getPlayer2Team2(), pointsPlayer2);
            int pointsPlayer1 = GamePoints.MATCHPLAYED.points;
            // if losing player is girl and played against a boy = extrapoints
            if (userService.getUserByID(game.getPlayer1Team1()).isGeschlechtw() && !userService.getUserByID(game.getPlayer2Team2()).isGeschlechtw()) {
                pointsPlayer1 += GamePoints.GIRLAGAINSTBOY.points;
            }
            addPlayedGame(game.getGameID(), game.getPlayer1Team1(), pointsPlayer1);
        }
    }

    /**
     * Punkte für Spiel gegen Erwachsenen verteilen
     */
    public void getPointsVsAdult() {
        int pointsPlayer = (toMins(game.getPlayTime().toString()) / 15) * GamePoints.MIN15AGAINSTADULT.points;
        addPlayedGame(game.getGameID(), game.getPlayer1Team1(), pointsPlayer);
    }

    /**
     * Auswertung ob Spieler 1 mehr punkte hat
     *
     * @return
     */
    private boolean player1won() {
        return game.getGameSetTeam1() > game.getGameSetTeam2();
    }

    /**
     * trägt ein Spiel in die Played Tabelle ein
     *
     * @param gameID
     * @param userID
     * @param points
     * @return
     */
    public Integer addPlayedGame(int gameID, int userID, int points) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer playedID = null;

        try {
            tx = session.beginTransaction();
            Played played = new Played();
            played.setGameID(gameID);
            played.setUserID(userID);
            played.setPoints(points);
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

    /**
     * Ein Spiel in die Datenbank eintragen
     *
     * @param game das zu speichernde Spiel
     * @return
     */
    private Integer addGame(Game game) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer gameID = null;

        try {
            tx = session.beginTransaction();
            gameID = (Integer) session.save(game);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return gameID;
    }

    /**
     * MySQL time zu integer minuten umwandeln
     *
     * @param s
     * @return
     */
    private static int toMins(String s) {
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        return hoursInMins + mins;
    }

    /**
     * @param userId
     * @param matchtype oneVSone adult or team
     * @return
     */
    public List listAllGamesOfUser(int userId, String matchtype) {
        List<Game> games = new ArrayList();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            if (matchtype.equals("oneVSone")) {
                games = session.createQuery("FROM Game WHERE Player1Team1 = " + userId
                        + "AND Player3Team1 = 0 AND Player2Team2 != "+ GlobalVars.ErwachsenerID).list();
                games.addAll(session.createQuery("FROM Game WHERE Player2Team2 = " + userId
                        + "AND Player3Team1 = 0").list());
            }
            if (matchtype.equals("team")) {
                games = session.createQuery("FROM Game WHERE Player1Team1 = " + userId + "AND Player3Team1 != 0").list();
                games.addAll(session.createQuery("FROM Game WHERE Player2Team2 = " + userId + "AND Player3Team1 != 0").list());
                games.addAll(session.createQuery("FROM Game WHERE Player3Team1 = " + userId + "AND Player3Team1 != 0").list());
                games.addAll(session.createQuery("FROM Game WHERE Player4Team2 = " + userId + "AND Player3Team1 != 0").list());
            }
            if (matchtype.equals("adult")) {
                games = session.createQuery("FROM Game WHERE Player1Team1 = " + userId + "AND Player2Team2 = " + GlobalVars.ErwachsenerID).list();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return games;
    }
}
