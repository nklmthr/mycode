package com.nklmthr.drone;

import java.util.ArrayList;
import java.util.List;

public class Drone {
	static int calcDroneMinEnergy(int[][] route) {
		int result = 0;
		int flightFuel = 0;
		int intialFuel = 0;
		System.out.println(route.length);
		for (int i = 0; i < route.length - 1; i++) {
			int[] source = route[i];
			int[] destination = route[i + 1];
			flightFuel = destination[2] - source[2];

			intialFuel += flightFuel;
			if (intialFuel > 0 && intialFuel > result) {
				result = intialFuel;
			}
			System.out.println(
					"i=" + i + ",flightFuel=" + flightFuel + ",intialFuel=" + intialFuel + ",result=" + result);
		}
		return result;
	}

	public static List<Integer> reverseArray(List<Integer> a) {
		for (int i = 0; i < a.size() / 2; i++) {
			Integer temp = a.get(i);
			a.set(i, a.get(a.size() - (1 + i)));
			a.set(a.size() - (1 + i), temp);

		}
		return a;

	}

	public static void main(String[] args) {
		int[][] route = // { { 0, 2, 10 }, { 3, 5, 0 }, { 9, 20, 6 }, { 10, 12, 15 }, { 10, 10, 8 } };

				{ { 0, 2, 2 }, { 3, 5, 38 }, { 9, 20, 6 }, { 10, 12, 15 }, { 10, 10, 8 } };
		// int result = calcDroneMinEnergy(route);
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(4);
		a.add(3);
		a.add(2);
		System.out.println(reverseArray(a));
	}

}
