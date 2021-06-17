package com.nklmthr.apisetu.model;

import java.util.List;

public class District {
	public District(String id, String name) {
		this.districtId = id;
		this.districtName = name;
	}

	public String districtId;
	public String districtName;
	public List<Center> centers;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("District [districtId=").append(districtId).append(", districtName=").append(districtName)
				.append(", centers=").append(centers).append("]");
		return builder.toString();
	}

}