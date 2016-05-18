package com.nklmthr.games.game29.context;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan(basePackages = { "com.nklmthr.*" })
@PropertySources({ @PropertySource("classpath:application.properties") })

public class SpringApplicationContext {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("spring_context.xml");
	private static Logger logger = Logger.getLogger(SpringApplicationContext.class);

	public static ApplicationContext getSpringContext(){
		logger.debug("Get Spring Context");
		return context;
	}
}
