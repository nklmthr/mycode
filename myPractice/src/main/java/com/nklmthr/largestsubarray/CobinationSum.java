package com.nklmthr.largestsubarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CobinationSum {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 5, 10, 20, 50, 100, 500, 2000 };
		List<List<Integer>> output = combinationSum(nums, 5100);
		for (List<Integer> x : output) {
			for (Integer y : x)
				System.out.print(y + " ");
			System.out.println();
		}

	}

//	private static List<List<Integer>> combinationSum(int[] nums, int target) {
//		List<List<Integer>> results = new ArrayList<List<Integer>>();
//		for (int i = 0; i < nums.length; i++) {
//			int selfMultiplier = target % nums[i];
//			if (selfMultiplier == 0) {
//				List<Integer> result = new ArrayList<Integer>();
//				for (int j = 0; j < target / nums[i]; j++)
//					result.add(nums[i]);
//				results.add(result);
//			}
//		}
//		return results;
//	}

	public static List<List<Integer>> combinationSum(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, target, 0);
		return list;
	}

	private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
		// System.out.println("tempList=" + tempList + ",remain=" + remain + ",start=" +
		// start);
		if (remain < 0)
			return;
		else if (remain == 0)
			list.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < nums.length; i++) {
				tempList.add(nums[i]);
				backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
				tempList.remove(tempList.size() - 1);
			}
		}
	}

}
