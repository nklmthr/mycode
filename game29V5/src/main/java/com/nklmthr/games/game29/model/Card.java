package com.nklmthr.games.game29.model;

import java.util.Comparator;

public class Card implements Comparable<Card> {
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
		return "<img src='images/" + suite.ordinal() + "_" + rank.ordinal() + ".jpg'>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suite == null) ? 0 : suite.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (suite != other.suite)
			return false;
		if (rank != other.rank)
			return false;
		return true;
	}

	public int compareTo(Card o) {
		if (getSuite().equals(o.getSuite())) {
			return o.getRank().ordinal() - getRank().ordinal();
		} else {
			return o.getSuite().ordinal() - getSuite().ordinal();
		}
	}

}
