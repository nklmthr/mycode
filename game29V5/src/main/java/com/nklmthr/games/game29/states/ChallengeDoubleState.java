package com.nklmthr.games.game29.states;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.nklmthr.games.game29.events.ChallengeEvent;
import com.nklmthr.games.game29.model.Card;
import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.FetchEvent;
import com.nklmthr.games.game29.model.Game;
import com.nklmthr.games.game29.model.Match;
import com.nklmthr.games.game29.model.Player;
import com.nklmthr.games.game29.model.State;

public class ChallengeDoubleState extends SectionHTML implements State {
	public synchronized State transition(Game game, Event event) {
		if (event instanceof ChallengeEvent) {
			ChallengeEvent challenge = (ChallengeEvent) event;
			// If player passes
			if (challenge.isPass()) {
				// if the player is the last player to pass
				// or challenge completed
				// move to next state
				if (challenge.getPlayer().equals(game.getMatch().getDealPlayer()) || game.getMatch().getChallenge()
						.getChallengePlayer().equals(game.getMatch().getDealPlayer())) {
					if (game.getMatch().isChallengeDoubled()) {
						return new ChallengeReDoubleState();
					} else {
						game.getMatch().setTrumpSetPlayer(game.getMatch().getChallenge().getChallengePlayer());
						return new TrumSetState();
					}
				}
				// if the player passing is primary player
				else if (challenge.getPlayer().equals(game.getMatch().getChallenge().getChallengePrimaryPlayer())) {
					game.getMatch().getChallenge()
							.setChallengePrimaryPlayer(game.getMatch().getChallenge().getChallengeSecondaryPlayer());
					game.getMatch().getChallenge().setChallengeSecondaryPlayer(playerService
							.getOppositionFirstPlayer(game.getMatch().getChallenge().getChallengeSecondaryPlayer()));
				}
				// if the player passing is not primary player
				else {
					game.getMatch().getChallenge()
							.setChallengeSecondaryPlayer(playerService.getOppositionFirstPlayer(challenge.getPlayer()));
				}
			}
			if (challenge.isDoubleChallenge()) {
				game.getMatch().setChallengeDoubled(true);
				return new ChallengeReDoubleState();
			}
			if (game.getMatch().getChallenge().getChallengeSecondaryPlayer().equals(challenge.getPlayer())) {
				if (game.getMatch().getChallenge().getChallengePoints() < challenge.getChallengePoints()) {
					game.getMatch().getChallenge().setChallengePoints(challenge.getChallengePoints());
					game.getMatch().getChallenge().setChallengePlayer(challenge.getPlayer());
				}
			} else if (game.getMatch().getChallenge().getChallengePrimaryPlayer().equals(challenge.getPlayer())) {
				if (game.getMatch().getChallenge().getChallengePoints() <= challenge.getChallengePoints()) {
					game.getMatch().getChallenge().setChallengePoints(challenge.getChallengePoints());
					game.getMatch().getChallenge().setChallengePlayer(challenge.getPlayer());
				}
			}
		}
		return new ChallengeDoubleState();
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
			// str.append("<br>");
			int challengePoints = match.getChallenge().getChallengePoints();
			if (match.getChallenge().getChallengePrimaryPlayer().equals(fetch.getPlayer())) {
				str.append("<table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"2\">");
				str.append("<tr>");
				for (int i = 16; i < 30; i++) {
					if (i == 23) {
						str.append("</td></tr><tr><td align=\"center\" valign=\"center\">");
					} else {
						str.append("<td align=\"center\" valign=\"center\">");
					}
					if (i < challengePoints) {
						str.append("<a href=\"#\" class=\"buttonRed\"/>" + i + "</a>");
					} else if (i == challengePoints
							&& match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
						str.append("<a href=\"javascript:throwChallenge(" + i
								+ ",false,false);\" class=\"buttonGreen\"/>" + i + "</a>");
					} else {
						str.append("<a href=\"javascript:throwChallenge(" + i
								+ ",false,false);\" class=\"buttonBlue\"/>" + i + "</a>");
					}
					str.append("</td>");
				}
				str.append("</tr>");

				if (!match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
					str.append("<tr>");
					str.append("<td colspan=\"3\" align=\"left\" valign=\"center\">");
					str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + challengePoints
							+ ",false,true)\" class=\"buttonGreen\"/>DOUBLE</a>");
					str.append("</td>");
					str.append("<td colspan=\"4\" align=\"right\" valign=\"center\">");
					str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + challengePoints
							+ ",true,false)\" class=\"buttonGreen\"/>PASS</a>");
					str.append("</td></tr>");
				}
				str.append("</table>");

			} else if (match.getChallenge().getChallengeSecondaryPlayer().equals(fetch.getPlayer())) {
				str.append("<table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"2\">");
				str.append("<tr>");
				for (int i = 16; i < 30; i++) {
					if (i == 23) {
						str.append("</td></tr><tr><td align=\"center\" valign=\"center\">");
					} else {
						str.append("<td align=\"center\" valign=\"center\">");
					}
					if (i < challengePoints) {
						str.append("<a href=\"#\" class=\"buttonRed\"/>" + i + "</a>");
					} else if (i == challengePoints
							&& !match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
						str.append("<a href=\"javascript:throwChallenge(" + i + ",false,false);\" class=\"buttonRed\"/>"
								+ i + "</a>");
					} else if (i == challengePoints
							&& match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
						str.append("<a href=\"javascript:throwChallenge(" + i
								+ ",false,false);\" class=\"buttonGreen\"/>" + i + "</a>");
					} else {
						str.append("<a href=\"javascript:throwChallenge(" + i
								+ ",false,false);\" class=\"buttonBlue\"/>" + i + "</a>");
					}
					str.append("</td>");
				}
				str.append("</tr>");
				if (!match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
					str.append("<tr>");
					str.append("<td colspan=\"3\" align=\"left\" valign=\"center\">");
					str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + challengePoints
							+ ",false,true)\" class=\"buttonGreen\"/>DOUBLE</a>");
					str.append("</td>");
					str.append("<td colspan=\"4\" align=\"right\" valign=\"center\">");
					str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + challengePoints
							+ ",true,false)\" class=\"buttonGreen\"/>PASS</a>");

					str.append("</td></tr>");
				}
				str.append("</table>");

			} else {
				str.append("<table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"2\">");
				str.append("<tr>");
				for (int i = 16; i < 30; i++) {
					if (i == 23) {
						str.append("</td></tr><tr><td align=\"center\" valign=\"center\">");
					} else {
						str.append("<td align=\"center\" valign=\"center\">");
					}
					if (i < challengePoints) {
						str.append("<a href=\"#\" class=\"buttonRed\"/>" + i + "</a>");
					} else if (i == challengePoints) {
						str.append("<a href=\"#\" class=\"buttonBlue\"/>" + i + "</a>");
					} else {
						str.append("<a href=\"#\" class=\"buttonBlack\"/>" + i + "</a>");
					}
					str.append("</td>");
				}
				str.append("</tr>");
				str.append("</table>");
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
