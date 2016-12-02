package org.personal.myapp.mydriver.service;

import org.personal.myapp.mydriver.excpetion.BaseHttpException;
import org.personal.myapp.mydriver.model.Location;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Service
@Configuration
@EnableEncryptableProperties
@PropertySources({ @PropertySource("classpath:application-${spring.profiles.active:dev}.properties"),
		@PropertySource(value = "classpath:local.properties", ignoreResourceNotFound = true) })
public class DistanceCalculateService {
	public double distance(Location location1, Location location2, String unitStr) throws BaseHttpException {
		double lat1, lon1, lat2, lon2;
		char unit;
		try {
			lat1 = Double.valueOf(location1.getLattitude());
			lon1 = Double.valueOf(location1.getLongitude());
			lat2 = Double.valueOf(location2.getLattitude());
			lon2 = Double.valueOf(location2.getLongitude());

		} catch (NumberFormatException e) {
			throw new BaseHttpException("Coordinate inputs are incorrect", HttpStatus.BAD_REQUEST);
		}
		if (unitStr.length() == 1 && (unitStr.toLowerCase().charAt(0) == 'k' || unitStr.toLowerCase().charAt(0) == 'm'
				|| unitStr.toLowerCase().charAt(0) == 'n')) {
			unit = unitStr.toUpperCase().charAt(0);
		} else if (unitStr.length() == 0) {
			unit = 'K';
		} else
			throw new BaseHttpException("Units should be defined in 'K'=kms, 'M'=miles, 'N'=Nautical Miles",
					HttpStatus.BAD_REQUEST);

		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist =

				rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}
