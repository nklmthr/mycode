package com.nklmthr.games.game29.states;

import java.util.ArrayList;
import java.util.List;

import com.nklmthr.games.game29.model.Card;
import com.nklmthr.games.game29.model.Rank;
import com.nklmthr.games.game29.model.Suite;

public class test {

	public static void main(String[] args) {
		List<Card> cards = new ArrayList<Card>();

		for (Suite suite : Suite.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suite, rank));

			}
		}
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 31; j++) {
				System.out.println(cards.get(i).getSuite() + "," + cards.get(i).getRank() + " ->compared to ->"
						+ cards.get(j).getSuite() + "," + cards.get(j).getRank() + " = "
						+ cards.get(i).compareTo(cards.get(j)));
			}
		}

	}

}
