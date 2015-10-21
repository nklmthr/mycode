package com.twosides.social.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.twosides.social.restservices.util.SpringContextLoader;
import com.twosides.social.service.HealthService;

@Path("/v1/health")
public class HealthCheckRESTService {

	HealthService service = SpringContextLoader.getContext().getBean(HealthService.class);

	@GET
	@Path("/check")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getHealthCheck() {
		return Response.ok().entity(service.getHealth()).build();
	}

}
