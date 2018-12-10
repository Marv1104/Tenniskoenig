package com.tco.view;

import com.tco.components.BlankLabel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
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
    private final Label header = new Label();
    private final ComboBox player1 = new ComboBox("Spieler 1");
    private final ComboBox<String> player2 = new ComboBox("Spieler 2");
    private final ComboBox player3 = new ComboBox("Spieler 3");
    private final ComboBox player4 = new ComboBox("Spieler 4");
    private final Checkbox teammatch = new Checkbox("Doppel");
    private final TextField set1Team1 = new TextField();
    private final TextField set1Team2 = new TextField();
    private final TextField set2Team1 = new TextField();
    private final TextField set2Team2 = new TextField();
    private final TextField set3Team1 = new TextField();
    private final TextField set3Team2 = new TextField();
    private final TextField timePlayed = new TextField();

    private Button submit = new Button("Spiel eintragen");


    public EnterMatchView() {
        // testing purposes
        player2.setItems("Erwachsener");

        header.getElement().setProperty("innerHTML", "<h1>SPiel eintragen</h1>");
        add(header);
        add(header, getOneVsOne(), submit);

        teammatch.addValueChangeListener(valueChangeEvent -> {
            if (teammatch.getValue()) {
                removeAll();
                add(header, getTeammatch(),submit);
            }
            if (!teammatch.getValue()) {
                removeAll();
                add(header, getOneVsOne(), submit);
            }
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

        FormLayout resultForm = getResultForm(teammatch.getValue());
        FormLayout timePlayedForm = getTimePLayed();
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
        if (player2.getValue() == "Erwachsener") {
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

        FormLayout resultForm = getResultForm(teammatch.getValue());

        // add all forms to the layout
        layout.add(team1form, team2form, resultForm);
        return layout;
    }

    /**
     * Creates a form for the results
     *
     * @param isTeammatch indicates if match is a teammatch
     * @return Formlayout for results
     */
    private FormLayout getResultForm(boolean isTeammatch) {
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
    private FormLayout getTimePLayed() {
        //create blanks to use in the form
        BlankLabel blank1 = new BlankLabel();
        BlankLabel blank2 = new BlankLabel();

        FormLayout layout = new FormLayout();
        layout.add(timePlayed, blank1, blank2);
        layout.setSizeFull();
        layout.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));
        return layout;
    }
}
