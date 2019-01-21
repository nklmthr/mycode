package com.nklmthr.practice.phase4;

import java.util.ArrayList;
import java.util.List;

public class Arrange {
	static List<BusinessUnit> units = new ArrayList<BusinessUnit>();

	public static void main(String[] args) {

		for (int i = 0; i < 20; i++) {
			int businessUnit = i % 3;
			boolean isLead = i % 10 == 0 ? true : false;
			addTeamMember(0, isLead);
			
			
		}
	}

	private static void showSeatingArragement() {
		System.out.println("###################");
		for(BusinessUnit unit: units){
			System.out.println("unit "+unit.name);
			for(SeatingBay bay: unit.seatingBays){
				System.out.println("\t\tbay "+bay.rowNumber);
				for(Seat seat: bay.seats){
					System.out.println("\t\t\t\tseat "+seat.position);
				}
			}
		}
		
	}

	private static void addTeamMember(int businessUnit, boolean isLead) {
		if(units.size()==0){
			units.add(new BusinessUnit(units.size()+1));
		}
		//showSeatingArragement();
		BusinessUnit unit = units.get(businessUnit);
		if (unit.seatingBays.size() == 0) {
			unit.seatingBays.add(new SeatingBay(unit.seatingBays.size()));
		}
		//showSeatingArragement();
		if (unit.seatingBays.get(unit.seatingBays.size() - 1).seats.size() <= 10) {
			
		}else{
			unit.seatingBays.add(new SeatingBay(unit.seatingBays.size()));
		}
		unit.seatingBays.get(unit.seatingBays.size() - 1).seats
		.add(new Seat(unit.seatingBays.get(unit.seatingBays.size() - 1).seats.size(), isLead));
		showSeatingArragement();
	}

}

class Seat {
	public Seat(int position, boolean isLead) {
		this.position = position;
		this.isLead = isLead;
		this.occupied = true;
	}

	boolean occupied;
	int position;
	boolean isLead;
}

class SeatingBay {
	public SeatingBay(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	List<Seat> seats = new ArrayList<Seat>();
	int rowNumber;
	public boolean isSeatAdditionAllowed(){
		return seats.size() == 9;
	}
}

class BusinessUnit {
	public BusinessUnit(int name) {
		this.name = name;
	}
	int name;
	List<SeatingBay> seatingBays = new ArrayList<SeatingBay>();
}