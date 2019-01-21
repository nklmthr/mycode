package com.nklmthr.projects.utilities.thisdaythatyearmedia.main;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.nklmthr.projects.utilities.thisdaythatyearmedia.job.MyJob;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@ComponentScan("com.nklmthr.projects.utilities.thisdaythatyearmedia.job")
@ComponentScan("com.nklmthr.projects.utilities.thisdaythatyearmedia.media")
public class MainApplication {
	Logger logger = Logger.getLogger(MainApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public JobDetail sampleJobDetail() {
		return JobBuilder.newJob(MyJob.class).withIdentity("sampleJob").usingJobData("jobTestString", "World")
				.storeDurably().build();
	}

	@Bean
	public Trigger sampleJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24)
				.repeatForever();
		// SimpleScheduleBuilder scheduleBuilder =
		// SimpleScheduleBuilder.simpleSchedule()
		// .withIntervalInSeconds(2).repeatForever();
		logger.info("Print this");
		return TriggerBuilder.newTrigger().forJob(sampleJobDetail()).withIdentity("sampleTrigger")
				.withSchedule(scheduleBuilder).build();
	}

}
