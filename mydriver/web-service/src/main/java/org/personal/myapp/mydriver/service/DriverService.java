package org.personal.myapp.mydriver.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.personal.myapp.mydriver.dao.DriverDAO;
import org.personal.myapp.mydriver.dao.DriverLocationDAO;
import org.personal.myapp.mydriver.dto.DriverDTO;
import org.personal.myapp.mydriver.dto.DriverLocationDTO;
import org.personal.myapp.mydriver.excpetion.BaseHttpException;
import org.personal.myapp.mydriver.model.Driver;
import org.personal.myapp.mydriver.model.DriverLocation;
import org.personal.myapp.mydriver.model.Location;
import org.personal.myapp.mydriver.model.ProximateDriver;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DriverService {

	@Autowired
	DriverDAO driverDAO;

	@Autowired
	DriverLocationDAO driverLocationDAO;

	@Autowired
	DistanceCalculateService calculator;

	@Autowired
	Mapper dozzerMapping;

	private static Logger logger = Logger.getLogger(DriverService.class);

	public Driver addDriver(String name) {
		DriverDTO driverDTO = new DriverDTO();
		driverDTO.setName(name);
		driverDTO.setId(UUID.randomUUID().toString());
		driverDTO = driverDAO.saveAndFlush(driverDTO);
		Driver driver = dozzerMapping.map(driverDTO, Driver.class);
		return driver;
	}

	public Set<ProximateDriver> findNearByDrivers(Location location, String unit, String radiusStr, String limitStr)
			throws BaseHttpException {
		int radius = 0;
		int limit = 0;
		try {
			if (StringUtils.isBlank(radiusStr)) {
				radius = 100;
			} else {
				radius = Integer.parseInt(radiusStr);
			}
			if (StringUtils.isBlank(limitStr)) {
				limit = 2;
			} else {
				limit = Integer.parseInt(limitStr);
			}
		} catch (NumberFormatException e) {
			throw new BaseHttpException("Invalid Numberic format for radius/limit", HttpStatus.BAD_REQUEST);
		}
		
		Set<ProximateDriver> proximityDriverList = new TreeSet<ProximateDriver>();
		List<DriverLocationDTO> driverLocations = driverLocationDAO.findAll();
		for (DriverLocationDTO driverLocation : driverLocations) {
			logger.info("Sytem driver Info id=" + driverLocation.getDriverId() + " Location="
					+ driverLocation.getLattitude() + " ," + driverLocation.getLongitude());
			double distanceFromCustomer = calculator.distance(location,
					new Location(driverLocation.getLattitude(), driverLocation.getLongitude()), unit);
			logger.debug("distanceFromCustomer=" + distanceFromCustomer);
			if (distanceFromCustomer < radius) {
				if (proximityDriverList.size() < limit) {
					ProximateDriver proximateDriver = new ProximateDriver(driverLocation.getDriverId(),
							new Location(driverLocation.getLattitude(), driverLocation.getLongitude()),
							distanceFromCustomer);
					proximityDriverList.add(proximateDriver);
					logger.info("Added " + proximateDriver);
					logger.debug("proximityDriverList=" + proximityDriverList.stream().map(ProximateDriver::getDistance)
							.collect(Collectors.toList()));
				} else {
					logger.info("Limit full trying other drivers");
					Set<ProximateDriver> proximityDriverListCopy = new TreeSet<ProximateDriver>();
					proximityDriverListCopy.addAll(proximityDriverList);

					for (ProximateDriver driver : proximityDriverListCopy) {
						logger.debug("before proximityDriverList driver:" + driver.getLocation()
								+ proximityDriverList.stream().map(ProximateDriver::getLocation)
										.collect(Collectors.toList())
								+ " compare =" + driverLocation.getLattitude() + " ," + driverLocation.getLongitude());
						logger.debug("distance compare: " + distanceFromCustomer + ", " + driver.getDistance());
						if (distanceFromCustomer < driver.getDistance()
								&& !driver.getDriverId().equalsIgnoreCase(driverLocation.getDriverId())) {
							proximityDriverList.remove(driver);
							proximityDriverList.add(new ProximateDriver(driverLocation.getDriverId(),
									new Location(driverLocation.getLattitude(), driverLocation.getLongitude()),
									distanceFromCustomer));
							logger.info("After proximityDriverList driver:" + driver.getLocation()
									+ proximityDriverList.stream().map(ProximateDriver::getLocation)
											.collect(Collectors.toList())
									+ " compare =" + driverLocation.getLattitude() + " ,"
									+ driverLocation.getLongitude());
							break;
						}

					}
				}
			}
		}
		return proximityDriverList;

	}

	public DriverLocation updateLocation(String driverId, Location location) {
		DriverLocationDTO driverLocationDTO = driverLocationDAO.getDriverDetails(driverId);
		if (driverLocationDTO != null) {
			driverLocationDTO.setLattitude(location.getLattitude());
			driverLocationDTO.setLongitude(location.getLongitude());
		} else {
			driverLocationDTO = new DriverLocationDTO();
			driverLocationDTO.setDriverLocationId(UUID.randomUUID().toString());
			driverLocationDTO.setDriverId(driverId);
			driverLocationDTO.setLattitude(location.getLattitude());
			driverLocationDTO.setLongitude(location.getLongitude());
		}
		driverLocationDTO = driverLocationDAO.saveAndFlush(driverLocationDTO);
		DriverLocation driverLocation = dozzerMapping.map(driverLocationDTO, DriverLocation.class);
		return driverLocation;
	}

}
