package com.intuit.cto.fds.dash.web.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.intuit.cto.fds.dash.web.client.presenter.HeaderPresenter;
import com.intuit.cto.fds.dash.web.shared.ImageResources;

public class HeaderView extends Composite implements HeaderPresenter.Display {
	HorizontalPanel headerPanel = new HorizontalPanel();

	public HeaderView() {
		initWidget(headerPanel);
		headerPanel.setWidth("100%");
		Grid lGrid = new Grid(1, 2);
		lGrid.setWidth("100%");
		Image dashLogo = new Image(ImageResources.images.getDashLogo());
		Image intuitLogo = new Image(ImageResources.images.getIntuitLogo());
		HTMLTable.CellFormatter formatter = lGrid.getCellFormatter();
		formatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		lGrid.setWidget(0, 0, dashLogo);
		lGrid.setWidget(0, 1, intuitLogo);
		headerPanel.add(lGrid);
	}

}
