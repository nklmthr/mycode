package com.twosides.social.restservices.util;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
@PreMatching
public class RESTRequestPreMatchFilter implements ContainerRequestFilter {

	private static final Logger logger = Logger.getLogger(RESTRequestPreMatchFilter.class);

	public void filter(ContainerRequestContext requestContext) throws IOException {

		WebContext providerContext = WebContext.getInstance();
		logger.info("RESTRequestPreMatchFilter");

	}
}
