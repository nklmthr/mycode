package com.intuit.cto.fds.dash.web.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.intuit.cto.fds.dash.web.client.presenter.ContentPresenter;

public class ContentView extends Composite implements ContentPresenter.Display {
	private Label label = new Label();

	public ContentView() {
		initWidget(label);
		label.setText("Content");
	}


}
