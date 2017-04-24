package com.vaadin.cdi.tutorial;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.vaadin.cdi.CDINavigator;
import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.ui.UI;

@NormalUIScoped
public class NavigationServiceImpl implements NavigationService {

    @Inject
    private CDINavigator navigator;

    @Inject
    private ErrorView errorView;

    @Inject
    private UI ui;

    @PostConstruct
    public void initialize() {
        if (ui.getNavigator() == null) {
            navigator.init(ui, ui);
            navigator.setErrorView(errorView);
        }
    }

    @Override
    public void onNavigationEvent(@Observes NavigationEvent event) {
        try {
            navigator.navigateTo(event.getNavigateTo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
