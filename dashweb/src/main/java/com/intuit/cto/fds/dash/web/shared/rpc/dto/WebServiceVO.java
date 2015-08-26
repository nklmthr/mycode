package com.intuit.cto.fds.dash.web.shared.rpc.dto;

public class WebServiceVO extends ChannelVO {

	private static final long serialVersionUID = 1L;

	public WebServiceVO() {
		preference = 2;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebServiceVO [super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	public ChannelType typeOfChannel() {
		return ChannelType.WEB_SERVICE;
	}

}
