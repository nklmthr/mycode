package com.srishti.parkinglot;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * Lots, Ticketing
 */

public class ParkingLot {
	private List<Slot> slots = new ArrayList<Slot>();

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

}
