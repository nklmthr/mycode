package com.nklmthr.games.game29.states;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.nklmthr.games.game29.context.SpringApplicationContext;
import com.nklmthr.games.game29.events.ChallengeDoubleEvent;
import com.nklmthr.games.game29.events.ChallengeEvent;
import com.nklmthr.games.game29.model.Card;
import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.FetchEvent;
import com.nklmthr.games.game29.model.Game;
import com.nklmthr.games.game29.model.Match;
import com.nklmthr.games.game29.model.Player;
import com.nklmthr.games.game29.model.State;
import com.nklmthr.games.game29.service.PlayerService;

public class ChallengeReDoubleState extends SectionHTML implements State {
	PlayerService playerService = SpringApplicationContext.getSpringContext().getBean(PlayerService.class);

	public synchronized State transition(Game game, Event event) {
		if (event instanceof ChallengeDoubleEvent) {
			ChallengeDoubleEvent challenge = (ChallengeDoubleEvent) event;
			if (challenge.isPass()) {
				game.getMatch().setChallengeRedoubled(false);
				return new TrumSetState();
			} else if (challenge.isReDdoubleChallenge()) {
				game.getMatch().setChallengeRedoubled(true);
				return new TrumSetState();
			}
		}
		return null;
	}

	public String getSection21(Game game, Event event) {
		return getSection21Generic(game, event, 4);
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

	public String getSection22(Game game, Event event) {
		return getSection22GenericPlayArena(game, event);
	}

	public String getSection23(Game game, Event event) {
		return getSection23Generic(game, event, 4);
	}

	public String getSection31(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;
			Match match = game.getMatch();

			int challengePoints = match.getChallenge().getChallengePoints();
			for (int i = 16; i < 30; i++) {
				if (i == 23) {
					str.append("<br><br><br>");
				}
				if (i < challengePoints) {
					str.append("<a href=\"#\" class=\"buttonNA\"/>" + i + "</a>&nbsp;&nbsp; ");
				} else if (i == challengePoints) {
					str.append("<a href=\"#\" class=\"buttonA\"/>" + i + "</a>&nbsp;&nbsp; ");
				} else {
					str.append("<a href=\"#\" class=\"buttonDB\"/>" + i + "</a>&nbsp;&nbsp; ");
				}
			}
			if (match.isChallengeDoubled() && match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
				str.append(
						"<br><br><a href=\"javascript:void(0);\" onclick=\"throwReDoubleChallenge(true,false)\" class=\"buttonNO\"/>PASS</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append(
						"<a href=\"javascript:void(0);\" onclick=\"throwReDoubleChallenge(false,true)\" class=\"buttonNO\"/>RE DOUBLE</a>");
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
		// TODO Auto-generated method stub
		return null;
	}

}
