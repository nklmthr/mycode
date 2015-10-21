package com.twosides.social.restservices.util;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextLoader {
	static Logger logger = Logger.getLogger(SpringContextLoader.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			"spring_context_restservices.xml");

	public static ApplicationContext getContext() {
		logger.info("SpringContextLoader");
		return context;
	}
}
