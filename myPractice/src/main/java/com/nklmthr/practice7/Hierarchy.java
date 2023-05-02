package com.nklmthr.practice7;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Hierarchy {

	public static void main(String[] args) {
//		Parent p1 = new Parent();
//		Parent.method2();
//		p1.method1();

//		Child c1 = new Child();
//		Child.method2();
//		c1.method1();

//		
//		Parent p2 = new Child();
//		Parent.method2();
//		p2.method1();
		
//		MarutiCar car = new MarutiCar();
//		car.moveForward();
//		car.moveBackward();
//		car.turnLeft();
//		car.turnRight();
//		car.ShowSpeed();
//		car.runFast();
//		
//		CarInterface car1 = new MarutiCar();
//		car1.moveForward();
//		car1.moveBackward();
//		car1.turnLeft();
//		car1.turnRight();
//		car1.ShowSpeed();
//		CarInterface.runFast();
//		
		CarInterface car2 = new HyundaiCar();
		car2.moveForward();
		car2.moveBackward();
		car2.turnLeft();
		car2.turnRight();
		car2.ShowSpeed();
		CarInterface.runFast();
		List l;
		AbstractList k;
		ArrayList a;
	}
}

class Parent {
	Parent() {
		System.out.println("Constructor Parent");
	}

	static {
		System.out.println("Static Parent");
	}

	void method1() {
		System.out.println("Called Parent method1");
	}

	static void method2() {
		System.out.println("Called Parent static method2");
	}
}

class Child extends Parent {
	static {
		System.out.println("Static Child");
	}

	Child() {
		System.out.println("Constructor Child");
	}

	void method1() {

		System.out.println("Called Child method1");
	}

	static void method2() {
		System.out.println("Called Child static method2");
	}
}