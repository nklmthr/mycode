package com.nklmthr.project.myfinance;

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

import com.nklmthr.project.myfinance.job.TimedJob;

@SpringBootApplication
@EnableJpaRepositories("com.nklmthr.project.myfinance.repository")
@EntityScan("com.nklmthr.project.myfinance.model")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public JobDetail sampleJobDetail() {
		return JobBuilder.newJob(TimedJob.class).withIdentity("sampleJob")
				.usingJobData("jobTestString", "World").storeDurably().build();
	}
	
	@Bean
	public Trigger sampleJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(60).repeatForever();

		return TriggerBuilder.newTrigger().forJob(sampleJobDetail())
				.withIdentity("sampleTrigger").withSchedule(scheduleBuilder).build();
	}
}
