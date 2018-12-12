package com.tco.view;

import com.tco.GlobalVars;
import com.tco.components.BlankLabel;
import com.tco.services.GameService;
import com.tco.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.tco.model.User;

import java.sql.Time;
import java.util.Calendar;

@Route(GlobalVars.RouteSpielEintrage)
public class EnterMatchView extends VerticalLayout {
    private final Label header = new Label();
    private final ComboBox<User> player1 = new ComboBox("Spieler 1");
    private final ComboBox<User> player2 = new ComboBox("Spieler 2");
    private final ComboBox<User> player3 = new ComboBox("Spieler 3");
    private final ComboBox<User> player4 = new ComboBox("Spieler 4");
    private final Checkbox teammatch = new Checkbox("Doppel");
    private final TextField set1Team1 = new TextField();
    private final TextField set1Team2 = new TextField();
    private final TextField set2Team1 = new TextField();
    private final TextField set2Team2 = new TextField();
    private final TextField set3Team1 = new TextField();
    private final TextField set3Team2 = new TextField();
    private final TextField timePlayed = new TextField("Gespielte Zeit");
    private GameService gameService = new GameService();

    private Button submit = new Button("Spiel eintragen");

    private UserService userService = new UserService();

    public EnterMatchView() {
        HeaderView headerView = new HeaderView();
        this.add(headerView);
        //set all HTML IDs to simplifiy testing
        setHtmlIds();

        // set items in Combooxes
        player1.setItems(userService.listAllUsers());
        player2.setItems(userService.listAllUsers());
        player3.setItems(userService.listAllUsers());
        player4.setItems(userService.listAllUsers());

        header.getElement().setProperty("innerHTML", "<h1>Spiel eintragen</h1>");
        add(header);
        add(header, getOneVsOne(), submit);

        teammatch.addValueChangeListener(valueChangeEvent -> {
            if (teammatch.getValue()) {
                removeAll();
                add(header, getTeammatch(), submit);
            }
            if (!teammatch.getValue()) {
                removeAll();
                add(header, getOneVsOne(), submit);
            }
        });
        submit.addClickListener(ClickEvent -> {
            int player1ID = player1.getValue().getId();
            int player2ID = player2.getValue().getId();
            int player3ID = 0;
            if (player3.getValue() != null) {
                player3ID = player3.getValue().getId();
            }
            int player4ID = 0;
            if (player4.getValue() != null) {
                player4ID = player4.getValue().getId();
            }
            String results = set1Team1.getValue() + ":" + set1Team2.getValue() + ";" + set2Team1.getValue()
                    + ":" + set2Team2.getValue() + ";" + set3Team1.getValue() + ":" + set3Team2.getValue();
            int gameSetTeam1 = 0;
            int gameSetTeam2 = 0;
            if (player2ID != GlobalVars.ErwachsenerID) {
                gameSetTeam1 = getGameSetTeam1();
                gameSetTeam2 = getGameSetTeam2();
            }
            java.sql.Date gameDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            Time playTime = Time.valueOf("00:00:00");
            if (player2ID == GlobalVars.ErwachsenerID) {
                playTime = Time.valueOf(timePlayed.getValue());
            }
            gameService.setGame(player1ID, player2ID, player3ID,
                    player4ID, results, gameSetTeam1, gameSetTeam2, gameDate, playTime);
            boolean adult = (player2ID == GlobalVars.ErwachsenerID);
            gameService.evaluateGame(teammatch.getValue(), adult);
        });

    }

