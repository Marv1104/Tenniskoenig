package com.tco;

import com.tco.view.DashView;
import com.tco.view.HeaderView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.security.access.AccessDeniedException;

@Route("main")
public class MainView extends VerticalLayout implements RouterLayout, BeforeEnterObserver {

    public MainView() {
        HeaderView headerView = new HeaderView();
        DashView dashView = new DashView();
        add(headerView);
        add(dashView);

    }
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!SecurityUtils.isAccessGranted(event.getNavigationTarget())) {
            event.rerouteToError(AccessDeniedException.class);
        }
    }
}