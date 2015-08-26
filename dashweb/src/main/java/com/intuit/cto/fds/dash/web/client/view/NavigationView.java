package com.intuit.cto.fds.dash.web.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Tree.Resources;
import com.google.gwt.user.client.ui.TreeItem;
import com.intuit.cto.fds.dash.web.client.presenter.NavigationPresenter;
import com.intuit.cto.fds.dash.web.shared.TreeResources;

public class NavigationView extends Composite implements NavigationPresenter.Display {
	final Tree navigationTree = new Tree((Resources) GWT.create(TreeResources.class), true);
	
	public NavigationView(){
		initWidget(navigationTree.asWidget());
		navigationTree.setSize("100%", "100%");
		TreeItem root = new TreeItem();
		root.setText("DASH");
		
		TreeItem providers = new TreeItem(new Label("Providers"));
		root.addItem(providers);
		TreeItem ein = new TreeItem(new Label("EIN"));
		root.addItem(ein);
		TreeItem logo = new TreeItem(new Label("Logo"));
		root.addItem(logo);
		TreeItem tickets = new TreeItem(new Label("Tickets"));
		root.addItem(tickets);
		root.setState(true);
		navigationTree.addItem(root);
		navigationTree.setAnimationEnabled(true);
		
	}
	
	@Override
	public Tree getNavigationTree() {
		return navigationTree;
	}

}
