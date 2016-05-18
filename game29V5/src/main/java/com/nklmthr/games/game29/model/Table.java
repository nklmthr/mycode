package com.nklmthr.games.game29.model;

import java.util.LinkedHashSet;

public class Table {
	private LinkedHashSet<TableCard> tableCards = new LinkedHashSet<TableCard>();

	public LinkedHashSet<TableCard> getTableCards() {
		return tableCards;
	}

	public void setTableCards(LinkedHashSet<TableCard> tableCards) {
		this.tableCards = tableCards;
	}
	
}
