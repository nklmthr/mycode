package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class WebIntegrationVO extends ChannelVO implements IsSerializable, Serializable{

	private static final long serialVersionUID = 1L;

	public WebIntegrationVO() {
		preference = 5;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebIntegrationVO [super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	public ChannelType typeOfChannel() {
		return ChannelType.WEB_INTEGRATION;
	}
	
}
