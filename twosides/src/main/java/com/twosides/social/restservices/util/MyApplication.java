package com.twosides.social.restservices.util;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider;

public class MyApplication extends ResourceConfig {
	Logger logger = Logger.getLogger(MyApplication.class);
	public MyApplication() {
		logger.info("MyApplication");
		packages("com.twosides.social.restservices,org.codehaus.jackson.jaxrs");
		//register(JacksonJaxbXMLProvider.class);
	}
}
