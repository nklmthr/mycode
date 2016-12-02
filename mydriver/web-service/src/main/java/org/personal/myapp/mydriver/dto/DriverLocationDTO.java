package org.personal.myapp.mydriver.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DRIVER_LOCATION")
public class DriverLocationDTO {
	@Id
	@Column(name = "ID")
	private String driverLocationId;

	@Column(name = "DRIVER_ID")
	private String driverId;

	@Column(name = "LAT")
	private String lattitude;

	@Column(name = "LON")
	private String longitude;

	public String getDriverLocationId() {
		return driverLocationId;
	}

	public void setDriverLocationId(String driverLocationId) {
		this.driverLocationId = driverLocationId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
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
		return "DriverLocationDTO [driverLocationId=" + driverLocationId + ", driverId=" + driverId + ", lattitude="
				+ lattitude + ", longitude=" + longitude + "]";
	}

}
