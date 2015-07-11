package com.nklmthr.mycode.game29.model;

import java.util.LinkedHashMap;

public class Table {
	private int tableId;
	private LinkedHashMap<Player, Card> tableCards;
	private Player bestPlayer;
	private Card bestTableCard;
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public LinkedHashMap<Player, Card> getTableCards() {
		return tableCards;
	}
	public void setTableCards(LinkedHashMap<Player, Card> tableCards) {
		this.tableCards = tableCards;
	}
	public Player getBestPlayer() {
		return bestPlayer;
	}
	public void setBestPlayer(Player bestPlayer) {
		this.bestPlayer = bestPlayer;
	}
	public Card getBestTableCard() {
		return bestTableCard;
	}
	public void setBestTableCard(Card bestTableCard) {
		this.bestTableCard = bestTableCard;
	}
	@Override
	public String toString() {
		return "Table [tableId=" + tableId + ", tableCards=" + tableCards
				+ ", bestPlayer=" + bestPlayer + ", bestTableCard="
				+ bestTableCard + "]";
	}
	
}
