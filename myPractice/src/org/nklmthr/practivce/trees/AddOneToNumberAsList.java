package org.nklmthr.practivce.trees;

import java.util.ArrayList;

public class AddOneToNumberAsList {

	public static void main(String[] args) {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(0);
		nums.add(2);
		nums.add(3);

		System.out.println(plusOne(nums));

	}

	public static ArrayList<Integer> plusOne(ArrayList<Integer> a) {
		int num = 0;
		boolean isSignificant = false;
		int tenFactor = a.size() - 1;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) == 0 && !isSignificant) {
				tenFactor--;
				continue;
			} else {
				isSignificant = true;
				
				num += Math.pow(10, tenFactor) * a.get(i);
				tenFactor--;
			}
		}
		
		num++;
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (num > 0) {
			int rem = num - (10 * (num / 10));
			num = num / 10;
			result.add(0, rem);
		}

		return result;
	}

}
