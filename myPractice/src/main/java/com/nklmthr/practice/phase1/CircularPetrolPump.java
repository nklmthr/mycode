package com.nklmthr.practice.phase1;

import java.util.ArrayList;

public class CircularPetrolPump {

	public static void main(String[] args) {
		int petrolpump[][] = { { 1, 5 }, { 10, 3 }, { 3, 4 } };
		CircularArrayList<int[]> list = new CircularArrayList<int[]>();
		list.add(petrolpump[0]);
		list.add(petrolpump[1]);
		list.add(petrolpump[2]);

		System.out.println(findTrip(list));

	}

	private static int findTrip(CircularArrayList<int[]> list) {
		int result = -1;
		for (int i = 0; i < list.size(); i++) {
			int fuel = 0, distance = 0;
			int baseIndex = (i == list.size() - 1 ? 0 : i == 0 ? list.size() : i - 1);
			result = findTrip(list, baseIndex, i, fuel, distance);
			if (result > -1)
				return result;
		}
		return result;
	}

	private static int findTrip(CircularArrayList<int[]> list, int baseIndex, int i, int fuel, int distance) {

		int[] current = list.get(i);
		int currFuel = current[0];
		int currDistance = current[1];
		fuel = fuel + currFuel;
		distance = currDistance + distance;
		if (fuel > distance) {
			int index = (i == list.size() - 1 ? 0 : i == 0 ? list.size() : i - 1);
			if (index == baseIndex) {
				return i;
			} else {
				return findTrip(list, baseIndex, i + 1, fuel, distance);
			}
		}
		return -1;

	}

}

class CircularArrayList<T> extends ArrayList<T> {
	@Override
	public T get(int index) {
		if (index < size()) {
			return super.get(index);
		} else {
			index = 0;
			return super.get(index++);
		}
	}
}
