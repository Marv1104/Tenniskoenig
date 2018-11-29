package com.tco.ui.vaadin;

import java.net.MalformedURLException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinServletResponse;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This is the form login page.
 * 
 *
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@SpringUI(path = "/login")
public class LoginUI extends UI {
	
	@Autowired
    private AuthenticationProvider authenticationProvider;
	
	@Autowired
	SessionAuthenticationStrategy sessionAuthenticationStrategy;	

    @Override
    protected void init(final VaadinRequest request) {    	
    	
    	if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken))
    	{
    		URI currentLoc = Page.getCurrent().getLocation();
    		try {
				Page.getCurrent().setLocation(  currentLoc.toURL().toString().replace("/login", "/app"));
			} catch (MalformedURLException e1) {				
				e1.printStackTrace();
			}
    		return;
    	}
    	
    	VerticalLayout vl = new VerticalLayout();
    	LoginForm lf = new LoginForm();
    	lf.addLoginListener(e -> {
            final Authentication auth = new UsernamePasswordAuthenticationToken(e.getLoginParameter("username"), e.getLoginParameter("password"));
            try {
            	// this is the code for achieving the spring security authentication in a programmatic way
                final Authentication authenticated = authenticationProvider.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(authenticated);
                sessionAuthenticationStrategy.onAuthentication(auth, ((VaadinServletRequest)VaadinService.getCurrentRequest()).getHttpServletRequest(), ((VaadinServletResponse)VaadinService.getCurrentResponse()).getHttpServletResponse());
                URI currentLoc = Page.getCurrent().getLocation();
        		try {
    				Page.getCurrent().setLocation(  currentLoc.toURL().toString().replace("/login", "/app"));
    			} catch (MalformedURLException e1) {    				
    				e1.printStackTrace();
    			}
            } catch (final AuthenticationException ex) {
            	String message = "Incorrect user or password:" + ex.getMessage() + e.getLoginParameter("username") + ":" + e.getLoginParameter("password");
            	Notification.show(message, Notification.Type.ERROR_MESSAGE);
            }

    	});
    	
    	vl.addComponent(lf);
    	vl.setComponentAlignment(lf, Alignment.MIDDLE_CENTER);
    	vl.setSizeFull();
    	setContent(vl);
    }

}
