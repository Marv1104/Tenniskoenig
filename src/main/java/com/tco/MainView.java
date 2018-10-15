package com.tco;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        HeaderView headerView = new HeaderView();
        Label label = new Label("Hier steht was");
        Button button = new Button("Click me",
                event -> Notification.show("Clicked!"));
        add(headerView);
        add(label);
        add(button);
    }
}
