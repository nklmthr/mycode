package com.nklmthr.rate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiter {
	public static Map<String, HitCounter> clientMap = new HashMap<String, HitCounter>();

	public static boolean isAllowed(String clientId) {
		long currTime = System.currentTimeMillis();
		if (!clientMap.containsKey(clientId)) {
			HitCounter hitCounter = new HitCounter();
			hitCounter.hit(currTime);
			clientMap.put(clientId, hitCounter);
			return true;
		} else {
			HitCounter hitCounter = clientMap.get(clientId);
			return hitCounter.hit(currTime);
			
		}
	}
}

class HitCounter {
	public Queue<Long> queue;
	public int REQUEST_LIMIT = 100;
	public Long TIME_LIMIT = 1000L;

	public HitCounter() {
		queue = new LinkedList<>();
	}

	public boolean hit(long timestamp) {
		while (!queue.isEmpty() && queue.peek() - timestamp > TIME_LIMIT) {
			queue.poll();
		}
		if (queue.size() < REQUEST_LIMIT) {
			queue.add(timestamp);
			return true;
		}
		return false;
	}
}
