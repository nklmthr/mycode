package com.nklmthr.largestsubarray;

public class LargestSubArray {

	public static void main(String[] args) {
		int[] a = { 2, -3, -4, 1, 2, 1, -5, 3 };
		System.out.println("Maximum contiguous sum is " + maxSubArraySum(a));

	}

	static int maxSubArraySum(int a[]) {
		int size = a.length;
		int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

		for (int i = 0; i < size; i++) {			
			max_ending_here = max_ending_here + a[i];
			System.out.println(
					"Before: i=" + i + ",a[i]=" + a[i] + ",max_so_far=" + max_so_far + ",max_ending_here=" + max_ending_here);
			if (max_so_far < max_ending_here)
				max_so_far = max_ending_here;
			if (max_ending_here < 0)
				max_ending_here = 0;
			System.out.println(
					"After: i=" + i + ",a[i]=" + a[i] + ",max_so_far=" + max_so_far + ",max_ending_here=" + max_ending_here+"\n\n");
		}
		return max_so_far;
	}
}
