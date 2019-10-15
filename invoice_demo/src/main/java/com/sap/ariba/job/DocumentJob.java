package com.sap.ariba.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.sap.ariba.service.DocumentService;

@Component
public class DocumentJob extends QuartzJobBean {

	@Autowired
	DocumentService service;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		service.processOneDocument();
	}


}
