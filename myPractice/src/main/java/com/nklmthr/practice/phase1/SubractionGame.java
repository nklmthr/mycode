package com.nklmthr.practice.phase1;


import java.util.ArrayList;
import java.util.List;

public class SubractionGame {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(new Integer(2));
		list.add(new Integer(7));
		subractionGame(list, 0, 0);
		System.out.println(list);

	}

	private static void subractionGame(List<Integer> list, int idx1, int idx2) {
		//System.out.println(list);
		if (idx1 > idx2 || idx2 >= list.size() - 1)
			return;
		idx2++;
		if (!list.contains(new Integer(Math.abs(list.get(idx1) - list.get(idx2))))) {
			list.add(Math.abs(list.get(idx1) - list.get(idx2)));

		}
		
		subractionGame(list, idx1, idx2);
		idx1++;
		subractionGame(list, idx1, 1);
	}
}
