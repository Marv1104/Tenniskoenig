package com.tco.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class HeaderView extends HorizontalLayout {
    public HeaderView(){
        HorizontalLayout tenniskoenig = new HorizontalLayout();
        tenniskoenig.setSizeUndefined();
        tenniskoenig.setJustifyContentMode(JustifyContentMode.START);
        Label name = new Label("Tenniskönig");
        tenniskoenig.add(name);
        getStyle().set("border", "1px solid #9E9E9E");
        setSizeFull();

        Label lblName = new Label("Max Mustermann");
        Button btnLogout = new Button("Logout");
        add(tenniskoenig);
        add(lblName);
        add(btnLogout);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.END);
        btnLogout.addClickListener(buttonClickEvent ->
        btnLogout.getUI().ifPresent(ui -> ui.navigate("")));
    }
}
