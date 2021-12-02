package com.nklmthr.practice.phase6;

import com.itextpdf.text.log.SysoCounter;

public class Integers {

	public static void main(String[] args) {
		System.out.println(isPalindrome(12321));

	}

	public static boolean isPalindrome(int x) {
		boolean check = false;
		char[] chars = Integer.toString(x).toCharArray();
		System.out.println(chars);
		for (int i = 0; i < (chars.length / 2); i++) {
			System.out.println(
					"i=" + i + ",chars[i]=" + chars[i] + ",chars[chars.length - i -1]=" + chars[chars.length - i - 1]);
			if (chars[i] != chars[chars.length - i - 1]) {
				System.out.println("Entering break here...");
				check = false;
				break;
			}
			System.out.println("should set true here");
			check = true;
		}
		if (check) {
			return true;
		} else {
			return false;
		}
	}
}
