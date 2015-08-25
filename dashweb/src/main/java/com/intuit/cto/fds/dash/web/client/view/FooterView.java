package com.intuit.cto.fds.dash.web.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.intuit.cto.fds.dash.web.client.presenter.FooterPresenter;

public class FooterView extends Composite implements FooterPresenter.Display{

	private Label label = new Label();
	public FooterView(){
		initWidget(label);
		label.setText("CTO-Dev FDS DASH");
	}

}
