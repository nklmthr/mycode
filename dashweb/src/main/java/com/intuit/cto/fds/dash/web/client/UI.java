package com.intuit.cto.fds.dash.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.intuit.cto.fds.dash.web.client.presenter.ContentPresenter;
import com.intuit.cto.fds.dash.web.client.presenter.FooterPresenter;
import com.intuit.cto.fds.dash.web.client.presenter.HeaderPresenter;
import com.intuit.cto.fds.dash.web.client.presenter.NavigationPresenter;
import com.intuit.cto.fds.dash.web.client.presenter.ProviderPresenter;
import com.intuit.cto.fds.dash.web.client.view.ContentView;
import com.intuit.cto.fds.dash.web.client.view.FooterView;
import com.intuit.cto.fds.dash.web.client.view.HeaderView;
import com.intuit.cto.fds.dash.web.client.view.NavigationView;
import com.intuit.cto.fds.dash.web.client.view.ProviderView;

import net.customware.gwt.presenter.client.DefaultEventBus;

public class UI implements EntryPoint {
	protected static final String SUCCESS = "SUCCESS";
	DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.EM);

	public void onModuleLoad() {
		final DefaultEventBus eventBus = new DefaultEventBus();
		
		HorizontalPanel headerPanel = new HorizontalPanel();
		headerPanel.setStyleName("headerFooterPanel");
		mainPanel.addNorth(headerPanel, 5);

		HorizontalPanel footerPanel = new HorizontalPanel();
		footerPanel.setStyleName("headerFooterPanel");
		mainPanel.addSouth(footerPanel, 5);

		VerticalPanel navigationPanel = new VerticalPanel();
		navigationPanel.setStyleName("navigationPanel");
		mainPanel.addWest(navigationPanel, 15);

		HorizontalPanel contentPanel = new HorizontalPanel();
		contentPanel.setStyleName("contentPanel");
		
		mainPanel.add(contentPanel);
		RootLayoutPanel.get().add(mainPanel);

		headerPanel.add(new HeaderPresenter(new HeaderView(), eventBus).getDisplay().asWidget());

		footerPanel.add(new FooterPresenter(new FooterView(), eventBus).getDisplay().asWidget());

		navigationPanel.add(new NavigationPresenter(new NavigationView(), eventBus).getDisplay().asWidget());

		contentPanel.add(new ContentPresenter(new ContentView(), eventBus).getDisplay().asWidget());

	}

}
