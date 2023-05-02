package com.nklmthr.practice7;

public class MarutiCar extends AbstractCar {
	
	@Override
	public void moveForward() {
		System.out.println("MatutiCar moveForward");
		
	}

	@Override
	public void moveBackward() {
		System.out.println("MatutiCar moveBackward");
		
	}

	@Override
	public void turnLeft() {
		System.out.println("MatutiCar turnLeft");
		
	}

	@Override
	public void turnRight() {
		System.out.println("MatutiCar turnRight");
		
	}
	public static void runFast() {
		System.out.println("MarutiCar  runFast");
	}
	public void ShowSpeed() {
		System.out.println("MarutiCar showSpeed");
	}

}
