package com.nklmthe.practice.dp;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeetCode451 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String frequencySort(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (Character c : s.toCharArray()) {
			map.put(c, Integer.valueOf(map.getOrDefault(c, 0) + 1));
		}
		HashMap<Character, Integer> hm = sort(map);
		StringBuilder sb = new StringBuilder();
		for (char m : hm.keySet()) {
			int time = hm.get(m);
			while (time-- > 0) {
				sb.append(m);
			}
		}
		return sb.toString();
	}

	private HashMap<Character, Integer> sort(Map<Character, Integer> map) {
		List<Map.Entry<Character, Integer>> entries = new LinkedList<>(map.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<Character, Integer>>() {
			public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		HashMap<Character, Integer> temp = new LinkedHashMap<>();
		for (Map.Entry<Character, Integer> l : entries) {
			temp.put(l.getKey(), l.getValue());
		}
		return temp;
	}

}
