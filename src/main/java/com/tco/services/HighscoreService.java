package com.tco.services;

import com.tco.GlobalVars;
import com.tco.model.Game;
import com.tco.model.Highscore;
import com.tco.model.Played;
import com.tco.model.User;
import com.vaadin.flow.router.Route;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class HighscoreService {
    private static SessionFactory factory;
    private UserService userService = new UserService();
    private GameService gameService = new GameService();


    public HighscoreService() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }

    public int getPointsByPlayerGame(int userId, int gameId) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Played> played = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            played = session.createQuery("FROM Played WHERE userID = " + userId
                    + " AND gameID = " + gameId).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return (played.size()>0)? played.get(0).getPoints() : 0;
    }

    public ArrayList<Highscore> getHighscores() {
        ArrayList<Highscore> highscores = new ArrayList<>();
        List<User> users = userService.listAllUsers();
        users.remove(userService.getUserByID(GlobalVars.ErwachsenerID));
        for (User u : users) {
            int points = 0;
            int matchesPlayed = 0;
            List<Game> games =  gameService.listAllGamesOfUser(u.getId(),"oneVSone");
            games.addAll(gameService.listAllGamesOfUser(u.getId(),"adult"));
            games.addAll(gameService.listAllGamesOfUser(u.getId(),"team"));
            for (Game g : games) {
                points += getPointsByPlayerGame(u.getId(), g.getGameID());
                matchesPlayed++;
            }
            highscores.add(new Highscore(u.toString(), points, matchesPlayed));
        }
        highscores.sort(Comparator.comparing(Highscore::getPoints).reversed());
        for(int i = 0; i<highscores.size(); i++) {
            highscores.get(i).setPosition(i+1);
        }
        return highscores;
    }
}
