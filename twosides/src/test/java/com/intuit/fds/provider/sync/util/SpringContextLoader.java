package com.intuit.fds.provider.sync.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextLoader {
	private static ApplicationContext testApplicationContext = new ClassPathXmlApplicationContext(
			"spring_context_test_provider_restservices.xml");

	public static ApplicationContext getApplicationContext() {
		return testApplicationContext;
	}
}
