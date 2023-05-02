package com.nklmthr.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;

public class Main {
	public static void main(String[] args) {
		int aa = 3, bb = 2;
		AddNumber n = (a, b) -> a + b;
		System.out.println(n.add(aa, bb));
		List<String> l = new ArrayList<>();
		l.add("nikhil");
		l.add("Srishti");
		l.stream().forEach(a -> System.out.println(a));;
		Stack s;
	}
}
