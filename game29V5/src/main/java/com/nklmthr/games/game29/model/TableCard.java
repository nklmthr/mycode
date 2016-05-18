package com.nklmthr.games.game29.model;

public class TableCard implements Comparable<TableCard> {

	private Player player;
	private Card card;

	public int compareTo(TableCard o) {
		return getCard().compareTo(o.getCard());
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

}
