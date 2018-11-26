package com.tco.view;


import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.Objects;

import static com.vaadin.flow.component.UI.getCurrent;
@Tag("")

@Route("login")
@PageTitle("Login required")
public class LoginView extends PolymerTemplate<LoginView.Model> implements PageConfigurator, AfterNavigationObserver {
    private final Text text = new Text("Login benötigt!");
    private final TextField login = new TextField("Nutzername");
    private final PasswordField password = new PasswordField("Passwort");
    private final Button btnOk = new Button("Login");
    private final Button btnCancel = new Button("Cancel");
    private final HorizontalLayout buttons = new HorizontalLayout(btnOk,btnCancel);


    public interface Model extends TemplateModel{
        void setError(boolean error);
    }

    public void postProcess(){
        //setSizeFull();
        //login.setId(ID_TEXTFIELD_LOGIN);
        //login.setLabel(BUTTON_CAPTION_LOGIN); //TODO i18n

        //password.setId(ID_PASSWORDFIELD_PASSWORD);
        //password.setLabel(BUTTON_CAPTION_PASSWORD); //TODO i18n

        //btnOk.setText(BUTTON_CAPTION_OK);
        //btnOk.setId(ID_BUTTON_OK);

        btnOk.addClickListener(buttonClickEvent ->  {
            boolean isValid = checkLoginData(login.getValue(), password.getValue());
            if(isValid){
                btnOk.getUI().ifPresent(ui -> ui.navigate("main"));
            }
            else {
                clearInputFields();
            }
        });
        //btnCancel.setId(ID_BUTTON_CANCEL);
        //btnCancel.setText(BUTTON_CAPTION_CANCEL);
        btnCancel.addClickListener(buttonClickEvent ->
        clearInputFields());
    }

    private void clearInputFields(){
        login.setValue("");
        password.setValue("");
    }

    private boolean checkLoginData(String login, String password) {
        return ! (Objects.isNull(login) || Objects.isNull(password))
                && (! (login.isEmpty() || password.isEmpty())
                && (login.equals("admin") && password.equals("admin")));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {

    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {

    }
}
