package com.nklmthr.practice.phase3;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ClickGame {

	public static void main(String[] args) {
		try {
			Worker worker = new Worker();
			while (true) {
				Thread thread = new Thread(worker);
				thread.start();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

interface Game {
	void click(String user);

	void printLeaderBoard(TreeSet<TimeUserClick> instantMap2);

	// Map<Integer, List<String>> leaderBoaerd = getLeaders();
	void printLeaderBoard(Map<Integer, List<String>> leaderBoaerd);

	void printLeaderBoard();
}

class MyGame implements Game {
	Map<String, Integer> userClicks = new HashMap<String, Integer>();
	Map<Integer, List<String>> countMap = new TreeMap<Integer, List<String>>();
	TreeSet<TimeUserClick> instantMap = new TreeSet<TimeUserClick>();
	private long LOOK_BACK_PERIOD_FOR_LEADER_BOARD = 60000;
	private int NUMBER_OF_LEADERS = 5;

	public void click(String user) {
		System.out.println("Click User=" + user);
		int prevCount = 0, newCount = 1;
		if (userClicks.containsKey(user)) {
			prevCount = userClicks.get(user);
			newCount = prevCount + 1;
			userClicks.put(user, newCount);
		} else {
			userClicks.put(user, newCount);
		}
		// System.out.println("Processing prevCount=" + prevCount + ",newCount="
		// + newCount);
		List<String> usersByPrevCount = countMap.get(prevCount);
		if (usersByPrevCount != null && usersByPrevCount.size() > 0 && prevCount > 0) {
			usersByPrevCount.remove(usersByPrevCount.indexOf(user));
		}
		List<String> usersByNewCount = countMap.get(newCount);
		if (usersByNewCount != null) {
			usersByNewCount.add(user);
		} else {
			usersByNewCount = new ArrayList<String>();
			usersByNewCount.add(user);
		}
		countMap.put(newCount, usersByNewCount);

		Map<Integer, List<String>> leaderBoaerd = getLeaders();
		List<String> timeLeaderUsers = new ArrayList<String>();
		for (Integer c : leaderBoaerd.keySet()) {
			timeLeaderUsers.addAll(leaderBoaerd.get(c));
		}
		Instant instant = Instant.now();
		if (instantMap.size() > 0) {
			TimeUserClick tc = instantMap.first();
			Instant lastInstant = tc.getTime();
			if (Duration.between(lastInstant, instant).toMillis() > LOOK_BACK_PERIOD_FOR_LEADER_BOARD) {
				tc = new TimeUserClick(instant, timeLeaderUsers);
				instantMap.add(tc);
			} else {
				tc.setUser(timeLeaderUsers);
			}
		} else {
			TimeUserClick tc = new TimeUserClick(instant, timeLeaderUsers);
			instantMap.add(tc);
		}
		// printLeaderBoard(instantMap);
	}

	public void printLeaderBoard(TreeSet<TimeUserClick> instantMap2) {
		TimeUserClick tc = instantMap2.first();
		LocalDateTime ldt = LocalDateTime.ofInstant(tc.getTime(), ZoneOffset.systemDefault());
		System.out.print(ldt.get(ChronoField.HOUR_OF_DAY));
		System.out.print(":" + ldt.get(ChronoField.MINUTE_OF_HOUR));
		System.out.println(":" + ldt.get(ChronoField.SECOND_OF_MINUTE));
		for(String user: tc.getUser()){
			System.out.print(user+"["+userClicks.get(user)+"],");
		}
		System.out.println();
	}

	private Map<Integer, List<String>> getLeaders() {
		int leaders = NUMBER_OF_LEADERS;
		Map<Integer, List<String>> leaderBoard = new HashMap<Integer, List<String>>();
		Set<Integer> countSet = new TreeSet<Integer>(Collections.reverseOrder());
		countSet.addAll(countMap.keySet());

		for (Integer count : countSet) {
			if (countMap.get(count) != null && countMap.get(count).size() > 0) {
				List<String> users = countMap.get(count);
				for (String user : users) {
					if (leaders > 0) {
						if (leaderBoard.get(count) == null) {
							leaderBoard.put(count, new ArrayList<String>());
						}
						leaderBoard.get(count).add(user);
						leaders--;
					}
				}

			}
		}
		return leaderBoard;
	}

	public void printLeaderBoard(Map<Integer, List<String>> leaderBoaerd) {
		for (Integer count : leaderBoaerd.keySet()) {
			//System.out.print("count = " + count + " -->");
			for (String user : leaderBoaerd.get(count)) {
				System.out.print(user+"["+userClicks.get(user)+"],");
			}
		}
		System.out.println();
		System.out.println("total recorder users =" + userClicks.size() + "\n\n");
	}

	public void printLeaderBoard() {
		printLeaderBoard(instantMap);
		printLeaderBoard(getLeaders());
	}
}

class TimeUserClick implements Comparable<TimeUserClick> {
	private Instant time;
	private List<String> user;

	public TimeUserClick(Instant instant, List<String> timeLeaderUsers) {
		this.time = instant;
		this.user = timeLeaderUsers;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public List<String> getUser() {
		return user;
	}

	public void setUser(List<String> user) {
		this.user = user;
	}

	public int compareTo(TimeUserClick o) {
		if (o.getTime().isAfter(this.getTime())) {
			return 1;
		} else if (o.getTime().isBefore(this.getTime())) {
			return -1;
		}
		return 0;

	}
}

class Worker implements Runnable {
	Game game = new MyGame();

	public void run() {
		Random rand = new Random();
		int player = Math.abs(rand.nextInt()) % 20;
		String valueAsWords = NumberToWordsConverter.convert(player);
		game.click(valueAsWords);
		game.printLeaderBoard();
	}

}
