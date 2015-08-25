package com.intuit.cto.fds.dash.web.client.presenter;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.intuit.cto.fds.dash.web.shared.event.ProviderDetailsEvent;
import com.intuit.cto.fds.dash.web.shared.event.ProviderDetailsEventHandler;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

public class ProviderPresenter extends WidgetPresenter<ProviderPresenter.Display> {

	public ProviderPresenter(Display display, EventBus eventBus) {
		super(display, eventBus);

		bind();
	}

	public interface Display extends WidgetDisplay {
		HorizontalPanel getCenterPanel();
	}

	@Override
	public void revealDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onBind() {
		this.eventBus.addHandler(ProviderDetailsEvent.type, new ProviderDetailsEventHandler() {
			@Override
			public void onSelection(ProviderDetailsEvent event) {
				DockLayoutPanel dlp = (DockLayoutPanel) RootLayoutPanel.get().getWidget(0);
				dlp.remove(dlp.getWidget(3));
				dlp.add(display.getCenterPanel().asWidget());
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
