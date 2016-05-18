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
		// TODO Auto-generated method stub
		return null;
	}

	public String getSection12Generic(Game game, Event event, int numCards) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
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
		}
		return str.toString();
	}

	public String getSection13Generic(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;
			str.append("I am " + fetch.getPlayer().getPlayerName());
			str.append("<br><br>Challenger " + game.getMatch().getChallenge().getChallengePlayer().getPlayerName());
			str.append("<br><br>Team 1:->" + game.getMatch().getTeam1Points());
			str.append("<br>Team 2:->");
			str.append(game.getMatch().getTeam2Points());
			str.append("<br>Points Remaining:->");
			str.append((29 - (game.getMatch().getTeam1Points() + game.getMatch().getTeam2Points())));
			str.append("<br>");
			str.append("Deal Player" + game.getMatch().getDealPlayer().getPlayerName());
		}
		return str.toString();
	}

	public String getSection21Generic(Game game, Event event, int numCards) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
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
		}
		return str.toString();
	}

	public String getSection23Generic(Game game, Event event, int numCards) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
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

}
