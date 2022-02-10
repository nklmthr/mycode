package com.nklmthr.practice.phase1;

public class XORNonRepeating {

	public static void main(String[] args) {

		int arr[] = new int[] { 8, 8, 2, 4, 7, 9, 2, 4, 6, 6 };
		int sum = 0;
		System.out.println(Integer.toBinaryString(7));
		for (int i = 0; i < arr.length; i++) {
			System.out.println("a[i]=" + arr[i] + "," + Integer.toBinaryString(arr[i]));
			System.out.println("Before sum=" + Integer.toBinaryString(sum));
			sum = (sum ^ arr[i]);
			System.out.println("After sum=" + Integer.toBinaryString(sum));
		}
		System.out.println("sum=" + sum + ",bin=" + Integer.toBinaryString(sum) + ",-sum=" + (-sum) + ",bin="
				+ Integer.toBinaryString(-sum));
		sum = (sum & -sum);
		System.out.println("After Operation sum=" + Integer.toBinaryString(sum));
		int sum1 = 0;
		int sum2 = 0;
		// traversing the array again
		for (int i = 0; i < arr.length; i++) {

			// Bitwise & the arr[i] with the sum
			// Two possibilities either result == 0
			// or result > 0
			if ((arr[i] & sum) > 0) {

				// if result > 0 then arr[i] xored
				// with the sum1
				sum1 = (sum1 ^ arr[i]);
			} else {
				// if result == 0 then arr[i]
				// xored with sum2
				sum2 = (sum2 ^ arr[i]);
			}
		}
		System.out.println("The non-repeating elements are " + sum1 + " and " + sum2);
	}

}
