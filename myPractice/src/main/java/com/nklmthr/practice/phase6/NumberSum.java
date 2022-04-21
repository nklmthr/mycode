package com.nklmthr.practice.phase6;

public class NumberSum {

	public static void main(String[] args) {
		int ans = checkNumberSum(12345);
		System.out.println("Answer:"+ans);

	}

	private static int checkNumberSum(int num) {
		System.out.println("numbers=" + num);
		num = sumDigits(num);
		if (num > 9) {
			checkNumberSum(num);
		}
		return num;
	}

	private static int sumDigits(int num) {
		
		int sum = 0;
		if (num > 9) {
			sum += (num % 10) + sumDigits(num / 10);
		} else {
			sum = num;
		}
		System.out.println("sum=" + sum);
		return sum;
	}

}
