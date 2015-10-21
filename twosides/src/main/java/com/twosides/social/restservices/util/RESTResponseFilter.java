package com.twosides.social.restservices.util;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class RESTResponseFilter implements ContainerResponseFilter {

	private static final Logger logger = Logger.getLogger(RESTResponseFilter.class);

	public void filter(ContainerRequestContext containerRequestContext,
			ContainerResponseContext containerResponseContext) throws IOException {
		logger.info("RESTResponseFilter");
		WebContext.removeInstance();
	}
}