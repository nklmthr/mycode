package com.nklmthr.practice.phase1;

/*
 * http://collabedit.com/q62rc
 */
public class TreePlantation {

	public static void main(String[] args) {
		int a[] = { 0, 0, 0, 0, 0, 0, 1, 0, 0 };
		int count = 4;
		System.out.println(canBePlanted(a, count));

	}

	public static boolean canBePlanted(int a[], int count) {
		int lIndex = 0, sum = 0;

		if (a.length == 0)
			return false;
		if (a.length == 1)
			if (a[0] == 0)
				return true;
			else
				return false;

		for (int i = 0; i < a.length; i++) {
			System.out.println("i=" + i + " lIndex= " + lIndex + " sum=" + sum);
			if (a[i] == 1) {
				// System.out.println(i - 1 + " " + i + " " + (i + 1)+ " "+
				// lIndex);
				lIndex = i;
			} else {
				// System.out.println("i < a.length - 1" + (i < a.length - 1) +
				// " i - 1 != lIndex " + (i - 1 != lIndex)
				// + " a[i] == 0 && a[i + 1]" + (a[i] == 0 && a[i + 1]==0));
				if (((i < a.length - 1) && (i - 1 != lIndex && a[i] == 0 && a[i + 1] == 0))
						|| ((i == a.length - 1) && (i - 1 != lIndex && a[i] == 0))) {
					// System.out.println(i - 1 + " " + i + " " + (i + 1)+ " "+
					// lIndex);
					lIndex = i;
					sum++;
				}
			}
		}
		if (sum >= count)
			return true;

		return false;

	}
}
