package com.twosides.social.restservices.util;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class RESTRequestFilter implements ContainerRequestFilter {

	private static final Logger logger = Logger.getLogger(RESTRequestFilter.class);

	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("RESTRequestFilter");
	}
}