package org.nklmthr.practivce.trees;

public class Test {

	private static char[][] dict = { { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' }, { 'j', 'k', 'l' },
			{ 'm', 'n', 'o' }, { 'p', 'q', 'r' }, { 's', 't', 'u' }, { 'v', 'w', 'x' }, { 'y' }, { 'z' } };

	public static void main(String[] args) {
		System.out.println('a' ^ 'b');
		printletters(3854, "");

	}

	public static void printletters(int digit, String suffix) {
		int last_digit = digit % 10;
		int rest_digit = digit / 10;
		for (int i = 0; i < dict[last_digit].length; i++) {
			if (rest_digit == 0) {
				System.out.println(dict[last_digit][i] + suffix);
			} else {
				printletters(rest_digit, dict[last_digit][i] + suffix);
			}
		}
	}
}
