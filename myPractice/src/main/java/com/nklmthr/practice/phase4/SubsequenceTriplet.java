package com.nklmthr.practice.phase4;

public class SubsequenceTriplet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[] { 12, 11, 10, 5, 6, 2, 30 };
		findTriplet(arr);
	}

	private static void findTriplet(int[] arr) {
		// TODO Auto-generated method stub
		int max = arr.length - 1;
		int min = 0;

		int smaller[] = new int[arr.length];
		smaller[0] = -1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] <= arr[min]) {
				min = i;
				smaller[i] = -1;
			} else {
				smaller[i] = min;
			}
		}

		int greater[] = new int[arr.length];
		greater[arr.length - 1] = -1;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] >= arr[max]) {
				max = i;
				greater[i] = -1;
			} else {
				greater[i] = max;
			}
		}

		// Now find a number which has both a greater number
		// on right side and smaller number on left side
		for (int i = 0; i < arr.length; i++) {
			if (smaller[i] != -1 && greater[i] != -1) {
				System.out.print(arr[smaller[i]] + " " + arr[i] + " " + arr[greater[i]]);
				return;
			}
		}
		System.out.println("No such triplet found");
		return;
	}

}
