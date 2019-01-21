package com.nklmthr.practice.phase4;

import java.util.ArrayList;
import java.util.List;

public class PetStore {
	static List<Store> stores = new ArrayList<>();
	static Inventory inventory = new Inventory();

	public static void main(String[] args) {
		createMasterData();

	}

	private static void createMasterData() {
		for (int i = 0; i < 100; i++) {
			if (i % 5 == 0) {
				stores.add(new Store("Store" + i % 5, "city" + i % 5, "state" + i % 5));
				inventory = null;
			}
		}

	}
}

enum Category {
	BEVERAGE, FOOD
};

class Item {
	String name;
	String category;
	String unitPrice;

}

class Store {

	public Store(String name, String city, String state) {
		this.name = name;
		this.city = city;
		this.state = state;
	}

	String name;
	String city;
	String state;

}

class Inventory {
	Store store;
	List<Item> stocks;
}
