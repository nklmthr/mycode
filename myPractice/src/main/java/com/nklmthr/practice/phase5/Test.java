package com.nklmthr.practice.phase5;

public class Test {

	public static void main(String[] args) {
		int MAXIMUM_CAPACITY = 1 << 31;
		System.out.println(Integer.MAX_VALUE);
		int i = Integer.MAX_VALUE - 1;
		int count = 0;
		while (i > 0) {
			i = i / 2;
			System.out.println(i);
			count++;
		}
		System.out.println(count);
	}

}
