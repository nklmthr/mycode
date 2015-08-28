package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;


public class ChannelsVO implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1L;

    protected List<ChannelVO> channel;

	public List<ChannelVO> getChannel() {
		return channel;
	}

	public void setChannel(List<ChannelVO> channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelsVO [channel=");
		builder.append(channel);
		builder.append("]");
		return builder.toString();
	}


}
