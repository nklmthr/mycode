package com.nklmthr.practice7;

public interface Vehicle {
	default void moveForward() {
		System.out.println("Vehicle moveForward");
	}
	void moveBackward();
}
