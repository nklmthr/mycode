package com.nklmthr.finance.personal.scheduler;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.nklmthr.finance.personal.exception.InvalidMessageException;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {
	@Autowired private ApplicationContext applicationContext;
	@Scheduled(cron = "${yesbank.cc.cron.expression}")
	public void runYesCCJob() throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		ScheduledTask task = applicationContext.getBean(YesBankCCSchedulerImpl.class);
		task.doScheduledTask();
	}

	@Scheduled(cron = "${sbi.cc.cron.expression}")
	public void runSBICCJob() throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		ScheduledTask task = applicationContext.getBean(SBICCSchedulerImpl.class);
		task.doScheduledTask();
	}

	@Scheduled(cron = "${axis.cc.cron.expression}")
	public void runAxisCCJob() throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		ScheduledTask task = applicationContext.getBean(AxisBankCCSchedulerImpl.class);
		task.doScheduledTask();
	}

	@Scheduled(cron = "${axis.sb.cron.expression}")
	public void runCitiSvgJob() throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		ScheduledTask task = (ScheduledTask) applicationContext.getBean(AxisSBATMScheduleImpl.class);
		task.doScheduledTask();
	}
	
	@Scheduled(cron = "${axis.sb.cron.expression}")
	public void runAxisSvgATMJob() throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		ScheduledTask task = (ScheduledTask) applicationContext.getBean(AxisSBUPIScheduleImpl.class);
		task.doScheduledTask();
	}
	@Scheduled(cron = "${axis.sb.cron.expression}")
	public void runAxisSvgTransactionJob() throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		ScheduledTask task = (ScheduledTask) applicationContext.getBean(AxisSBTransactionScheduleImpl.class);
		task.doScheduledTask();
	}
	
	@Scheduled(cron = "${icici.amazon.cc.cron.expression}")
	public void runICICIAmazonCCJob() throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		ScheduledTask task = (ScheduledTask) applicationContext.getBean(ICICIAmazonCCSchedulerImpl.class);
		task.doScheduledTask();
	}
	

}
