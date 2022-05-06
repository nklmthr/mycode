package com.srishti.parkinglot;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		ParkingLot parkingLot = new ParkingLot();
		
		ParkingLotManager mgr = new ParkingLotManager(parkingLot);
		
		Slot slot = mgr.getFreeSlot();		
		mgr.assignSlot(slot, 6036);		
		Double price = mgr.calculateTicketPrice(slot);
		System.out.println(price);
		
		
		
	}

}
