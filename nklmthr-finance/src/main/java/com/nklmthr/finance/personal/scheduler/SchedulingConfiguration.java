package com.nklmthr.finance.personal.scheduler;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {
	@Autowired private ApplicationContext applicationContext;
	@Scheduled(cron = "${yesbank.cc.cron.expression}")
	public void runYesCCJob() throws GeneralSecurityException, IOException, ParseException {
		ScheduledTask task = applicationContext.getBean(YesBankCCSchedulerImpl.class);
		task.doScheduledTask();
	}

	@Scheduled(cron = "${sbi.cc.cron.expression}")
	public void runSBICCJob() throws GeneralSecurityException, IOException, ParseException {
		ScheduledTask task = applicationContext.getBean(SBICCSchedulerImpl.class);
		task.doScheduledTask();
	}

	@Scheduled(cron = "${axis.cc.cron.expression}")
	public void runAxisCCJob() throws GeneralSecurityException, IOException, ParseException {
		ScheduledTask task = applicationContext.getBean(AxisBankCCSchedulerImpl.class);
		task.doScheduledTask();
	}

	@Scheduled(cron = "${citi.sb.cron.expression}")
	public void runCitiSvgJob() throws GeneralSecurityException, IOException, ParseException {
		ScheduledTask task = (ScheduledTask) applicationContext.getBean(CitiSBScheduleImpl.class);
		task.doScheduledTask();
	}

}
