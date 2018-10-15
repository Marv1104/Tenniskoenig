package com.tco;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HeaderView extends com.vaa {
    public HeaderView(){
        getStyle().set("border", "1px solid #9E9E9E");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setVerticalComponentAlignment(Alignment.CENTER);
        Label lblName = new Label("Max Mustermann");
        Button btnLogout = new Button("Logout");
        add(lblName);
        add(btnLogout);
    }
}
