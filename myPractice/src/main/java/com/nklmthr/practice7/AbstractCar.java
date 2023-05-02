package com.nklmthr.practice7;

public abstract class AbstractCar implements CarInterface, Vehicle{

	public AbstractCar(){
		
	}
	
	@Override
	abstract public void moveForward() ;

	@Override
	abstract public void moveBackward() ;

	
	@Override
	abstract public void turnLeft();

	@Override
	abstract public void turnRight();
	int numOfSeats = 7;
	private void moveSeats() {
		
	}

}
