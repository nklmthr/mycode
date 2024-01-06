package com.nklmthr.finance.personal;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.nklmthr.finance.personal")
public class Application extends SpringBootServletInitializer {
	private static Logger logger = Logger.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("Application Started...");
	}
}
