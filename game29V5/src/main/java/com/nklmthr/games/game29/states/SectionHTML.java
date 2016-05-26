package com.nklmthr.games.game29.states;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nklmthr.games.game29.context.SpringApplicationContext;
import com.nklmthr.games.game29.model.Card;
import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.FetchEvent;
import com.nklmthr.games.game29.model.Game;
import com.nklmthr.games.game29.model.Player;
import com.nklmthr.games.game29.model.Suite;
import com.nklmthr.games.game29.model.Table;
import com.nklmthr.games.game29.model.TableCard;
import com.nklmthr.games.game29.service.PlayerService;

public class SectionHTML {

	PlayerService playerService = SpringApplicationContext.getSpringContext().getBean(PlayerService.class);
	private Logger logger = Logger.getLogger(SectionHTML.class);

	protected boolean checkValidMove(Game game, Player player, Card card) {
		List<Table> tables = game.getMatch().getTables();
		Table currentTable = tables.get(tables.size() - 1);
		for (TableCard tableCard : currentTable.getTableCards()) {
			if (tableCard.getPlayer().equals(player) && tableCard.getCard().equals(card)) {
				return false;
			}
		}
		if (currentTable.getTableCards().size() > 0) {
			Suite base = currentTable.getTableCards().get(0).getCard().getSuite();
			List<Card> cards = game.getMatch().getPlayerCards().get(player);
			for (Card playerCard : cards) {
				if (playerCard.getSuite().equals(base) && !card.getSuite().equals(base)) {
					return false;
				}
			}
		}
		return true;

	}

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

			List<Player> players = playerService.getPlayers();
			for (Player player : players) {
				str.append("<tr>");
				str.append("<td>");
				str.append(player.getPlayerName());
				str.append("</td>");
				str.append("<td>");
				if (player.getLastSeen() == null) {
					str.append("NA");
				} else {
					long time = System.currentTimeMillis() - player.getLastSeen().getTime();
					if (time < 60000) {
						str.append((time / 1000) + " sec ago");
					} else if (time < 3600000) {
						str.append((time / 60000) + " min ago");
					} else {
						str.append("Long Time No see");
					}
				}
				str.append("</td>");
				str.append("<tr>");
			}

			str.append("</table");

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

			str.append("<table class=\"tg\" width=\"100%\" height=\"100%\">");
			str.append("<tr><th class=\"tg-b44r\" colspan=\"2\"> I am: " + fetch.getPlayer().getPlayerName()
					+ "</th></tr>");
			str.append("<tr><td class=\"tg-72gw\">Deal Player: " + game.getMatch().getDealPlayer().getPlayerName()
					+ "</td><td class=\"tg-3smg\">Trump Show: ");
			str.append(game.getMatch().getTrumpShowPlayer() != null
					? game.getMatch().getTrumpShowPlayer().getPlayerName() : "");
			str.append("</td></tr>");
			str.append("<tr><td class=\"tg-vv23\">Team 1: " + game.getMatch().getTeam1Points()
					+ "</td><td class=\"tg-mtwr\">Team 2: " + game.getMatch().getTeam2Points() + "</td></tr>");
			str.append("<tr><td class=\"tg-6c3r\">Challenge Points: "
					+ game.getMatch().getChallenge().getChallengePoints());
			str.append("</td><td class=\"tg-3b15\">Challenge Player: ");
			str.append(game.getMatch().getChallenge().getChallengePlayer().getPlayerName());
			str.append("</td></tr>");
			str.append("<tr><td class=\"tg-6c2r\">KQ Show: "
					+ (game.getMatch().isKQshown() ? game.getMatch().getKQShowPlayer().getPlayerName() : "NA")
					+ "</td><td class=\"tg-3c15\"></td></tr>");
			str.append("");
			str.append("</table");

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

	protected boolean canPlayerWithTurnOpenTrump(Game game, Player playerWithTurn) {
		if (game.getMatch().getTables().size() > 0
				&& game.getMatch().getTables().get(game.getMatch().getTables().size() - 1).getTableCards().size() > 0) {
			Suite base = game.getMatch().getTables().get(game.getMatch().getTables().size() - 1).getTableCards().get(0)
					.getCard().getSuite();
			Player playerLastPlayed = game.getMatch().getTables().get(game.getMatch().getTables().size() - 1)
					.getTableCards()
					.get(game.getMatch().getTables().get(game.getMatch().getTables().size() - 1).getTableCards().size()
							- 1)
					.getPlayer();

			if (playerWithTurn.equals(playerService.getOppositionFirstPlayer(playerLastPlayed))) {
				List<Card> playerCards = game.getMatch().getPlayerCards().get(playerWithTurn);
				for (Card card : playerCards) {
					if (card.getSuite().equals(base)) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

}
