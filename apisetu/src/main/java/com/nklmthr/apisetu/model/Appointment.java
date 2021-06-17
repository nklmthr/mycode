package com.nklmthr.apisetu.model;

import java.util.List;


public class Appointment {
	public String stateId;
	public String stateName;
	public List<District> districts;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Appointment [stateId=").append(stateId).append(", stateName=").append(stateName)
				.append(", districts=").append(districts).append("]");
		return builder.toString();
	}
	
}

