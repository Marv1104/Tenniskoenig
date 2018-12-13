package com.tco.view;

import com.tco.GlobalVars;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DashView extends VerticalLayout {
    public DashView() {
        Button btnRegeln = new Button("Regeln anschauen");
        btnRegeln.setId("btnRegeln");
        btnRegeln.addClickListener(
                event -> btnRegeln.getUI().ifPresent(ui -> ui.navigate(GlobalVars.RouteRegeln)));
        Button btnMatchHistory = new Button("Zeige Matchverlauf");
        btnMatchHistory.setId("btnMatchHistory");
        btnMatchHistory.addClickListener(event ->
                btnMatchHistory.getUI().ifPresent(ui -> ui.navigate(GlobalVars.RouteGameHistory)));
        // Testing purposes
        Button enterMatch = new Button("Spiel eintragen");
        enterMatch.addClickListener(buttonClickEvent -> enterMatch.getUI().ifPresent(ui -> ui.navigate(GlobalVars.RouteSpielEintrage)));
        add(btnRegeln);
        add(btnMatchHistory);
        add(enterMatch);
    }
}
