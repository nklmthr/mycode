package com.nklmthr.practice.phase1;

import com.itextpdf.text.log.SysoCounter;

public class Fibonnacci {
	private static void fibonacci(int i, int first, int second) {
		if (i > 0) {
			System.out.print((100-i)+":"+first + ", ");
			int third = first + second;
			first = second;
			second = third;
			fibonacci(i - 1, first, second);
		}

	}

	public static void main(String[] args) {
		fibonacci(100, 0, 1);
		//printHelloWorld("Hello World");
	}

	public static void printHelloWorld(String str) {
		System.out.println(str);
	}

}
