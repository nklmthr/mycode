package com.srishti.geektrust.metro.reports;

public class PassengerSummary {
	private String category;
	private int passengerCount;

	public PassengerSummary() {
		super();
	}

	public PassengerSummary(String category, int passengerCount) {
		super();
		this.category = category;
		this.passengerCount = passengerCount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

}