    /**
     * Creates a form for a one vs one match
     *
     * @return FormLayout for a One vs One Match
     */
    private VerticalLayout getOneVsOne() {

        //create blanks to use in the form
        BlankLabel blank1 = new BlankLabel();
        BlankLabel blank2 = new BlankLabel();

        VerticalLayout layout = new VerticalLayout();

        Label players = new Label();
        players.getElement().setProperty("innerHTML", "<h3>Spieler</h3>");
        FormLayout playerForm = new FormLayout(players, blank1, blank2, player1, player2, teammatch);
        playerForm.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));
        playerForm.setSizeFull();

        layout.add(playerForm);

        FormLayout resultForm = getResultForm();
        FormLayout timePlayedForm = getTimePlayed();
        layout.add(resultForm);
        teammatch.addValueChangeListener(valueChangeEvent -> {
            changeResultForm(layout, resultForm, timePlayedForm);
        });
        player2.addValueChangeListener(valueChangeEvent -> {
            changeResultForm(layout, resultForm, timePlayedForm);
        });

        return layout;
    }

    private void changeResultForm(VerticalLayout layout, FormLayout resultForm, FormLayout timePlayedForm) {
        if (player2.getValue().getVorname().equals(GlobalVars.VornameErwachsener)) {
            layout.remove(resultForm);
            layout.add(timePlayedForm);
        } else {
            layout.remove(timePlayedForm);
            layout.add(resultForm);
        }
    }

    /**
     * Creates a form for a teammatch
     *
     * @return VerticalLayout with a form for a temamatch
     */
    private VerticalLayout getTeammatch() {
        // create blanks for use in the forms below
        BlankLabel blank1 = new BlankLabel();
        BlankLabel blank2 = new BlankLabel();
        BlankLabel blank3 = new BlankLabel();
        BlankLabel blank4 = new BlankLabel();
        BlankLabel blank5 = new BlankLabel();

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Label team1 = new Label();
        team1.getElement().setProperty("innerHTML", "<h3>Mannschaft 1</h3>");

        FormLayout team1form = new FormLayout(team1, blank1, blank2, player1, player2, teammatch);
        team1form.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));
        team1form.setSizeFull();
        teammatch.setValue(true);

        Label team2 = new Label();
        team2.getElement().setProperty("innerHTML", "<h3>Mannschaft 2</h3>");


        FormLayout team2form = new FormLayout(team2, blank3, blank4, player3, player4, blank5);
        team2form.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));
        team2form.setSizeFull();

        FormLayout resultForm = getResultForm();

        // add all forms to the layout
        layout.add(team1form, team2form, resultForm);
        return layout;
    }

    /**
     * Creates a form for the results
     *
     * @return Formlayout for results
     */
    private FormLayout getResultForm() {
        //create blanks to use in the form
        BlankLabel blank1 = new BlankLabel();
        BlankLabel blank2 = new BlankLabel();
        BlankLabel blank3 = new BlankLabel();

        FormLayout layout = new FormLayout();

        Label result = new Label();
        result.getElement().setProperty("innerHTML", "<h3>Ergebnisse</h3>");

        layout.add(result, blank1, blank2, new Label("Satz 1"), new Label("Satz 2"), new Label("Satz 3 (optional)"));

        // create a HorizontalLayout for every set
        HorizontalLayout set1 = new HorizontalLayout();
        set1.add(set1Team1, new Label(":"), set1Team2);
        HorizontalLayout set2 = new HorizontalLayout();
        set2.add(set2Team1, new Label(":"), set2Team2);
        HorizontalLayout set3 = new HorizontalLayout();
        set3.add(set3Team1, new Label(":"), set3Team2);

        layout.add(set1, set2, set3);
        layout.setSizeFull();
        layout.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));
        return layout;
    }

    /**
     * Creates a form for the played time against an adult
     *
     * @return FormLayout with the form for the played time
     */
    private FormLayout getTimePlayed() {
        //create blanks to use in the form
        BlankLabel blank1 = new BlankLabel();
        BlankLabel blank2 = new BlankLabel();

        FormLayout layout = new FormLayout();
        layout.add(timePlayed, blank1, blank2);
        layout.setSizeFull();
        layout.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));
        return layout;
    }

    /**
     * sets all HTML IDs to simplify testing
     */
    private void setHtmlIds() {
        player1.setId("player1");
        player2.setId("player2");
        player3.setId("player3");
        player4.setId("player4");
        teammatch.setId("teammatch");
        set1Team1.setId("set1team1");
        set1Team2.setId("set1team2");
        set2Team1.setId("set2team1");
        set2Team2.setId("set2team2");
        set3Team1.setId("set3team1");
        set3Team2.setId("set3team2");
        timePlayed.setId("timePlayed");
        submit.setId("submit");
    }

    private int getGameSetTeam1() {
        int gameSet = 0;
        if (Integer.valueOf(set1Team1.getValue()) > Integer.valueOf(set1Team2.getValue())) {
            gameSet++;
        }
        if (Integer.valueOf(set2Team1.getValue()) > Integer.valueOf(set2Team2.getValue())) {
            gameSet++;
        }
        if (gameSet == 1) { //3 Satz nur wenn davor unentschieden
            if (Integer.valueOf(set3Team1.getValue()) > Integer.valueOf(set3Team2.getValue())) {
                gameSet++;
            }
        }
        return gameSet;
    }

    private int getGameSetTeam2() {
        int gameSet = 0;
        if (Integer.valueOf(set1Team1.getValue()) < Integer.valueOf(set1Team2.getValue())) {
            gameSet++;
        }
        if (Integer.valueOf(set2Team1.getValue()) < Integer.valueOf(set2Team2.getValue())) {
            gameSet++;
        }
        if (gameSet == 1) { //3 Satz nur wenn davor unentschieden
            if (Integer.valueOf(set3Team1.getValue()) < Integer.valueOf(set3Team2.getValue())) {
                gameSet++;
            }
        }
        return gameSet;
    }
}
