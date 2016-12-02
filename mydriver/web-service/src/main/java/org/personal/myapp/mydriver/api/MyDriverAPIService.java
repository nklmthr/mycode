
package org.personal.myapp.mydriver.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Set;

import org.personal.myapp.mydriver.excpetion.BaseHttpException;
import org.personal.myapp.mydriver.model.Driver;
import org.personal.myapp.mydriver.model.DriverLocation;
import org.personal.myapp.mydriver.model.Location;
import org.personal.myapp.mydriver.model.ProximateDriver;
import org.personal.myapp.mydriver.service.DistanceCalculateService;
import org.personal.myapp.mydriver.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", methods = { RequestMethod.HEAD, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS, RequestMethod.DELETE, RequestMethod.PUT })

@RestController
@RequestMapping(value = "mydriver/", produces = { APPLICATION_JSON_VALUE })
@Api(value = "/mydriver")

public class MyDriverAPIService {

	private static final Logger logger = LoggerFactory.getLogger(MyDriverAPIService.class);

	@Autowired
	DistanceCalculateService distanceService;

	@Autowired
	DriverService driverService;

	@ApiOperation(value = "Get distance between two points", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Near-By Drivers"),
			@ApiResponse(code = 204, message = "No Response"),
			@ApiResponse(code = 401, message = "Authentication Failed"),
			@ApiResponse(code = 403, message = "Unauthorized"), @ApiResponse(code = 500, message = "Unexpected error"),
			@ApiResponse(code = 503, message = "Service unavailable") })
	@RequestMapping(value = "distance", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getDistance(@RequestParam(value = "Lattitude1", required = true) String lattitude1,
			@RequestParam(value = "Longitude1", required = true) String longitude1,
			@RequestParam(value = "Lattitude2", required = true) String lattitude2,
			@RequestParam(value = "Longitude2", required = true) String longitude2,
			@RequestParam(value = "unit", required = false) String unit) throws BaseHttpException {
		Location location1 = new Location(lattitude1, longitude1);
		Location location2 = new Location(lattitude2, longitude2);
		return String.valueOf(distanceService.distance(location1, location2, unit));
	}

	@ApiOperation(value = "Add new driver", response = Driver.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Driver Added Successfully"),
			@ApiResponse(code = 204, message = "No Response"),
			@ApiResponse(code = 401, message = "Authentication Failed"),
			@ApiResponse(code = 403, message = "Unauthorized"), @ApiResponse(code = 500, message = "Unexpected error"),
			@ApiResponse(code = 503, message = "Service unavailable") })
	@RequestMapping(value = "drivers", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Driver addDriver(@RequestParam(value = "name", required = true) String name) throws BaseHttpException {
		logger.info("Add Driver:" + name);
		Driver driver = driverService.addDriver(name);
		return driver;
	}

	@ApiOperation(value = "Update Driver Location", response = DriverLocation.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 401, message = "Unauthorized request"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "drivers/{driverId}/locations", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public DriverLocation updateDriverLocation(
			@ApiParam(value = "Driver ID to update", required = true) @PathVariable("driverId") String driverId,
			@ApiParam(value = "Location", required = true) @RequestBody Location location) throws BaseHttpException {

		DriverLocation driverLocation = driverService.updateLocation(driverId, location);
		return driverLocation;
	}

	@ApiOperation(value = "Get nearby drivers", response = ProximateDriver.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Near-By Drivers"),
			@ApiResponse(code = 204, message = "No Response"),
			@ApiResponse(code = 401, message = "Authentication Failed"),
			@ApiResponse(code = 403, message = "Unauthorized"), @ApiResponse(code = 500, message = "Unexpected error"),
			@ApiResponse(code = 503, message = "Service unavailable") })
	@RequestMapping(value = "drivers", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Set<ProximateDriver> getNearByDrivers(@RequestParam(value = "Lattitude", required = true) String lattitude,
			@RequestParam(value = "Longitude", required = true) String longitude,
			@RequestParam(value = "unit", required = false) String unit,
			@RequestParam(value = "radius", required = false) String radius,
			@RequestParam(value = "limit", required = false) String limit) throws BaseHttpException {
		Location location = new Location(lattitude, longitude);
		logger.info("location= " + location + " radius=" + radius + " limit=" + limit);
		Set<ProximateDriver> drivers = driverService.findNearByDrivers(location, unit, radius, limit);
		return drivers;
	}

	@ApiOperation(value = "Generate Test Data", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Generate Test Data"),
			@ApiResponse(code = 204, message = "No Response"),
			@ApiResponse(code = 401, message = "Authentication Failed"),
			@ApiResponse(code = 403, message = "Unauthorized"), @ApiResponse(code = 500, message = "Unexpected error"),
			@ApiResponse(code = 503, message = "Service unavailable") })
	@RequestMapping(value = "testdata", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String generateTestData(@RequestParam(value = "size", required = true) String sizeStr)
			throws BaseHttpException {
		int size = 0;
		try {
			size = Integer.parseInt(sizeStr);
		} catch (NumberFormatException e) {
			throw new BaseHttpException("Invalid Size", HttpStatus.BAD_REQUEST);
		}
		for (int i = 0; i < size; i++) {
			Driver driver = driverService.addDriver("Test" + size);
			DriverLocation driverLocation = driverService.updateLocation(driver.getId(),
					new Location(String.valueOf(i), String.valueOf(i)));
			logger.info("Driver Created "+driver+" location"+driverLocation.getLocation());
		}
		return "success";
	}

}
