package com.nklmthr.practice.phase6;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class HashMapAndSetPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer, String> map1 = new HashMap<Integer, String>();
		Map<Integer, String> map2 = new TreeMap<Integer, String>();
		Map<Integer, String> map3 = new LinkedHashMap<Integer, String>();

		map1.put(new Integer(5), "Five");
		map1.put(new Integer(10), "Ten");
		map1.put(new Integer(1), "One");
		map1.put(new Integer(7), "Seven");

		map2.put(new Integer(5), "Five");
		map2.put(new Integer(10), "Ten");
		map2.put(new Integer(1), "One");
		map2.put(new Integer(7), "Seven");

		map3.put(new Integer(5), "Five");
		map3.put(new Integer(10), "Ten");
		map3.put(new Integer(1), "One");
		map3.put(new Integer(7), "Seven");

		System.out.println("First Map");
		printValues(map1);

		System.out.println("SecondMap");
		printValues(map2);

		System.out.println("Third Map");
		printValues(map3);

	}

	public static void printValues(Map<Integer, String> map) {
		Set<Integer> set = map.keySet();
		Iterator<Integer> itr = set.iterator();
		while (itr.hasNext()) {
			Integer key = itr.next();
			// System.out.println("Key=" + itr.next() + ",value=" + map.get(itr.next()));
			System.out.println("Key=" + key + ",value=" + map.get(key));
		}

	}

	public static void printEntrySet(Map<Integer, String> map) {
		Set<Entry<Integer, String>> entrySet = map.entrySet();
		for (Entry<Integer, String> entry : entrySet) {
			System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());

		}
	}

	public static void printUsingForLoop(Map<Integer, String> map) {
		for (Integer key : map.keySet()) {
			System.out.println("key=" + key + ",value=" + map.get(key));
		}
	}

}
