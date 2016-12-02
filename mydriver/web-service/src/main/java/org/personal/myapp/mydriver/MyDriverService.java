package org.personal.myapp.mydriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.personal.myapp.mydriver.dao")
public class MyDriverService {
	
	public static void main(String[] args) {
		SpringApplication.run(MyDriverService.class, args);
	}

}
