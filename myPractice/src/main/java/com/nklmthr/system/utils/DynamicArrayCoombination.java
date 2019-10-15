package com.nklmthr.system.utils;

public class DynamicArrayCoombination {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[] { 3, 5, 8 };
		generateCombination(arr);
	}

	private static void generateCombination(int[] arr) {
		makeAtt(0, 3, 1, 3, new double[0]);

	}

	private static void makeAtt(double min, double max, double sz, int depth, double[] vals) {
		// Guard against bad depth
		if (depth < 0)
			return;
		if (depth == 0) {
			System.out.print("{");
			for (double d : vals) {
				System.out.print(d + " ");
			}
			System.out.print("}\n\n");
		} else {
			for (double i = min; i < max; i += sz) {
				double[] newVals = new double[vals.length + 1];
				for (int z = 0; z < vals.length; z++)
					newVals[z] = vals[z];
				newVals[vals.length] = i;
				makeAtt(min, max, sz, depth - 1, newVals);
			}
		}
	}
}
