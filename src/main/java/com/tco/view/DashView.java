package com.tco.view;

import com.tco.GlobalVars;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DashView extends VerticalLayout {
    public DashView() {
        Label label = new Label("Hier steht was");
        Button button = new Button("Regeln anschauen");
        button.addClickListener(
                event -> {
                    button.getUI().ifPresent(ui -> ui.navigate(GlobalVars.RouteRegeln));
                });

        add(label);
        add(button);
    }
}
