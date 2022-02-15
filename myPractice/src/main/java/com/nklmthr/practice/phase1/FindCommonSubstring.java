package com.nklmthr.practice.phase1;

public class FindCommonSubstring {

	public static void main(String[] args) {
		String[] arr = new String[] { "abcdefghji", "abhijklmno", "abpmnojddj", "ahhdhhdhhdhdhd" };
		String prefix = arr[0];
		for (int i = 1; i < arr.length; i++) {
			System.out.println("\n\narr[i].indexOf(prefix)="+arr[i].indexOf(prefix));
			while (arr[i].indexOf(prefix) < 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				System.out.println("arr[i]=" + arr[i] + ", prefix=" + prefix);
			}
		}
		System.out.println("prefix=" + prefix);
	}

}
