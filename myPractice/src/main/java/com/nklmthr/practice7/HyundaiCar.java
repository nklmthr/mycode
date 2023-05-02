package com.nklmthr.practice7;

public class HyundaiCar extends AbstractCar{
	@Override
	public void moveForward() {
		System.out.println("HyundaiCar moveForward");
		
	}

	@Override
	public void moveBackward() {
		System.out.println("HyundaiCar moveBackward");
		
	}

	@Override
	public void turnLeft() {
		System.out.println("HyundaiCar turnLeft");
		
	}

	@Override
	public void turnRight() {
		System.out.println("HyundaiCar turnRight");
		
	}
}
