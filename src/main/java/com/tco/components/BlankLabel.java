package com.tco.components;

import com.vaadin.flow.component.html.Label;

/**
 * This class is used to create blank fields in FormLayouts
 */
public class BlankLabel extends Label {
    private Label label;

    public BlankLabel() {
        label = new Label();
        label.getElement().setProperty("innerHTML", "&nbsp");
    }
}
