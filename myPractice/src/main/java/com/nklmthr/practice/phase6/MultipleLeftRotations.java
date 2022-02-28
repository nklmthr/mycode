package com.nklmthr.practice.phase6;

import java.util.Arrays;

public class MultipleLeftRotations {

	public static void main(String[] args) {
		int[] A = new int[] { 1,2,3,4,5};
		int[] B = new int[] { 2,3 };
		int[][] result = solve(A, B);
		for (int i = 0; i < result.length; i++) {
			System.out.print("[");
			for (int j = 0; j < result[0].length; j++) {
				System.out.print(result[i][j] + ",");
			}
			System.out.println("]");
		}
	}

	private static int[][] solve1(int[] a, int[] b) {
		int[][] result = new int[b.length][a.length];
		for (int i = 0; i < b.length; i++) {
			int[] currArr = a.clone();
			reverse1(currArr, b[i]);
			result[i] = currArr;
		}
		return result;
	}

	private static void reverse1(int[] a, int num) {
		for (int i = 0; i < num; i++) {
			leftRotatebyOne(a);
		}
	}

	private static void leftRotatebyOne(int arr[]) {
		int temp;
		temp = arr[0];
		for (int i = 0; i < arr.length - 1; i++) {
			arr[i] = arr[i + 1];
		}
		arr[arr.length - 1] = temp;
	}

	public static int[][] solve(int[] A, int[] B) {
		int[][] mat = new int[B.length][A.length];
		int b = 0;
		int j = 0;
		int[] currArr = A;
		for (int i = 0; i < B.length; i++) {
			b = B[i] % A.length;
			System.out.println("1i=" + i + ",b=" + b + ",j=" + j + ",currArray=" + Arrays.toString(currArr));
			reverse(currArr, 0, currArr.length - 1);
			System.out.println("2i=" + i + ",b=" + b + ",j=" + j + ",currArray=" + Arrays.toString(currArr));
			reverse(currArr, 0, b - 1);
			System.out.println("3i=" + i + ",b=" + b + ",j=" + j + ",currArray=" + Arrays.toString(currArr));
			reverse(currArr, b, currArr.length - 1);
			System.out.println("4i=" + i + ",b=" + b + ",j=" + j + ",currArray=" + Arrays.toString(currArr));
			mat[j] = currArr.clone();
			System.out.println("5i=" + i + ",b=" + b + ",j=" + j + ",currArray=" + Arrays.toString(currArr));
			j++;

		}
		return mat;
	}

	public static void reverse(int[] A, int start, int end) {
		while (start < end) {
			int temp = 0;
			temp = A[start];
			A[start] = A[end];
			A[end] = temp;
			start++;
			end--;
		}
	}
}
