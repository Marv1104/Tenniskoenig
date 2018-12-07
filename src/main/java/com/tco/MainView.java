package com.tco;

import com.tco.view.DashView;
import com.tco.view.HeaderView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The main view contains a button and a click listener.
 */
@Route("main")
public class MainView extends VerticalLayout {
        private static SessionFactory factory;

    public MainView() {
        HeaderView headerView = new HeaderView();
        DashView dashView = new DashView();
        add(headerView);
        add(dashView);


        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();

        Session session = factory.openSession();
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        session.close();

        //ManageAuto MA = new ManageAuto();

        // Hinzufügen einiger Autos
        //Integer autoID1 = MA.addAuto("Fiat", "Punto", 50, "grün");

    }
}
