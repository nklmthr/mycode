package com.nklmthr.practice.phase5;

/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			n = sc.nextInt();
			System.out.println(Integer.MAX_VALUE);
			System.out.println(n);
			int result = noSum(n);
			while (result > 9) {
				System.out.println(result);
				result = noSum(result);
			}
			System.out.println(result);
		}
	}

	private static int noSum(int n) {
		int sum = 0;
		while (n > 0) {
			int d = n / 10;
			int r = n % 10;
			sum = sum + r;
			System.out.println(sum+"*"+d);
			n = d;
		}
		return sum;
	}
}
