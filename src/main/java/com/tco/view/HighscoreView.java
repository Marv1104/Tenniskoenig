package com.tco.view;

import com.tco.GlobalVars;
import com.tco.model.Highscore;
import com.tco.services.HighscoreService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(GlobalVars.RouteHighscores)
public class HighscoreView extends VerticalLayout {
    private  final Label title = new Label();
    private Grid<Highscore> highscores = new Grid<>();
    private HighscoreService highscoreService = new HighscoreService();

    public HighscoreView() {
        add(new HeaderView());
        this.setSizeFull();

        title.getElement().setProperty("innerHTML","<h1>Bestenliste</h1>");
        highscores.setSizeFull();
        highscores.addColumn(Highscore::getPosition).setHeader("Pos.");
        highscores.addColumn(Highscore::getName).setHeader("Name");
        highscores.addColumn(Highscore::getPoints).setHeader("Punkte");
        highscores.addColumn(Highscore::getMatchesPlayed).setHeader("Anzahl Spiele");
        highscores.setHeight("100vh");
        highscores.setItems(highscoreService.getHighscores());

        add(highscores);
    }
}
