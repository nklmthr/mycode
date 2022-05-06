package com.srishti.parkinglot;

import java.util.Date;

public class Slot {
	private int num;
	private boolean occupied;
	private long lastCarInTime;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public long getLastCarInTime() {
		return lastCarInTime;
	}

	public void setLastCarInTime(long lastCarInTime) {
		this.lastCarInTime = lastCarInTime;
	}


}
