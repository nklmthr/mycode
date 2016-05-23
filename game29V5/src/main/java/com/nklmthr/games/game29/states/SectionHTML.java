package com.nklmthr.games.game29.states;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nklmthr.games.game29.context.SpringApplicationContext;
import com.nklmthr.games.game29.model.Card;
import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.FetchEvent;
import com.nklmthr.games.game29.model.Game;
import com.nklmthr.games.game29.model.Match;
import com.nklmthr.games.game29.model.Player;
import com.nklmthr.games.game29.model.Table;
import com.nklmthr.games.game29.model.TableCard;
import com.nklmthr.games.game29.service.PlayerService;

public class SectionHTML {

	PlayerService playerService = SpringApplicationContext.getSpringContext().getBean(PlayerService.class);
	private Logger logger = Logger.getLogger(SectionHTML.class);

	public String getSection11Generic(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;
			str.append("<table border=\"0\" width=\"100%\" height=\"100%\" >");
			str.append("<tr><td>");
			str.append("<img src='images/score/score_team1_" + game.getTeam1score() + ".jpg'/>");
			str.append("</td><td>");
			str.append("<img src='images/score/score_team2_" + game.getTeam2score() + ".jpg'/>");
			str.append("</td>");
			str.append("</tr>");

		}
		return str.toString();
	}

	public String getSection12Generic(Game game, Event event, int numCards) {
		StringBuilder str = new StringBuilder();
		if (game.getMatch().isPointLessGame() || game.getMatch().isJackLessGame()) {
			FetchEvent fetch = (FetchEvent) event;
			Map<Player, List<Card>> playerCards = game.getMatch().getPlayerCards();
			List<Card> cards = playerCards.get(playerService.getTeamMate(fetch.getPlayer()));
			int count = 0;
			str.append("<p>");
			for (Card card : cards) {

				if (count % 4 == 0) {
					str.append("</p><p>");
				}
				count++;
				if (count > numCards)
					break;
				str.append(card.toString());
				str.append("&nbsp;&nbsp;&nbsp;");
			}
			str.append("</p><br>");
		} else {
			str.append("<img src='images/FlapCard.jpg'>");
			str.append("&nbsp;&nbsp;&nbsp;");
			str.append("<img src='images/FlapCard.jpg'>");
			str.append("&nbsp;&nbsp;&nbsp;");
		}
		return str.toString();
	}

	public String getSection13Generic(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;

			str.append("<table border=\"1\" width=\"100%\" height=\"100%\" >");
			str.append("<tr> <td><a href=\"#\" class=\"buttonBlack\"/>" + fetch.getPlayer().getPlayerName().substring(0,
					fetch.getPlayer().getPlayerName().length() > 6 ? 6 : fetch.getPlayer().getPlayerName().length())
					+ "</a></td>");
			str.append(
					" <td>Deal:<a href=\"#\" class=\"buttonBlue\"/>"
							+ game.getMatch().getDealPlayer().getPlayerName()
									.substring(0,
											game.getMatch().getDealPlayer().getPlayerName().length() > 6 ? 6
													: game.getMatch().getDealPlayer().getPlayerName().length())
							+ "</a></td>");
			str.append("<td>Challenger<a href=\"#\" class=\"buttonRed\"/>"
					+ game.getMatch().getChallenge().getChallengePlayer().getPlayerName().substring(0,
							game.getMatch().getChallenge().getChallengePlayer().getPlayerName().length() > 6 ? 6
									: game.getMatch().getChallenge().getChallengePlayer().getPlayerName().length())
					+ "</a></td>");
			str.append("</tr>");
			str.append(" <tr> <td>Team 1:<a href=\"#\" class=\"buttonGreen\"/>" + game.getMatch().getTeam1Points()
					+ "</a></td>");
			str.append("<td>Team 2:<a href=\"#\" class=\"buttonGreen\"/>" + game.getMatch().getTeam2Points()
					+ "</a></td>");
			str.append("<td>Points Remaining:<a href=\"#\" class=\"buttonBlack\"/>"
					+ (29 - (game.getMatch().getTeam1Points() + game.getMatch().getTeam2Points())) + "</a></td></tr>");
			str.append("<tr> <td colspan=3>Challenge Points:<a href=\"#\" class=\"buttonGreen\"/>"
					+ game.getMatch().getChallenge().getChallengePoints() + "</a></td> </tr>");

			if (game.getMatch().isChallengeDoubled() || game.getMatch().isChallengeRedoubled()) {
				str.append("<tr><td>");
				if (game.getMatch().isChallengeDoubled()) {
					str.append("<a href=\"#\" class=\"buttonGreen\"/>Doubled</a>");
				}
				str.append("</td><td colspan=2>");
				if (game.getMatch().isChallengeRedoubled()) {
					str.append("<a href=\"#\" class=\"buttonRed\"/>Re-Doubled</a>");
				}
				str.append("</td></tr>");
			}
			if (game.getMatch().getTrumpShowPlayer() != null) {
				str.append("<tr><td colspan=2>");
				str.append("Trump Opened by Player<a href=\"#\" class=\"buttonRed\"/>"
						+ game.getMatch().getTrumpShowPlayer().getPlayerName() + "</a>");
				str.append("</td>");
				str.append("<tr><td>");
				String kqPlayer = game.getMatch().getKQShowPlayer() != null
						? game.getMatch().getKQShowPlayer().getPlayerName() : "NA";
				str.append("KQ show Player<a href=\"#\" class=\"buttonRed\"/>" + kqPlayer + "</a>");
				str.append("</td>");
				str.append("</tr>");
			}
			str.append("</table>");
			str.append("");

		}
		return str.toString();
	}

	public String getSection21Generic(Game game, Event event, int numCards) {
		StringBuilder str = new StringBuilder();
		if (game.getMatch().isPointLessGame() || game.getMatch().isJackLessGame()) {
			FetchEvent fetch = (FetchEvent) event;
			Map<Player, List<Card>> playerCards = game.getMatch().getPlayerCards();
			List<Card> cards = playerCards.get(playerService.getOppositionFirstPlayer(fetch.getPlayer()));
			int count = 0;
			str.append("<p>");
			for (Card card : cards) {

				if (count % 4 == 0) {
					str.append("</p><p>");
				}
				count++;
				if (count > numCards)
					break;
				str.append(card.toString());
				str.append("&nbsp;&nbsp;&nbsp;");
			}
			str.append("</p><br>");
		} else {
			str.append("<img src='images/FlapCard.jpg'>");
			str.append("&nbsp;&nbsp;&nbsp;");
			str.append("<img src='images/FlapCard.jpg'>");
			str.append("&nbsp;&nbsp;&nbsp;");
		}
		return str.toString();
	}

	public String getSection23Generic(Game game, Event event, int numCards) {
		StringBuilder str = new StringBuilder();
		if (game.getMatch().isPointLessGame() || game.getMatch().isJackLessGame()) {
			FetchEvent fetch = (FetchEvent) event;
			Map<Player, List<Card>> playerCards = game.getMatch().getPlayerCards();
			List<Card> cards = playerCards.get(playerService.getOppositionSecondPlayer(fetch.getPlayer()));
			int count = 0;
			str.append("<p>");
			for (Card card : cards) {

				if (count % 4 == 0) {
					str.append("</p><p>");
				}
				count++;
				if (count > numCards)
					break;
				str.append(card.toString());
				str.append("&nbsp;&nbsp;&nbsp;");
			}
			str.append("</p><br>");
		} else {
			str.append("<img src='images/FlapCard.jpg'>");
			str.append("&nbsp;&nbsp;&nbsp;");
			str.append("<img src='images/FlapCard.jpg'>");
			str.append("&nbsp;&nbsp;&nbsp;");
		}
		return str.toString();
	}

	public String getSection32Generic(Game game, Event event, int numCards) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;
			Map<Player, List<Card>> playerCards = game.getMatch().getPlayerCards();
			List<Card> cards = playerCards.get(fetch.getPlayer());
			int count = 0;
			str.append("<p>");
			for (Card card : cards) {

				if (count % 4 == 0) {
					str.append("</p><p>");
				}
				count++;
				if (count > numCards)
					break;
				str.append("<a href=\"javascript:makeMove(" + (card.getSuite().ordinal()) + ","
						+ (card.getRank().ordinal()) + ");\">");
				str.append(card.toString());
				str.append("</a>");
				str.append("&nbsp;&nbsp;&nbsp;");
			}
			str.append("</p><br>");
		}
		logger.info(str.toString());
		return str.toString();
	}

	public String getSection22GenericPlayArena(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		// Match match = game.getMatch();
		List<Table> tables = game.getMatch().getTables();
		Table currentTable = tables.get(tables.size() - 1);
		Table prevTable = tables.size() > 1 ? tables.get(tables.size() - 2) : null;
		if (currentTable.getTableCards().size() == 0 && prevTable != null) {
			for (TableCard tableCard : prevTable.getTableCards()) {
				str.append(tableCard.getPlayer().getPlayerName());
				str.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append(tableCard.getCard().toString());
				str.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			str.append("<br>Waiting for&nbsp;" + prevTable.getTableWinner().getPlayerName() + " to make move");
		} else {
			for (TableCard tableCard : currentTable.getTableCards()) {
				str.append(tableCard.getPlayer().getPlayerName());
				str.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append(tableCard.getCard().toString());
				str.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			}
		}
		str.append("<br>");

		return str.toString();
	}

	public void calculateOnMatchEnd(Game game) {
		if (game.getMatch().getTables().size() == 8) {
			game.getMatch()
					.setPoints(game.getMatch().getTables().get(game.getMatch().getTables().size() - 1).getTableWinner()
							.getTeam(), game
									.getMatch().getPoints(game.getMatch().getTables()
											.get(game.getMatch().getTables().size() - 1).getTableWinner().getTeam())
									+ 1);
			if (game.getMatch().getPoints(game.getMatch().getChallenge().getChallengePlayer().getTeam()) >= game
					.getMatch().getChallenge().getChallengePoints()) {

				if (game.getMatch().isChallengeRedoubled()) {
					game.setTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam(),
							game.getTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam()) + 4);
				} else if (game.getMatch().isChallengeDoubled()) {
					game.setTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam(),
							game.getTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam()) + 2);
				} else {
					game.setTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam(),
							game.getTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam()) + 1);
				}
			} else {
				if (game.getMatch().isChallengeRedoubled()) {
					game.setTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam(),
							game.getTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam()) - 4);
				} else if (game.getMatch().isChallengeDoubled()) {
					game.setTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam(),
							game.getTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam()) - 2);
				} else {
					game.setTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam(),
							game.getTeamScore(game.getMatch().getChallenge().getChallengePlayer().getTeam()) - 1);
				}
			}
		}

	}

}
