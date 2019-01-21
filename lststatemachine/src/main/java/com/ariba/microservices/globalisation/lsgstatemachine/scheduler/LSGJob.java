package com.ariba.microservices.globalisation.lsgstatemachine.scheduler;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public abstract class LSGJob<K, V> extends QuartzJobBean {

	private Map<K, V> params = new HashMap<K, V>();
	
	public void setParams(Map<K, V> params) {
		 this.params = params;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		doWork(context, params);
		context.getMergedJobDataMap();
	}

	protected abstract void doWork(JobExecutionContext context, Map<K, V> params);
}
