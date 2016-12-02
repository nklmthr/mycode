package org.personal.myapp.mydriver.api;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApi {

	@RequestMapping(value = "/health/full", method = RequestMethod.GET, produces = { TEXT_PLAIN_VALUE })
	public String check() {
		return "Health Check Ok";
	}
}