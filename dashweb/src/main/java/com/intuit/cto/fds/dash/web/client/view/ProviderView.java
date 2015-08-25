package com.intuit.cto.fds.dash.web.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.intuit.cto.fds.dash.web.client.presenter.ProviderPresenter;

public class ProviderView extends Composite implements ProviderPresenter.Display {
	HorizontalPanel panel = new HorizontalPanel();
	public ProviderView() {
		panel.setStyleName("contentPanel");
		panel.add(new Label("Hello Provider"));
		
	}

	@Override
	public HorizontalPanel getCenterPanel() {
		return panel;
	}

}
