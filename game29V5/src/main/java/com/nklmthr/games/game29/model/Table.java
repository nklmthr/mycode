package com.nklmthr.games.game29.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Table {
	private List<TableCard> tableCards = new CopyOnWriteArrayList<TableCard>();
	private Player tableWinner;
	private int tablePoints;

	public List<TableCard> getTableCards() {
		return tableCards;
	}

	public void setTableCards(List<TableCard> tableCards) {
		this.tableCards = tableCards;
	}

	public Player getTableWinner() {
		return tableWinner;
	}

	public void setTableWinner(Player tableWinner) {
		this.tableWinner = tableWinner;
	}

	public int getTablePoints() {
		return tablePoints;
	}

	public void setTablePoints(int tablePoints) {
		this.tablePoints = tablePoints;
	}

}
