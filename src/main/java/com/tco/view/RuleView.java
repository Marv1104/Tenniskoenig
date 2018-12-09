package com.tco.view;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("rules")
public class RuleView extends VerticalLayout{

        //private final Label heading = new Label("Regeln zum Tenniskönig:");
        //private final Label rules = new Label(" 1. Du machst mit einem der Kinder/Jugendlichen vom TCO einen Spieltermin aus.\n" +
        //    " - NEU: Egal wie alt du bist es werden 2 Gewinnsätze bis 4 mit deinem Gegner gespielt.");
        private final Label rules = new Label();

        public RuleView() {
                // TODO add Code
                rules.getElement().setProperty("innerHTML", "<h1>Regeln zum Tenniskönig:</h1\n" +
                        "<ol start='1' type='1'>"+
                                "<li>Du machst mit einem der Kinder/Jugendlichen vom TCO einen Spieltermin aus.</li>"+
                                "<li>NEU: Egal wie alt du bist es werden 2 Gewinnsätze bis 4 mit deinem Gegner gespielt.</li>"+
                                "<li>Der 3. Satz wird als Match-Tiebreak bis 10 gespielt.</li>"+
                        "<li>Für jedes gespielte Match gibt es <i>5 Punkte</i>.</li>"+
                        "<li>Für jedes gewonnene Match gibt es zusätzlich <i>4 Punkte</i>.</li>"+
                        "<li>Du kannst gegen Mädchen und Jungs spielen.</li>"+
                        "<li>Mädchen bekommen bei einem Spiel gegen Jungs automatisch <i>2 Punkte</i>.</li>"+
                        "<li>Du kannst auch mit einem Erwachsenen spielen und dabei Punkte Sammeln. Für eine Viertelstunde Tennisspielen mit einem Erwachsenen gibt es <i>2 Punkte</i> (max. <i>8 Punkte</i> pro Termin).</li>"+
                        "<li>Pro gespieltes Doppel gibt es <i>10 Punkte</i>.</li>"+
                        "<li>Pro Woche kannst Du maximal 2 Matches gegen ein und denselben Gegner bestreiten um Punkte zu sammeln.</li>"+
                        "<li>Die Ergebnisse müssen Ergebnisse müssen in der Website vermerkt werden. Den aktuellen Stand der Rangfolge könnt online einsehen.</li>"+
                        "<li>Wer Spiele einträgt, die gar nicht stattgefunden haben, wird mit Punktabzug (min. <i>40 Punkte</i>) bestraft.</li>"+
                        "<li><b>Fair</b> geht vor!!!!</li>"+
                        "</ol>");
                this.add(rules);
        }

}
