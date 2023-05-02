package com.nklmthr.practice7;

public interface CarInterface {
	String name = "Srishti";
	
	void moveBackward();
	void turnLeft();
	void turnRight();

	
	static void runFast() {
		System.out.println("Interface CarInterface runFast");
	}
	static void runSlow() {
		System.out.println("Interface CarInterface runFast");
	}
	default void ShowSpeed() {
		System.out.println("Interface default showSpeed");
	}
	default void ShowTemp() {
		System.out.println("Interface default showSpeed");
	}
	default void moveForward() {
		System.out.println("CarInterface moveForward");
	}

	
}
