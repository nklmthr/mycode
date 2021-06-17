package com.nklmthr.apisetu.model;

import java.util.List;

public class Center {

	public Center(String name, String address, String from, String to) {
		this.name = name;
		this.address = address;
		this.from = from;
		this.to = to;
	}

	public String centerId;
	public String name;
	public String address;
	public String from;
	public String to;
	public List<Session> sesions;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Center [centerId=").append(centerId).append(", name=").append(name).append(", address=")
				.append(address).append(", from=").append(from).append(", to=").append(to).append(", sesions=")
				.append(sesions).append("]");
		return builder.toString();
	}

}