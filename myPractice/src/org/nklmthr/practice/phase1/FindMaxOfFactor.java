package org.nklmthr.practivce.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindMaxOfFactor {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] input = new String[in.nextInt()];
		in.nextLine(); // consuming the <enter> from input above
		for (int i = 0; i < input.length; i++) {
			input[i] = in.next();
		}
		int num = Integer.parseInt(input[0]);
		// System.out.println(num);
		List<Integer> factors = findFactors(num);
		System.out.println(factors);

	}

	private static List<Integer> findFactors(int num) {
		List<Integer> factors = new ArrayList<Integer>();
		while (num % 2 == 0) {
			factors.add(2);
			num /= 2;
		}

		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) {
				factors.add(i);
				num /= i;
			}
		}

		if (num > 2) {
			factors.add(num);
		}
		return factors;
	}

}
