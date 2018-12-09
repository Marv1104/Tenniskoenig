package com.tco;

import com.tco.view.DashView;
import com.tco.view.HeaderView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("main")
public class MainView extends VerticalLayout {

    public MainView() {
        HeaderView headerView = new HeaderView();
        DashView dashView = new DashView();
        add(headerView);
        add(dashView);
        // Testing purposes
        Button enterMatch = new Button("Spiel eintragen");
        enterMatch.addClickListener(buttonClickEvent -> {
            enterMatch.getUI().ifPresent(ui -> ui.navigate("enterMatch"));
        });
        add(enterMatch);
    }
}
