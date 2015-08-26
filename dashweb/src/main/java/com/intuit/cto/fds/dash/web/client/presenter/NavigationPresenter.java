package com.intuit.cto.fds.dash.web.client.presenter;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.intuit.cto.fds.dash.web.client.view.EINView;
import com.intuit.cto.fds.dash.web.client.view.ProviderView;
import com.intuit.cto.fds.dash.web.shared.event.ProviderDetailsEvent;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

public class NavigationPresenter extends WidgetPresenter<NavigationPresenter.Display> {
	
	public NavigationPresenter(Display display, EventBus eventBus) {
		super(display, eventBus);
		ProviderPresenter providerPresenter = new ProviderPresenter(new ProviderView(), eventBus);
		EINPresenter einPresenter = new EINPresenter(new EINView(), eventBus);
		bind();
	}

	public interface Display extends WidgetDisplay {
		Tree getNavigationTree();
	}

	@Override
	protected void onBind() {
		Tree tree = display.getNavigationTree();
		tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				TreeItem item = event.getSelectedItem();
				if (item.getText().equalsIgnoreCase("Providers")) {
					eventBus.fireEvent(new ProviderDetailsEvent());
				} else if (item.getText().equalsIgnoreCase("EIN")) {
					Window.alert("EIN");
				}

			}
		});
	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onRevealDisplay() {
		// TODO Auto-generated method stub

	}

}
