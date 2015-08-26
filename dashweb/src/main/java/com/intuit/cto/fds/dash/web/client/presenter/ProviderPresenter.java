package com.intuit.cto.fds.dash.web.client.presenter;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.intuit.cto.fds.dash.web.client.service.ProviderService;
import com.intuit.cto.fds.dash.web.client.service.ProviderServiceAsync;
import com.intuit.cto.fds.dash.web.shared.event.ProviderDetailsEvent;
import com.intuit.cto.fds.dash.web.shared.event.ProviderDetailsEventHandler;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ProviderDTO;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

public class ProviderPresenter extends WidgetPresenter<ProviderPresenter.Display> {

	final ProviderServiceAsync providerService = GWT.create(ProviderService.class);

	public ProviderPresenter(Display display, EventBus eventBus) {
		super(display, eventBus);

		bind();
	}

	public interface Display extends WidgetDisplay {
		Button getSearchProviderButton();

		VerticalPanel getCenterPanel();

		TextBox getTextInputLegacyId();

		TextBox getTextInputProviderId();

		void showProvider(ProviderDTO provider);

		HorizontalPanel getProviderDetailPanel();
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
		display.getSearchProviderButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				display.getProviderDetailPanel().clear();
				providerService.getProviderDetails(display.getTextInputProviderId().getValue(),
						new AsyncCallback<ProviderDTO>() {

					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());

					}

					public void onSuccess(ProviderDTO providerDTO) {
						display.getProviderDetailPanel().clear();
						display.showProvider(providerDTO);
					}
				});
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
