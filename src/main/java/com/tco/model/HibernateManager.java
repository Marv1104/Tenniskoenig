package com.tco.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.sql.Time;
import java.time.Instant;
import java.util.Date;

public class HibernateManager {
    private static SessionFactory factory;


public void method() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");
    factory = cfg.buildSessionFactory();

    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();

    User user = new User("Sven", "Haala", false, false);
    Integer id = (Integer) session.save(user);
    //int k = 1;
    //User user = (User) session.get(User.class, k);
    //System.out.println(user.getNachname());
    session.close();
}

}
