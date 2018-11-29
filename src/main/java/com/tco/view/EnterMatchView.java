package com.tco.view;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("enterMatch")
public class EnterMatchView extends VerticalLayout {
    private final ComboBox player1 = new ComboBox("Spieler 1");
    private final ComboBox player2 = new ComboBox("Spieler 2");
    private final ComboBox player3 = new ComboBox("Spieler 3");
    private final ComboBox player4 = new ComboBox("Spieler 4");
    private final Checkbox teammatch = new Checkbox("Doppel");
    private final TextField pointsplayer1 = new TextField("Punkte Spieler 1");
    private final TextField pointsplayer2 = new TextField("Punkte Spieler 2");
    private final TextField pointsteam1 = new TextField("Punkte Team 1");
    private final TextField pointsteam2 = new TextField("Punkte Team 2");

    public EnterMatchView() {
        // TODO add Code
        add(getOneVsOneChild());

        teammatch.addValueChangeListener(valueChangeEvent -> {
            if (teammatch.getValue()) {
                removeAll();
                add(getTeammatch());
            }
            if (!teammatch.getValue()) {
                removeAll();
                add(getOneVsOneChild());
            }
        });
    }

    private FormLayout getOneVsOneChild() {
        FormLayout layout = new FormLayout(player1, player2, teammatch, pointsplayer1, pointsplayer2);
        layout.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));
        return layout;
    }

    private VerticalLayout getTeammatch() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Label team1 = new Label();
        team1.getElement().setProperty("innerHTML", "<h3>Mannschaft 1</h3>");

        FormLayout team1form = new FormLayout(player1, player2, teammatch);
        team1form.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));
        teammatch.setValue(true);

        Label team2 = new Label();
        team2.getElement().setProperty("innerHTML", "<h3>Mannschaft 2</h3>");

        FormLayout team2form = new FormLayout(player3, player4);
        team1form.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));

        Label points = new Label();
        points.getElement().setProperty("innerHTML", "<h3>Ergebnis</h3>");

        FormLayout pointsform = new FormLayout(pointsteam1, pointsteam2);
        team1form.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));

        layout.add(team1);
        layout.add(team1form);
        layout.add(team2);
        layout.add(team2form);
        layout.add(points);
        layout.add(pointsform);
        return layout;
    }
}
