package com.intuit.cto.fds.dash.web.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.intuit.cto.fds.dash.web.client.presenter.LandingPresenter;

public class LadningView extends Composite implements LandingPresenter.Display {
	private Label label = new Label();

	public LadningView() {
		initWidget(label);
		label.setText("Content");
	}


}
