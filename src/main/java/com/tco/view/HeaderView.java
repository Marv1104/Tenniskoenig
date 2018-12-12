package com.tco.view;

import com.tco.GlobalVars;
import com.tco.components.BlankLabel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class HeaderView extends HorizontalLayout {
    public HeaderView() {
        HorizontalLayout tenniskoenig = new HorizontalLayout();
        tenniskoenig.setSizeUndefined();
        Label name = new Label("Tenniskönig");
        tenniskoenig.add(name);
        getStyle().set("border", "1px solid #9E9E9E");
        setSizeFull();

        add(getLinks());
        BlankLabel blank = new BlankLabel();
        this.expand(blank);
        add(blank);
        //this.expand(tenniskoenig);
        Label lblName = new Label("Max Mustermann");
        Button btnLogout = new Button("Logout");
        add(tenniskoenig);
        add(lblName);
        add(btnLogout);
        setAlignItems(Alignment.CENTER);
        btnLogout.addClickListener(buttonClickEvent ->
                btnLogout.getUI().ifPresent(ui -> ui.navigate(GlobalVars.RouteHome)));
    }

    private HorizontalLayout getLinks() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button btnHome = new Button("Home");
        btnHome.addClickListener(event -> btnHome.getUI().ifPresent(ui -> ui.navigate(GlobalVars.RouteHome)));
        horizontalLayout.add(btnHome);
        Button btnSpielerErstellen = new Button("Spieler erstellen");
        btnSpielerErstellen.addClickListener(event -> btnHome.getUI().ifPresent(ui -> ui.navigate(GlobalVars.RouteSpielerErstellen)));
        horizontalLayout.add(btnSpielerErstellen);
        Button btnSpielErstellen = new Button("Spiel erstellen");
        btnSpielErstellen.addClickListener(event -> btnHome.getUI().ifPresent(ui -> ui.navigate(GlobalVars.RouteSpielEintrage)));
        horizontalLayout.add(btnSpielErstellen);
        return horizontalLayout;
    }
}
