package com.sap.ariba;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sap.ariba.job.DocumentJob;

@SpringBootApplication
@EnableJpaRepositories("com.sap.ariba.repository")
@EntityScan("com.sap.ariba.model")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public JobDetail documentJobDetail() {
		return JobBuilder.newJob(DocumentJob.class).storeDurably().build();
	}

	@Bean
	public Trigger sampleJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(documentJobDetail()).withSchedule(scheduleBuilder).build();
	}
}
