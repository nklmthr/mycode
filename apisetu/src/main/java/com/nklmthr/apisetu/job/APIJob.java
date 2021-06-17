package com.nklmthr.apisetu.job;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.nklmthr.apisetu.service.AppointmentService;

public class APIJob extends QuartzJobBean {
	private static final Logger logger = Logger.getLogger(QuartzJobBean.class);
	@Autowired
	private AppointmentService service;

	private int minAge;

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("Starting search for available appointments for " + minAge + "+");
		try {
			service.findAppointmentsAvailabilityandNotify(minAge);
		} catch (ParseException | IOException e) {			
			e.printStackTrace();
		}
		logger.info("Completed search for available appointments for " + minAge + "+");
	}

}
