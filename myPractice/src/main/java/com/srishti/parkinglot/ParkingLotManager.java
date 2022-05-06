package com.srishti.parkinglot;

import java.util.Date;

public class ParkingLotManager {
	private ParkingLot parkingLot = null;

	public ParkingLotManager(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public Slot getFreeSlot() {
		for (Slot slot : parkingLot.getSlots()) {
			if (!slot.isOccupied()) {
				return slot;
			}
		}
		return null;
	}

	public void assignSlot(Slot slot, int num) {
		slot.setOccupied(true);
		slot.setNum(num);
		slot.setLastCarInTime(System.currentTimeMillis());
	}

	public Double calculateTicketPrice(Slot slot) {
		RateCard rc = new RateCard();
		long outTime = System.currentTimeMillis();
		long stayTime = outTime - slot.getLastCarInTime();
		Double price = (stayTime/(1000*60*60)) * rc.getHourlyRate(); 
		return price;
	}

}
