package org.nklmthr.practivce.trees;

import java.util.Scanner;

public class SimpleArraySum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] input = new String[in.nextInt()];
		in.nextLine(); // consuming the <enter> from input above
		for (int i = 0; i < input.length; i++) {
			input[i] = in.next();
		}
		int[] arr = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		System.out.println(sum(arr));
		int i =Integer.MAX_VALUE;
		int j= Integer.MIN_VALUE;
	}

	private static int sum(int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
}
