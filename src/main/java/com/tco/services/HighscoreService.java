package com.tco.services;

import com.tco.GlobalVars;
import com.tco.model.Game;
import com.tco.model.Played;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

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
}
