package com.intuit.cto.fds.dash.web.client.presenter;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

public class ContentPresenter extends WidgetPresenter<ContentPresenter.Display> {
	public ContentPresenter(Display display, EventBus eventBus) {
		super(display, eventBus);
		bind();
	}

	public interface Display extends WidgetDisplay {

	}

	@Override
	protected void onBind() {
		// TODO Auto-generated method stub
		
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
