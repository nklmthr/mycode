package com.intuit.cto.fds.dash.web.client.view;

import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.intuit.cto.fds.dash.web.client.presenter.ProviderPresenter;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ChannelVO;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ProviderDTO;

public class ProviderView extends Composite implements ProviderPresenter.Display {
	DockLayoutPanel providerPanel = new DockLayoutPanel(Unit.PCT);
	Button searchProviderbutton = new Button("Search");
	TextBox textInputProviderId = new TextBox();
	TextBox textInputLegacyId = new TextBox();
	VerticalPanel providerDetailPanel = new VerticalPanel();
	StackLayoutPanel channelsPanel = new StackLayoutPanel(Unit.PCT);

	public ProviderView() {
		Grid headerGrid = new Grid(1, 5);
		headerGrid.setCellPadding(0);
		headerGrid.setCellSpacing(0);
		Label providerIdLabel = new Label("Provider Id");
		providerIdLabel.setStyleName("label");
		headerGrid.setWidget(0, 0, providerIdLabel);

		textInputProviderId.setStyleName("searchTextBox");
		headerGrid.setWidget(0, 1, textInputProviderId);

		Label legacyIdLabel = new Label("Legacy Id");
		legacyIdLabel.setStyleName("label");
		headerGrid.setWidget(0, 2, legacyIdLabel);

		textInputLegacyId.setStyleName("searchTextBox");

		headerGrid.setWidget(0, 3, textInputLegacyId);

		searchProviderbutton.setStyleName("button");
		headerGrid.setWidget(0, 4, searchProviderbutton);
		headerGrid.setStyleName("headerBar");

		providerPanel.addNorth(headerGrid, 7);
		providerPanel.addWest(providerDetailPanel, 25);
		providerPanel.add(channelsPanel);
		providerPanel.setStyleName("contentPanel");

	}

	@Override
	public Button getSearchProviderButton() {
		return searchProviderbutton;
	}

	@Override
	public DockLayoutPanel getContentPanel() {
		return providerPanel;
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
		Grid providerDetailgrid = new Grid(4, 2);
		providerDetailgrid.setStyleName("providerDetailGrid");
		providerDetailgrid.setWidget(0, 0, new Label("Provider ID"));
		providerDetailgrid.setWidget(0, 1, new Label(provider.getId()));

		providerDetailgrid.setWidget(1, 0, new Label("Legacy ID"));
		providerDetailgrid.setWidget(1, 1, new Label(provider.getLegacyId()));

		providerDetailgrid.setWidget(2, 0, new Label("Name"));
		providerDetailgrid.setWidget(2, 1, new Label(provider.getName()));

		providerDetailgrid.setWidget(3, 0, new Label("Type"));
		providerDetailgrid.setWidget(3, 1, new Label(provider.getType()));
		providerDetailPanel.add(providerDetailgrid);
		showChannels(provider);

	}

	private void showChannels(ProviderDTO provider) {
		List<ChannelVO> channels = provider.getChannels();
		for (ChannelVO channel : channels) {
			HorizontalPanel channelPanel = new HorizontalPanel();
			Grid channelGrid = new Grid(4,2);
			channelGrid.setWidget(0, 0, new Label("ChannelType"));
			channelGrid.setWidget(0, 1, new Label(channel.getChannelType()));

			channelGrid.setWidget(1, 0, new Label("Channel URL"));
			channelGrid.setWidget(1, 1, new Label(channel.getUrl()));

			channelGrid.setWidget(2, 0, new Label("Channel Preference"));
			channelGrid.setWidget(2, 1, new Label(String.valueOf(channel.getPreference())));

			channelGrid.setWidget(3, 0, new Label("Uses MFA"));
			channelGrid.setWidget(3, 1, new Label(String.valueOf(channel.getUsesMFA())));
			channelGrid.setStyleName("providerDetailGrid");
			channelPanel.setStyleName("providerChannelPanel");
			channelPanel.add(channelGrid);
			channelsPanel.add(channelPanel, new Label(channel.getChannelType()), 7);
		}
	}

	@Override
	public VerticalPanel getProviderDetailPanel() {
		return providerDetailPanel;
	}

	@Override
	public StackLayoutPanel getChannelDetailsPanel() {
		return channelsPanel;
	}

}
