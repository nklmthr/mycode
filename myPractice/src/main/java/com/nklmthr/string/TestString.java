package com.nklmthr.string;

public class TestString {

	public static void main(String[] args) {

		String s1 = "abcd";
		String s2 = "abcd";
		String s3 = "defg";
		String s4 = "pqrt";

		String s11 = new String("abcd");
		String s12 = new String("abcd");
		String s13 = new String("defg");
		String s14 = new String("pqrt");

		System.out.println("s1 == s2:" + (s1 == s2));// true
		System.out.println("s11 == s12" + (s11 == s12));// true

		System.out.println("s1 == s11:" + (s1 == s11));// true
		System.out.println("s2 == s12:" + (s2 == s12));// true
		System.out.println("s3 == s13:" + (s3 == s13));// true
		System.out.println("s4 == s14:" + (s4 == s14));// true

		System.out.println("s1 equals s11:" + s1.equals(s11));// true
		System.out.println("s2 equals s12:" + s2.equals(s12));// true
		System.out.println("s3 equals s13:" + s3.equals(s13));// true
		System.out.println("s4 equals s14:" + s4.equals(s14));// true

	}

}
