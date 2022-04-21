package com.nklmthr.practice.phase6;

public class TypeConversion {

	public static void main(String[] args) throws Exception {
		int x = 2, n = 2, d = 3;
		calculate(x, n, d);
	}

	private static int calculate(int x, int n, int d) throws Exception {
		int ans = 0;
		if (n == 0) {
			ans = 1;
		}
		if (x == 0) {
			ans = d;
		}
		if (d == 0) {
			throw new Exception();
		}
		if (n % 2 == 0) {
			calculate(x, n/2, d);
		} else {
			calculate(x, ans, d);
		}

		return ans;

	}

}
