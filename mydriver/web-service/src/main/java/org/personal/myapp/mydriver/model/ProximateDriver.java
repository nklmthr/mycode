package org.personal.myapp.mydriver.model;

public class ProximateDriver implements Comparable<ProximateDriver> {
	private String driverId;
	private Location location;
	private double distance;

	public ProximateDriver() {
		super();
	}

	public ProximateDriver(String driverId, Location location, double distance) {
		super();
		this.driverId = driverId;
		this.location = location;
		this.distance = distance;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "ProximateDriver [driverId=" + driverId + ", location=" + location + ", distance=" + distance + "]";
	}

	@Override
	public int compareTo(ProximateDriver o) {
		if (this.distance > o.distance)
			return -1;
		else if (this.distance < o.distance)
			return 1;
		return 0;
	}

}
