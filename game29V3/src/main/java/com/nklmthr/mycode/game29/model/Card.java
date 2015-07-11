package com.nklmthr.mycode.game29.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.nklmthr.mycode.game29.exception.RefreshException;
import com.nklmthr.mycode.game29.util.HTMLDOMHelper;

public class Card implements Comparable<Card> {
	public enum Rank {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
	};

	public enum Suit {
		SPADES, HEARTS, CLUBS, DIAMONDS
	};

	private final Rank rank;
	private final Suit suit;
	private int priority;
	private final int points;

	private Card(Rank rank, Suit suit, int priority, int points) {
		this.rank = rank;
		this.suit = suit;
		this.priority = priority;
		this.points = points;
	}

	public Rank rank() {
		return rank;
	}

	public Suit suit() {
		return suit;
	}

	public static Suit getSuitFromInt(int suit) {
		for (Suit s : Suit.values()) {
			if (s.ordinal() + 1 == suit) {
				return s;
			}
		}
		return null;
	}

	public static Rank getRankFromInt(int rank) {
		for (Rank r : Rank.values()) {
			if (r.ordinal() + 2 == rank) {
				return r;
			}
		}
		return null;
	}

	public static Card getCardFromRankSuit(int suitNum, int rankNum) {
		Rank rank = getRankFromInt(rankNum);
		Suit suit = getSuitFromInt(suitNum);
		Card card = null;
		if (rank == Rank.SEVEN) {
			card = new Card(rank, suit, 8, 0);
		} else if (rank == Rank.EIGHT) {
			card = new Card(rank, suit, 7, 0);
		} else if (rank == Rank.NINE) {
			card = new Card(rank, suit, 2, 2);
		} else if (rank == Rank.TEN) {
			card = new Card(rank, suit, 4, 1);
		} else if (rank == Rank.JACK) {
			card = new Card(rank, suit, 1, 3);
		} else if (rank == Rank.QUEEN) {
			card = new Card(rank, suit, 6, 0);
		} else if (rank == Rank.KING) {
			card = new Card(rank, suit, 5, 0);
		} else if (rank == Rank.ACE) {
			card = new Card(rank, suit, 3, 1);
		}
		return card;
	}

	public String toString() {
		return rank + " of " + suit;
	}

	public Node toHiddenHTMLString(Document doc) throws RefreshException {
		Node node = HTMLDOMHelper.addHtmlImage(doc, "FlapCard.jpg",
				"images/FlapCard.jpg", "FlapCard.jpg");
		return node;
	}

	private static final List<Card> deck = new ArrayList<Card>();

	public static List<Card> newDeck() {
		deck.clear();
		for (Suit suit : Suit.values())
			for (Rank rank : Rank.values()) {
				if (rank.compareTo(Rank.SEVEN) >= 0) {
					deck.add(getCardFromRankSuit(suit.ordinal() + 1,
							rank.ordinal() + 2));
				}
			}
		return deck; // Return copy of prototype deck
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getPoints() {
		return points;
	}

	public static List<Card> getRandomCards(int numOfCards) {
		List<Card> randomCards = new ArrayList<Card>(numOfCards);
		List<Card> removeCards = new ArrayList<Card>(numOfCards);
		Collections.shuffle(deck);
		for (int i = 0; i < numOfCards; i++) {
			removeCards = deck.subList(deck.size() - numOfCards, deck.size());
		}
		randomCards = new ArrayList<Card>(removeCards);
		removeCards.clear();
		System.out.println("remaining cards in deck = " + deck.size());
		return randomCards;
	}

	public Node toHTMLNode(Document doc) throws RefreshException {
		Node node = HTMLDOMHelper.addHtmlImage(doc, (suit().ordinal() + 1)
				+ "_" + (rank().ordinal() + 1), "images/"
				+ (suit().ordinal() + 1) + "_" + (rank().ordinal() + 2)
				+ ".jpg",
				"images/" + (suit().ordinal() + 1) + "_" + (rank.ordinal() + 2)
						+ ".jpg");

		return node;
	}

	public int compareTo(Card card) {
		if (card.suit.compareTo(this.suit()) == 0) {
			if (card.rank().compareTo(this.rank) > 0) {
				return 1;
			} else if (card.rank().compareTo(this.rank) < 0) {
				return -1;
			}
		} else if (card.suit.compareTo(this.suit()) < 0) {
			return -1;
		} else if (card.suit.compareTo(this.suit()) > 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + points;
		result = prime * result + priority;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
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
		if (points != other.points)
			return false;
		if (priority != other.priority)
			return false;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}
}
