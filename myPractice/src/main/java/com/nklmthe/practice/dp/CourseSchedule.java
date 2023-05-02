package com.nklmthe.practice.dp;

import java.util.HashSet;
import java.util.Set;

import com.itextpdf.text.log.SysoCounter;

public class CourseSchedule {

	public static void main(String[] args) {
//		int numCourses = 2;
//		int[][] prerequisites = new int[][] { { 1, 0 } };

//		int numCourses = 4;
//		int[][] prerequisites = new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
//		
		int numCourses = 1;
		int[][] prerequisites = new int[][] { {} };
//		
		int[] result = findOrder(numCourses, prerequisites);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		Set<Integer> visited = new HashSet<Integer>();
		visited.add(0);
		for (int i = 0; i < prerequisites.length; i++) {
			for (int j = 0; j < prerequisites[0].length; j++) {
				visited.add(prerequisites[i][j]);
			}
		}
		int[] result = new int[visited.toArray().length];
		int k = 0;
		for (Integer val : visited) {
			result[k++] = val.intValue();
		}
		return result;
	}

}
