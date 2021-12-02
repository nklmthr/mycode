package com.atlassian.election;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Election -
 * 
 * 
 * /** For a list of votes, return an ordered set of candidate in descending
 * order of their votes.
 *
 * Each person can vote for 3 candidates. order of votes matter. 1 - 3 points, 2
 * - 2 points, 3 - 1 point.
 * 
 * Vote - candidate and order included.
 * 
 * List<String> findWinner(List<Vote> votes)
 */

class Vote {
	private String candidateName;
	private int point;

	public Vote() {
		super();
	}

	public Vote(String candidateName, int point) {
		super();
		this.candidateName = candidateName;
		this.point = point;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vote [candidateName=").append(candidateName).append(", point=").append(point).append("]");
		return builder.toString();
	}

}

class Node {

}

public class Election {
	private static final Logger logger = Logger.getLogger(Election.class);

	public static void main(String[] args) {
		logger.info(args);
		List<Vote> votes = new ArrayList<Vote>();
		votes.add(new Vote("B", 3));
		votes.add(new Vote("A", 2));
		votes.add(new Vote("B", 3));
		votes.add(new Vote("C", 2));
		votes.add(new Vote("B", 3));
		votes.add(new Vote("C", 2));
		votes.add(new Vote("A", 1));
		List<String> results = findWinner(votes);
		System.out.println(results);

	}

	private static List<String> findWinner(List<Vote> votes) {

		Map<String, Integer> results = new HashMap<String, Integer>();
		for (Vote v : votes) {
			if (results.containsKey(v.getCandidateName())) {
				results.put(v.getCandidateName(), results.get(v.getCandidateName()) + v.getPoint());
			} else {
				results.put(v.getCandidateName(), v.getPoint());
			}
		}

		Set<Entry<String, Integer>> electionResults = results.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>();

		for (Entry<String, Integer> entry : electionResults) {
			list.add(entry);
		}

		Collections.sort(list, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (o1.getValue() > o2.getValue()) {
					return -1;
				} else {
					if (o1.getValue() < o2.getValue()) {
						return 1;
					} else
						return 0;
				}
			}

		});

		List<String> finalResults = new ArrayList<String>();
		for (Entry<String, Integer> entry : list) {
			finalResults.add(entry.getKey());
		}
		return finalResults;
	}

}
