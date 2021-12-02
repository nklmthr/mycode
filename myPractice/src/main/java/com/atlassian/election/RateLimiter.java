package com.atlassian.election;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.validation.constraints.AssertTrue;

import org.apache.log4j.Logger;

public class RateLimiter {
	private static final Logger logger = Logger.getLogger(RateLimiter.class);
	private static Map<Integer, HitCounter> customerAccessMap = new ConcurrentHashMap<Integer, HitCounter>();
	public static void main(String[] args) throws Exception {
		RateLimiter rl = new RateLimiter();
		rl.rateLimit(10);
		rl.rateLimit(20);
		Thread.sleep(1000);
		
		// This should be allowed
		if(!rl.rateLimit(10)) {
			throw new Exception("Should be allowed"); 
		}
		if(!rl.rateLimit(20)) {
			throw new Exception("Should be allowed"); 
		}
		// This should be allowed
		if(!rl.rateLimit(20)) {
			throw new Exception("Should be allowed");
		}
		if(rl.rateLimit(10)) {
			throw new Exception("Should be allowed");
		}
		// This should not be allowed
		if (rl.rateLimit(20)) {
			throw new Exception("Should not be allowed");
		}
		if (rl.rateLimit(10)) {
			throw new Exception("Should not be allowed");
		}
	}

	private synchronized boolean rateLimit(int customerId) {
		logger.info("Starting check for customer:" + customerId);
		Integer customer = Integer.valueOf(customerId);		

		if (customerAccessMap.containsKey(customer)) {
			HitCounter hc = customerAccessMap.get(customer);
			boolean check = hc.hit();
			logger.info("Check for customer " + customer + " is:" + check);
			return check;
		} else {
			HitCounter hc = new HitCounter();
			hc.hit();
			customerAccessMap.put(customer, hc);
			return true;
		}
	}
}

class HitCounter {
	Queue<Long> hitQueue;
	private int rateLimit = 2;
	private long interval = 1000L;

	public HitCounter() {
		hitQueue = new ConcurrentLinkedQueue<Long>();
	}

	public synchronized boolean hit() {
		Long currentTime = System.currentTimeMillis();

		while (hitQueue.size() > 0 && (hitQueue.peek() - currentTime) > interval) {
			hitQueue.remove();
		}
		if (hitQueue.size() <= rateLimit) {
			hitQueue.add(currentTime);
			return true;
		} else {
			return false;
		}
	}
}
