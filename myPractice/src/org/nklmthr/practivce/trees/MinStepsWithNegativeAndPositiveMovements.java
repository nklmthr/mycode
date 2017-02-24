package org.nklmthr.practivce.trees;

public class MinStepsWithNegativeAndPositiveMovements {

	public static void main(String[] args) {
		steps(0,0,4);
	}
	
	static int steps(int source, int step, int dest) {
		System.out.println("source=" + source + " step=" + step + " dest=" + dest);
		if (Math.abs(source) > dest) {
			return Integer.MAX_VALUE;
		}
		if (source == dest) {
			
			return step;
		}
		int pos = steps(source + step + 1, step + 1, dest);

		// if we go on negative side
		int neg = steps(source - step - 1, step + 1, dest);

		// minimum of both cases
		return Math.min(pos, neg);

	}
}
