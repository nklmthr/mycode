package com.nklmthr.practice.phase6;

public class StringReverseRecursion {

	public static void main(String[] args) {
		String s = "Hello World";
		System.out.println(reverseString(s));

	}

	private static String reverseString(String s) {		
		if (s.isEmpty()) {
			return s;
		} else {			
			return s.charAt(s.length() - 1) + reverseString(s.substring(0, s.length() - 1));
		}

	}

}
