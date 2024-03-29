package com.practice.sap.work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCheckString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyObject m1 = new MyObject("Nikhil1", 100);
		MyObject m2 = new MyObject("Nikhil2", 100);
		MyObject m3 = new MyObject("Nikhil3", 100);
		MyObject m4 = new MyObject("Nikhil4", 100);
		List<MyObject> list = new ArrayList<MyObject>();
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
//		String[] s1 = { "Nikhil3" };
//		List search = Arrays.asList(s1);
//		System.out.println(list.stream().map(s -> Formatter.format(s.getName())).collect(Collectors.joining(",")));
//		list = list.stream().filter(s -> !search.contains(s.getName())).collect(Collectors.toList());
//		System.out.println(Arrays.toString(list.toArray()));
		// System.out.println(Arrays.toString(res.toArray()));

		Map<String, List<String>> ret = new HashMap();
		List<String> s1 = Arrays.asList("s11", "s12", "s13");
		ret.put("s1", s1);
		List<String> s2 = Arrays.asList("s21", "s22", "s23");
		ret.put("s2", s2);

		System.out.println();
		ret.entrySet().stream().forEach(s -> System.out.println(s.getKey()+":"+s.getValue().size()));
				
	}

}

class Formatter {
	public static String format(String s) {
		return "*" + s + "*";
	}
}

class MyObject {
	String name;
	int marks;

	@Override
	public String toString() {
		return "MyObject [name=" + name + ", marks=" + marks + "]";
	}

	public MyObject(String name, int marks) {
		this.name = name;
		this.marks = marks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

}