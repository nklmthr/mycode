package org.nklmthr.practivce.trees;

import java.util.ArrayList;

public class ArrayOutput1 {

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();
		// : [[1, 2, 3, 4], [5, 6, 7, 8],{
		// 9, 10, 11, 12}};
		ArrayList<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		A.add(a);

		ArrayList<Integer> b = new ArrayList<>();
		b.add(5);
		b.add(6);
		b.add(7);
		b.add(8);
		A.add(b);

		ArrayList<Integer> c = new ArrayList<>();
		c.add(9);
		c.add(10);
		c.add(11);
		c.add(12);
		A.add(c);

		ArrayList<ArrayList<Integer>> B = performOps(A);
		for (int i = 0; i < B.size(); i++) {
			for (int j = 0; j < B.get(i).size(); j++) {
				System.out.print(B.get(i).get(j) + " ");
			}
		}
	}

	static ArrayList<ArrayList<Integer>> performOps(ArrayList<ArrayList<Integer>> A) {
		ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < A.size(); i++) {
			B.add(new ArrayList<Integer>());

			for (int j = 0; j < A.get(i).size(); j++) {
				B.get(i).add(0);
			}

			for (int j = 0; j < A.get(i).size(); j++) {
				B.get(i).set(A.get(i).size() - 1 - j, A.get(i).get(j));
			}
		}
		return B;
	}

}
