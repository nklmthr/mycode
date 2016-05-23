package com.nklmthr.games.game29.states;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.nklmthr.games.game29.events.TrumpSetEvent;
import com.nklmthr.games.game29.model.Card;
import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.FetchEvent;
import com.nklmthr.games.game29.model.Game;
import com.nklmthr.games.game29.model.Player;
import com.nklmthr.games.game29.model.Rank;
import com.nklmthr.games.game29.model.State;
import com.nklmthr.games.game29.model.Suite;

public class TrumSetState extends SectionHTML implements State {

	public synchronized State transition(Game game, Event event) {
		if (event instanceof TrumpSetEvent) {
			TrumpSetEvent trumpSetEvent = (TrumpSetEvent) event;
			game.getMatch().setChallengeTrumpSuite(Suite.values()[trumpSetEvent.getTrump()]);
			game.getMatch().setTrumpSetPlayer(trumpSetEvent.getPlayer());
			Map<Player, List<Card>> playerCards = game.getMatch().getPlayerCards();
			List<Card> team1Cards = new ArrayList<Card>();
			List<Card> team2Cards = new ArrayList<Card>();

			for (Player player : playerCards.keySet()) {
				List<Card> cards = playerCards.get(player);
				cards.clear();
				cards.addAll(game.getMatch().getCards().subList((player.getPlayerId() - 1) * 8,
						((player.getPlayerId() - 1) * 8) + 8));
				if (player.getTeam() % 2 == 0) {
					team2Cards.addAll(cards);
				} else {
					team1Cards.addAll(cards);
				}
			}

			if (!(team1Cards.contains(new Card(Suite.SPADES, Rank.JACK))
					|| team1Cards.contains(new Card(Suite.HEARTS, Rank.JACK))
					|| team1Cards.contains(new Card(Suite.DIAMONDS, Rank.JACK))
					|| team1Cards.contains(new Card(Suite.CLUBS, Rank.JACK)))) {
				game.getMatch().setJackLessGame(true);
			}
			if (!(team2Cards.contains(new Card(Suite.SPADES, Rank.JACK))
					|| team2Cards.contains(new Card(Suite.HEARTS, Rank.JACK))
					|| team2Cards.contains(new Card(Suite.DIAMONDS, Rank.JACK))
					|| team2Cards.contains(new Card(Suite.CLUBS, Rank.JACK)))) {
				game.getMatch().setJackLessGame(true);
			}

			return new PlayWithTrumpNotShownStage();
		}
		return null;

	}

	public String getSection11(Game game, Event event) {
		return getSection11Generic(game, event);
	}

	public String getSection12(Game game, Event event) {
		return getSection12Generic(game, event, 4);
	}

	public String getSection13(Game game, Event event) {
		return getSection13Generic(game, event);
	}

	public String getSection21(Game game, Event event) {
		return getSection12Generic(game, event, 4);
	}

	public String getSection22(Game game, Event event) {
		return getSection22GenericPlayArena(game, event);
	}

	public String getSection23(Game game, Event event) {
		return getSection12Generic(game, event, 4);
	}

	public String getSection31(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;
			if (fetch.getPlayer().equals(game.getMatch().getTrumpSetPlayer())) {
				str.append("<a href=\"javascript:setTrump(0)\"><img src='images/SPADES.jpg' border=\"1\"></a>");
				str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append("<a href=\"javascript:setTrump(1)\"><img src='images/HEARTS.jpg' border=\"1\"></a>");
				str.append("<br><br>");
				str.append("<a href=\"javascript:setTrump(2)\"><img src='images/CLUBS.jpg' border=\"1\"></a>");
				str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append("<a href=\"javascript:setTrump(3)\"><img src='images/DIAMONDS.jpg' border=\"1\"></a>");
			} else {
				str.append("Waiting for trump to be set");
			}
		}
		return str.toString();
	}

	public String getSection32(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;
			Map<Player, List<Card>> playerCards = game.getMatch().getPlayerCards();
			List<Card> cards = playerCards.get(fetch.getPlayer());
			Collections.sort(cards);
			int count = 0;
			str.append("<p>");
			for (Card card : cards) {

				if (count % 4 == 0) {
					str.append("</p><p>");
				}
				count++;
				if (count > 4)
					break;
				str.append(card.toString());
				str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			str.append("</p><br>");
		}
		return str.toString();
	}

	public String getSection33(Game game, Event event) {
		return null;
	}

}
