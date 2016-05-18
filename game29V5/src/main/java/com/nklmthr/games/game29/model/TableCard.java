package com.nklmthr.games.game29.model;

import java.util.Comparator;

public class TableCard implements Comparator<TableCard> {

	private Player player;
	private Card card;

	public int compare(TableCard o1, TableCard o2) {

		return 0;
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
