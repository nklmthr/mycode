package com.nklmthr.practice.phase2;

public class Palindrome {

	public static void main(String[] args) {
		String str = "1a2";
		boolean check = checkPalindromeIgnoreCaseAlphanumericOnly(str);
		System.out.println(check);
	}

	private static boolean checkPalindromeIgnoreCaseAlphanumericOnly(String a) {
		char[] chars = a.toCharArray();
		int i = 0, j = chars.length - 1;
		while (i < j) {
			if (!Character.isLetterOrDigit(chars[i])) {
				i++;
			} else if (!Character.isLetterOrDigit(chars[j])) {
				j--;
			} else {
				if (Character.toLowerCase(chars[i]) == Character.toLowerCase(chars[j])) {
					i++;
					j--;
				} else {
					return false;
				}
			}
		}
		return true;
	}

}
