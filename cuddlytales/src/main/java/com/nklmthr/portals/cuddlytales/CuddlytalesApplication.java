package com.nklmthr.portals.cuddlytales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CuddlytalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuddlytalesApplication.class, args);
	}

}
