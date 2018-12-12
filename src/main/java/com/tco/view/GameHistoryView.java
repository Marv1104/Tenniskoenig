package com.tco.view;

import com.tco.GlobalVars;
import com.tco.model.Game;
import com.tco.model.User;
import com.tco.services.GameService;
import com.tco.services.UserService;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(GlobalVars.RouteGameHistory)
public class GameHistoryView extends VerticalLayout {
    private final ComboBox<User> user = new ComboBox<>("Spieler auswählen");
    private Grid<Game> grid = new Grid<>();
    private UserService userService = new UserService();
    private final Label gameHistory = new Label();
    private GameService gameService = new GameService();

    public GameHistoryView() {
        gameHistory.getElement().setProperty("innerHTML", "<h1>Spiel History</h1>");
        user.setItems(userService.listAllUsers());

        this.setSizeFull();
        grid.setSizeFull();
        grid.addColumn(Game::getPlayer1Team1 ).setHeader("Team 1 - Spieler 1");
        grid.addColumn(Game::getPlayer3Team1).setHeader("Team 1 - Spieler 3");
        grid.addColumn(Game::getPlayer2Team2).setHeader("Team 2 - Spieler 2");
        grid.addColumn(Game::getPlayer4Team2).setHeader("Team 2 - Spieler 4");
        grid.addColumn(Game::getResults).setHeader("Ergebnisse");
        grid.addColumn(Game::getPlayTime).setHeader("Gespielte Zeit");
        grid.addColumn(Game::getGameDate).setHeader("Datum");

        grid.setItems(gameService.listAllGamesOfUser(1));
        add(gameHistory, user, grid);
        user.addValueChangeListener(ValueChangeEvent -> {
            grid.setItems(gameService.listAllGamesOfUser(1));
        });

    }
}
