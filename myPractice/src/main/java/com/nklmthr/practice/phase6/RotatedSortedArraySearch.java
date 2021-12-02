package com.nklmthr.practice.phase6;

import java.util.Arrays;

import org.apache.commons.io.IOUtils;

public class RotatedSortedArraySearch {

	public static void main(String[] args) {
		int[] nums = new int[] { 3, 3, 3, 1 };
		// { 3, 1, 3 };
		// { 2, 2, 2, 2, 2, 2, 9, 2, 2 };
		// { 1, 3, 5 };
		int peak = findPivot(nums);
		// int target = 1;
		System.out.println("Peak =" + peak);
		if (peak == nums.length - 1) {
			System.out.println("Rotated peak [0]= " + nums[0]);
		} else {
			System.out.println("Peak = " + nums[peak + 1]);
		}
//		int firstTry = binarySearch(nums, 0, peak);
//		if (firstTry > -1) {
//			System.out.println(firstTry);
//		} else {
//			int secondTry = binarySearch(nums, peak + 1, nums.length - 1);
//			System.out.println(secondTry);
//		}

	}

	public static int findPivot(int nums[]) {
		int start = 0, end = nums.length - 1;
		while (start < end) {
			int mid = (start + end) / 2;
			System.out.println(Arrays.toString(Arrays.copyOfRange(nums, start, end + 1)) + ", nums[" + start + "]="
					+ nums[start] + ",nums[" + mid + "]=" + nums[mid] + ",nums[" + end + "]=" + nums[end]);

			if (nums[mid] == nums[start] && nums[mid] == nums[end]) {
				if (nums[start] > nums[start + 1]) {
					return start;
				}
				start++;
				if (nums[end] < nums[end - 1]) {
					return end - 1;
				}
				end--;
			} else {
				if ((mid + 1) == (nums.length - 1) && nums[mid] < nums[mid + 1] && nums[start] < nums[mid]) {
					return mid + 1;
				} else if (nums[mid] > nums[mid + 1]) {
					return mid;
				} else if (nums[start] < nums[mid]) {
					start = mid;
				} else {
					end = mid;
				}
			}
		}
		return -1;
	}

	public static int binarySearch(int nums[], int target, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			if (nums[start] > target || nums[end] < target) {
				return -1;
			} else if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}
}

/*
 * [1,3,5] [3,1,3]
 */