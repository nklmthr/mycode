package com.nklmthr.apisetu.model;

public class Session {
	public Session(String date, String capacity, String minAge, String vaccine) {
		this.date = date;
		this.availCapacity = capacity;
		this.minAge = minAge;
		this.vaccine = vaccine;

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Session [date=").append(date).append(", availCapacity=").append(availCapacity)
				.append(", minAge=").append(minAge).append(", vaccine=").append(vaccine).append("]");
		return builder.toString();
	}

	public String date;
	public String availCapacity;
	public String minAge;
	public String vaccine;
}