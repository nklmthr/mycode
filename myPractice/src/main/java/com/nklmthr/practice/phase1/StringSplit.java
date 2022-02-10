package com.nklmthr.practice.phase1;

public class StringSplit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSplit();
	}

	private static void testSplit() {
		String s1 = "I am good boy. I am very happy";
		String s2 = "10, 20, 30, 40, 50";
		String s3 = "2,4,9,8,6,7";

		String[] arr = s1.split("\\.");
		printStringArray(arr);

		arr = s1.split(" ");
		printStringArray(arr);

		arr = s2.split(",");
		printStringArray(arr);
		int[] a;
		double c;
		Double b[];
		Long d;
		Long w;

		Integer[] intArr = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			intArr[i] = Integer.parseInt(arr[i].trim());
		}
		
		for (int i = 0; i < intArr.length; i++) {
			System.out.println("[" + intArr[i] + "],");
		}
		arr = s3.split(",");
		printStringArray(arr);

	}

	private static void printStringArray(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println("[" + arr[i] + "],");
		}

	}

}
