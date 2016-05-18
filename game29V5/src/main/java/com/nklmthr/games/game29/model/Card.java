package com.nklmthr.games.game29.model;

public class Card {
	private Suite suite;
	private Rank rank;

	public Card(Suite suite, Rank rank) {
		this.suite = suite;
		this.rank = rank;
	}

	public Suite getSuite() {
		return suite;
	}

	public void setSuite(Suite suite) {
		this.suite = suite;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "<img src='images/" + (suite.ordinal() + 1) + "_" + (rank.ordinal() + 7) + ".jpg'>";
	}

}
