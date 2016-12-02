package org.personal.myapp.mydriver.model;

public class Location {
	private String lattitude;
	private String longitude;

	public Location() {
		super();
	}

	public Location(String lattitude, String longitude) {
		this.lattitude = lattitude;
		this.longitude = longitude;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "{"+lattitude + ", " + longitude+"}";
	}

}
