package com.nklmthr.srishti;

public class PassingValues {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 10;
		String s = "SRISHTI";
		String n = "nikhil";
		
		s.toUpperCase();
		n.toUpperCase();
		method1(i);
		System.out.println("i=" + i);//20
		method2(s);
		System.out.println("s=" + s);//srishti

	}

	static void method2(String s) {
		s = s.toLowerCase();
	}

	static int method1(int a) {
		a = a + 10;
		return a;
	}

}
