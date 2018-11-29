package com.tco.ui.vaadin;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import com.tco.model.InputData;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * 
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@SpringUI(path = "/app")
public class MyUI extends UI {

	@Autowired
	private InputController controller;

	private InputData inputData;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();

		final TextField name = new TextField();
		name.setCaption("Type your name here:");
		
		Label sessionIdLabel = new Label();
		

		Label dataFromService_Name = new Label();
		dataFromService_Name.setVisible(false);
		
		Label dataFromService_more = new Label();
		dataFromService_more.setVisible(false);

		layout.addComponent(name);
		layout.addComponent(sessionIdLabel);
		layout.addComponent(dataFromService_Name);
		layout.addComponent(dataFromService_more);


		Button button = new Button("Click Me");
		button.addClickListener(e -> {
			inputData = controller.setUserInput(name.getValue());
			String sessionID = ((com.vaadin.server.VaadinServletRequest) VaadinService.getCurrentRequest())
					.getHttpServletRequest().getSession().getId();

			sessionIdLabel.setValue(sessionID);
			dataFromService_Name.setValue("Thanks, you entered: " + inputData.getName() );
			dataFromService_Name.setVisible(true);
			
			dataFromService_more.setValue("Thanks, it has more data: " + inputData.getMoreData() );
			dataFromService_more.setVisible(true);

		});

		layout.addComponent(button);
		Button logout = new Button("Logout");
		logout.addClickListener(e -> {
			VaadinService.getCurrentRequest().getWrappedSession().invalidate();
			new SecurityContextLogoutHandler()
					.logout(((com.vaadin.server.VaadinServletRequest) VaadinService.getCurrentRequest())
							.getHttpServletRequest(), null, null);
			Page.getCurrent().setLocation("/");
		});

		layout.addComponent(logout);

		setContent(layout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends SpringVaadinServlet {
	}
}
