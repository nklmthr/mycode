package com.nklmthr.apisetu;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nklmthr.apisetu.job.APIJob;

@SpringBootApplication
public class DemoApplication {

	@Value("${appointment.minAge}")
	private int minAge;
	
	public static void main(String[] args) {		
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public JobDetail sampleJobDetail() {
		return JobBuilder.newJob(APIJob.class).withIdentity("AppointmentAvailabilityJob")
				.usingJobData("minAge", minAge).storeDurably().build();
	}
	
	@Bean
	public Trigger sampleJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(20000).repeatForever();

		return TriggerBuilder.newTrigger().forJob(sampleJobDetail())
				.withIdentity("sampleTrigger").withSchedule(scheduleBuilder).build();
	}
}
