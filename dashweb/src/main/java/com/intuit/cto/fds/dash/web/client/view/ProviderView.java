package com.intuit.cto.fds.dash.web.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.intuit.cto.fds.dash.web.client.presenter.ProviderPresenter;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ProviderDTO;


public class ProviderView extends Composite implements ProviderPresenter.Display {
	VerticalPanel panel = new VerticalPanel();
	Button searchProviderbutton = new Button("Search");
	TextBox textInputProviderId = new TextBox();
	TextBox textInputLegacyId = new TextBox();
	HorizontalPanel providerDetailPanel = new HorizontalPanel();
	public ProviderView() {
		
		panel.setStyleName("contentPanel");

		Grid grid = new Grid(1, 5);
		grid.setWidth("100%");
		Label providerIdLabel = new Label("Provider Id");
		providerIdLabel.setStyleName("label");
		grid.setWidget(0, 0, providerIdLabel);
		
		textInputProviderId.setStyleName("searchTextBox");
		grid.setWidget(0, 1, textInputProviderId);

		Label legacyIdLabel = new Label("Legacy Id");
		legacyIdLabel.setStyleName("label");
		grid.setWidget(0, 2, legacyIdLabel);
		
		textInputLegacyId.setStyleName("searchTextBox");

		grid.setWidget(0, 3, textInputLegacyId);
		
		searchProviderbutton.setStyleName("button");
		grid.setWidget(0, 4, searchProviderbutton);
		grid.setStyleName("headerBar");
		panel.add(grid);
		panel.add(providerDetailPanel);
	}

	@Override
	public Button getSearchProviderButton() {
		return searchProviderbutton;
	}

	@Override
	public VerticalPanel getCenterPanel() {
		return panel;
	}

	@Override
	public TextBox getTextInputLegacyId() {
		return textInputLegacyId;
	}

	@Override
	public TextBox getTextInputProviderId() {
		return textInputProviderId;
	}

	@Override
	public void showProvider(ProviderDTO provider) {
		Grid grid = new Grid(5,2);
		//grid.setWidth("40%");
		grid.setStyleName("providerDetailGrid");
		grid.setWidget(0, 0, new Label("Provider ID"));
		grid.setWidget(0, 1, new Label(provider.getId()));
		
		grid.setWidget(1, 0, new Label("Legacy ID"));
		grid.setWidget(1, 1, new Label(provider.getLegacyId()));
		
		grid.setWidget(2, 0, new Label("Name"));
		grid.setWidget(2, 1, new Label(provider.getName()));
		
		grid.setWidget(3, 0, new Label("Type"));
		grid.setWidget(3, 1, new Label(provider.getType()));
		providerDetailPanel.setStyleName("providerDetailGrid");
		providerDetailPanel.add(grid);
		
	}

	@Override
	public HorizontalPanel getProviderDetailPanel() {
		return providerDetailPanel;
	}

}
