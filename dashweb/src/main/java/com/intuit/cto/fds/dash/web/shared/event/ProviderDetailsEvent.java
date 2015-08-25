package com.intuit.cto.fds.dash.web.shared.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProviderDetailsEvent extends GwtEvent<ProviderDetailsEventHandler> {
	public static Type<ProviderDetailsEventHandler> type = new Type<ProviderDetailsEventHandler>();
	@Override
	protected void dispatch(ProviderDetailsEventHandler handler) {
		handler.onSelection(this);
	}

	@Override
	public Type<ProviderDetailsEventHandler> getAssociatedType() {
		return type;
	}

}
