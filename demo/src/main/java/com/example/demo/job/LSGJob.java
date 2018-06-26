package com.example.demo.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class LSGJob extends QuartzJobBean {

	private String jobTestString;
	
	public String getJobTestString() {
		return jobTestString;
	}

	public void setJobTestString(String jobTestString) {
		this.jobTestString = jobTestString;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(String.format("Hello %s!", this.jobTestString));
	}


}
