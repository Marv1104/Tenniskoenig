package com.tco.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("SpielerErstellen")
public class CreatePlayerView extends VerticalLayout {
    private final Label header = new Label();
    private final TextField vorname = new TextField("Vorname");
    private final TextField nachname = new TextField("Nachname");
    private final Checkbox geschlechtw = new Checkbox("Mädchen");
    private final Button submit = new Button("Spieler erstellen");

    public CreatePlayerView() {
        header.getElement().setProperty("innerHTML", "<h1>Spieler erstellen");

        FormLayout userData = new FormLayout(vorname,nachname,geschlechtw);
        userData.setResponsiveSteps(new FormLayout.ResponsiveStep("22em", 3));

        add(header,userData, submit);
    }

}
