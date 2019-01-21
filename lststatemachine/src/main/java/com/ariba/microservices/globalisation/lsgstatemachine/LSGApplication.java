package com.ariba.microservices.globalisation.lsgstatemachine;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ariba.microservices.globalisation.lsgstatemachine.scheduler.TestJob;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan("com.example.demo.model")
public class LSGApplication {

	public static void main(String[] args) {
		SpringApplication.run(LSGApplication.class, args);
	}

	@Bean
	public JobDetail sampleJobDetail() {
		JobDataMap map = new JobDataMap();
		map.put("name","nikhil");
		return JobBuilder.newJob(TestJob.class).withIdentity("testJob").usingJobData(map).storeDurably()
				.build();
	}

	@Bean
	public Trigger sampleJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
				.repeatForever();

		return TriggerBuilder.newTrigger().forJob(sampleJobDetail()).withIdentity("sampleTrigger")
				.withSchedule(scheduleBuilder).build();
	}

}
