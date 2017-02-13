package org.nklmthr.practivce.trees;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Scanner;

public class ModifiedFibonacci {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in).useDelimiter("\\s");
		String first = sc.next();
		String second = sc.next();
		int n = Integer.parseInt(sc.next());
		String third = "";
		int count = 2;
		while (count < n) {
			long start = System.currentTimeMillis();
			third = sum(first, allNewSquare(bytify(second)));
			long end = System.currentTimeMillis();
			System.out.println("n=" + count + " timeTaken=" + ((end - start)) + "ms first=" + first + " second="
					+ second + " third=" + third);
			first = second;
			second = stringify(third);
			count++;
		}
		System.out.println(third);
		sc.close();
	}

	private static String stringify(String s) {
		System.out.println(Character.toString((char) Integer.parseInt(s, 2)));
		return Character.toString((char) (Integer.parseInt(s, 2) + 48));
		// return String.valueOf(c);
	}

	private static String bytify(String s) {
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b - 48;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		return binary.toString();
	}

	private static String allNewSquare(String bytes) {
		// Base case
		System.out.println("bytes = " + bytes);
		if (bytes.toString().equals("00000000"))
			return "00000000";

		// Get floor(n/2) using right shift
		String x = "0";
		for (int i = 0; i < bytes.length() - 1; i++) {
			x += bytes.charAt(i);
		}
		if (bytes.charAt(bytes.length() - 1) != '0') {
			String t1 = allNewSquare(x);
			String t2 = leftShift(t1, 2);
			String res1 = bitwiseAdd(t2, leftShift(x, 2));
			String res2 = bitwiseAdd(res1, "00000001");
			return res2;
		} else {
			return leftShift(allNewSquare(x), 2);
		}
	}

	private static String bitwiseAdd(String s1, String s2) {
		String x = "";
		boolean carry = false;
		for (int i = 7; i >= 0; i--) {
			if (s1.charAt(i) == '1' && s2.charAt(i) == '1') {
				x += carry ? '1' : '0';
				carry = true;
			} else if ((s1.charAt(i) == '1' && s2.charAt(i) == '0') || (s1.charAt(i) == '0' && s2.charAt(i) == '1')) {
				x += carry ? '0' : '1';
				carry = true;
			} else if (s1.charAt(i) == '1' && s2.charAt(i) == '0') {
				x += carry ? '1' : '0';
				carry = false;
			} else {
				x += carry ? '1' : '0';
				carry = false;
			}
		}
		return x;
	}

	private static String leftShift(String bits, int num) {
		String x = bits.substring(num - 1, bits.length() - 1);
		for (int i = 0; i < num; i++) {
			x += "0";
		}
		return x;
	}

	private static int allNewSquare(int n) {
		// Base case

		if (n == 0)
			return 0;

		// Handle negative number
		if (n < 0)
			n = -n;

		// Get floor(n/2) using right shift
		int x = n >> 1;

		// If n is odd
		if ((n & 1) != 0)
			return ((allNewSquare(x) << 2) + (x << 2) + 1);
		else // If n is even
			return (allNewSquare(x) << 2);
	}

	private static String newSquare(String s) {
		String count = "0";
		String result = "0";
		while (!s.equals(count)) {
			result = sum(result, s);
			count = sum(count, "1");
		}
		return result;
	}

	private static String square(String s) {
		int length = s.length();
		StringBuilder finalResult = new StringBuilder();
		for (int i = length - 1; i >= 0; i--) {
			int result = 0;
			int carry = 0;
			char c1 = s.charAt(i);
			StringBuilder sb = new StringBuilder("");
			for (int j = length - 1; j >= 0; j--) {
				char c2 = s.charAt(j);
				result = (c1 - 48) * (c2 - 48) + carry;
				carry = result / 10;
				sb.append(result >= 10 ? result % 10 : result);
			}
			if (carry > 0) {
				sb.append(carry);
			}

			String oneNumResult = sb.reverse().toString();
			for (int k = 0; k < length - i - 1; k++) {
				oneNumResult += "0";
			}
			String previous = finalResult.toString();
			finalResult.setLength(0);
			finalResult.append(sum(previous, oneNumResult));
		}
		return finalResult.toString();

	}

	private static String sum(String s1, String s2) {
		StringBuilder result = new StringBuilder();
		int carry = 0;
		int s1len = s1.length(), s2Len = s2.length();
		while (s1len > 0 || s2Len > 0) {
			char c1 = '0', c2 = '0';
			if (s1len > 0) {
				c1 = s1.charAt(s1len - 1);
			}
			if (s2Len > 0) {
				c2 = s2.charAt(s2Len - 1);
			}

			int sum = c1 + c2 + carry - 96;
			carry = sum / 10;
			result.append(sum >= 10 ? sum % 10 : sum);
			s1len--;
			s2Len--;
		}
		result.append(carry > 0 ? carry : "");
		return result.reverse().toString();
	}

}