package com.nklmthr.games.game29.model;

public enum Rank {
	SEVEN(0), EIGHT(0), QUEEN(0), KING(0), TEN(1), ACE(1), NINE(2), JACK(3);
	int value;

	Rank(int value) {
		this.value = value;
	}
}
