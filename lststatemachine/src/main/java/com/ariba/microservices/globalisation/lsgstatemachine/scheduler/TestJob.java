package com.ariba.microservices.globalisation.lsgstatemachine.scheduler;

import java.util.Map;

import org.quartz.JobExecutionContext;

public class TestJob<K, V> extends LSGJob<K, V> {

	@Override
	protected void doWork(JobExecutionContext context, Map<K, V> params) {
		params.keySet().forEach(s -> System.out.println(s));
		System.out.println(context.getMergedJobDataMap().get("name"));
		
	}

}
