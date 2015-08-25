package com.intuit.cto.fds.dash.web.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.intuit.cto.fds.dash.web.client.presenter.NavigationPresenter;

public class NavigationView extends Composite implements NavigationPresenter.Display {
	private Tree navigationTree = new Tree();
	
	public NavigationView(){
		initWidget(navigationTree.asWidget());
		navigationTree.setSize("100%", "100%");
		TreeItem root = new TreeItem();
		root.setText("Providers");
		
		TreeItem providers = new TreeItem(new Label("Details"));
		root.addItem(providers);
		
		TreeItem ein = new TreeItem(new Label("EIN"));
		root.addItem(ein);
		TreeItem logo = new TreeItem(new Label("Logo"));
		root.addItem(logo);
		root.setState(true);
		navigationTree.addItem(root);
		
	}
	
	@Override
	public Tree getNavigationTree() {
		return navigationTree;
	}

}
