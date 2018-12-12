package com.tco.view;

import com.tco.GlobalVars;
import com.tco.model.*;
import com.tco.services.GameService;
import com.tco.services.UserService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(GlobalVars.RouteGameHistory)
public class GameHistoryView extends VerticalLayout {
    private final ComboBox<User> user = new ComboBox<>("Spieler auswählen");
    private Grid<PlayedGame> oneVSoneGames = new Grid<>();
    private Grid<PlayedTeamgame> teamGames = new Grid<>();
    private Grid<PlayedGameAgainstAdult> adultGames = new Grid<>();
    private UserService userService = new UserService();
    private final Label gameHistory = new Label();
    private GameService gameService = new GameService();

    public GameHistoryView() {
        gameHistory.getElement().setProperty("innerHTML", "<h1>Spiel History</h1>");
        user.setItems(userService.listAllUsers());

        this.setSizeFull();
        oneVSoneGames.setSizeFull();
        oneVSoneGames.addColumn(PlayedGame::getPlayer1).setHeader("Spieler 1");
        oneVSoneGames.addColumn(PlayedGame::getPlayer2).setHeader("Spieler 2");
        oneVSoneGames.addColumn(PlayedGame::getResults).setHeader("Ergebnisse");
        oneVSoneGames.addColumn(PlayedGame::getPoints).setHeader("Erhaltene Punkte");

        teamGames.setSizeFull();
        teamGames.addColumn(PlayedTeamgame::getPlayer1team1).setHeader("Team 1 - Spieler 1");
        teamGames.addColumn(PlayedTeamgame::getPlayer3team1).setHeader("Team 1 - Spieler 2");
        teamGames.addColumn(PlayedTeamgame::getPlayer2team2).setHeader("Team 2 - Spieler 1");
        teamGames.addColumn(PlayedTeamgame::getPlayer4team2).setHeader("Team 2 - Spieler 2");
        teamGames.addColumn(PlayedTeamgame::getPoints).setHeader("Erhaltene Punkte");

        adultGames.setSizeFull();
        adultGames.addColumn(PlayedGameAgainstAdult::getPlayer).setHeader("Spieler");
        adultGames.addColumn(PlayedGameAgainstAdult::getTime).setHeader("Gespielte Zeit");
        adultGames.addColumn(PlayedGameAgainstAdult::getPoints).setHeader("Erhaltene Punkte");

        add(gameHistory, user);
        user.addValueChangeListener(ValueChangeEvent -> {
            oneVSoneGames.setItems(getPlayedOneVSoneGames());
            teamGames.setItems(gameService.listAllGamesOfUser(user.getValue().getId(), "team"));
            adultGames.setItems(gameService.listAllGamesOfUser(user.getValue().getId(), "adult"));
        });
        add(oneVSoneGames, teamGames, adultGames);
    }

    private ArrayList<PlayedGame> getPlayedOneVSoneGames() {
        ArrayList<PlayedGame> playedGames = new ArrayList<>();
        List<Game> games = gameService.listAllGamesOfUser(user.getValue().getId(), "oneVSone");
        for (Game g : games) {
            String player1 = userService.getUserByID(g.getPlayer1Team1()).toString();
            String player2 = userService.getUserByID(g.getPlayer2Team2()).toString();
            // int points = highscoreService.getPointsByPlayerGame();
        }
        return playedGames;
    }
}
