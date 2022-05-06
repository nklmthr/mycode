package com.nklmthr.practice.phase6;

import java.util.Arrays;

public class ArraySubsequenceCheck {

	public static void main(String[] args) {
		int A[] = new int[] { 1, 2, 3, 4, 5 };
		int B = 5;
		int[] result = solve(A, B);
		System.out.println(Arrays.toString(result));
	}

	public static int[] solve(int[] A, int B) {
		int left = 0;
		int right = 0;
		int sum = 0;
		boolean flag = false;
		int[] ans = new int[2];
		int[] answer = new int[1];
		answer[0] = -1;
		for (int i = 0; i < A.length; i++) {
			System.out.println("Start: i=" + i + ",sum=" + sum + ", left=" + left + "ans=" + Arrays.toString(A));
			sum += A[i];
			if (sum > B) {
				while (sum > B) {
					sum -= A[left];
					left++;
				}
			}
			if (sum == B) {
				flag = true;
				ans[0] = A[left];
				ans[1] = A[i];
				break;
			}
			System.out.println("End: i=" + i + ",sum=" + sum + ", left=" + left + "ans=" + Arrays.toString(A));
		}

		if (flag == true) {
			return ans;
		} else
			return answer;
	}
}
