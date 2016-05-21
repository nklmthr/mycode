package com.nklmthr.games.game29.states;

import java.util.List;
import java.util.Map;

import com.nklmthr.games.game29.events.MakeMoveEvent;
import com.nklmthr.games.game29.events.TrumpShowEvent;
import com.nklmthr.games.game29.model.Card;
import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.FetchEvent;
import com.nklmthr.games.game29.model.Game;
import com.nklmthr.games.game29.model.Player;
import com.nklmthr.games.game29.model.State;
import com.nklmthr.games.game29.model.Table;
import com.nklmthr.games.game29.model.TableCard;

public class PlayWithTrumpNotShownStage extends SectionHTML implements State {

	public State transition(Game game, Event event) {
		if (event instanceof TrumpShowEvent) {
			TrumpShowEvent trumpShowEvent = (TrumpShowEvent) event;
			game.getMatch().setTrumpShowPlayer(trumpShowEvent.getPlayer());
			return new PlayWithTrumpShownStage();
		} else if (event instanceof MakeMoveEvent) {
			MakeMoveEvent makeMoveEvent = (MakeMoveEvent) event;
			makeMove(game, makeMoveEvent.getPlayer(), makeMoveEvent.getCard());
			return new PlayWithTrumpNotShownStage();
		}
		return null;
	}

	public String getSection11(Game game, Event event) {
		return getSection11Generic(game, event);
	}

	public String getSection12(Game game, Event event) {
		return getSection12Generic(game, event, 8);
	}

	public String getSection13(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;
			str.append("I am Player" + fetch.getPlayer().getPlayerName());
			str.append("<br>");
			str.append("Trump Set Player " + game.getMatch().getChallenge().getChallengePlayer().getPlayerName());
			str.append("<br><br>");
			str.append("Team 1:->" + game.getMatch().getTeam1Points());
			str.append("<br>");
			str.append("Team 2:->");
			str.append(game.getMatch().getTeam2Points());
			str.append("<br>");
			str.append("Points Remaining:->");
			str.append((29 - (game.getMatch().getTeam1Points() + game.getMatch().getTeam2Points())));
			str.append("<br>");
			str.append("Deal Player" + game.getMatch().getDealPlayer().getPlayerName());

		}
		return str.toString();
	}

	public String getSection21(Game game, Event event) {
		return getSection21Generic(game, event, 8);
	}

	public String getSection22(Game game, Event event) {
		return getSection22GenericPlayArena(game, event);
	}

	public String getSection23(Game game, Event event) {
		return getSection23Generic(game, event, 8);
	}

	public String getSection31(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;
			str.append("<br>");
			str.append(
					"Open Trump:&nbsp;&nbsp;<a href=\"javascript:openTrump();\"><img src='images/deck.jpeg' border=\"1\"/></a>");
			if (fetch.getPlayer().equals(game.getMatch().getTrumpSetPlayer())) {
				str.append("<br><br>");
				str.append("Trump:&nbsp;&nbsp;<img src='images/" + game.getMatch().getChallengeTrumpSuite()
						+ ".jpg' border=\"1\"/>");
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
			int count = 0;
			str.append("<p>");
			for (Card card : cards) {
				if (count % 4 == 0) {
					str.append("</p><p>");
				}
				count++;
				List<Table> tables = game.getMatch().getTables();
				Table currentTable = tables.get(tables.size() - 1);
				TableCard lastTableCard = null;
				if (currentTable.getTableCards().size() > 0) {
					lastTableCard = currentTable.getTableCards().get(currentTable.getTableCards().size() - 1);
				}
				boolean check1 = lastTableCard != null
						&& fetch.getPlayer().equals(playerService.getOppositionFirstPlayer(lastTableCard.getPlayer()));
				boolean check2 = false;
				if (game.getMatch().getTables().size() > 1) {
					check2 = lastTableCard == null && game.getMatch().getTables()
							.get(game.getMatch().getTables().size() - 2).getTableWinner().equals(fetch.getPlayer());
				} else {
					check2 = game.getMatch().getChallenge().getChallengePlayer().equals(fetch.getPlayer())
							&& currentTable.getTableCards().size() == 0;
				}
				if (check1 || check2) {
					str.append("<a href=\"javascript:makeMove(" + (card.getSuite().ordinal()) + ","
							+ (card.getRank().ordinal()) + ");\">");
					str.append(card.toString());
					str.append("</a>");
					str.append("&nbsp;&nbsp;&nbsp;");
				} else {
					str.append(card.toString());
					str.append("&nbsp;&nbsp;&nbsp;");
				}

			}
			str.append("</p><br>");
		}
		return str.toString();
	}

	public String getSection33(Game game, Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void makeMove(Game game, Player player, Card card) {
		List<Table> tables = game.getMatch().getTables();
		Table currentTable = tables.get(tables.size() - 1);

		TableCard tc = new TableCard();
		tc.setPlayer(player);
		tc.setCard(card);
		currentTable.getTableCards().add(tc);
		if (currentTable.getTableCards().size() == 4) {
			Player bestPlayer = null;
			Card bestCard = null;
			int points = 0;
			for (TableCard tableCard : currentTable.getTableCards()) {
				points += tableCard.getCard().getRank().getValue();
				if (bestCard == null) {
					bestCard = tableCard.getCard();
					bestPlayer = tableCard.getPlayer();
				} else {
					if (tableCard.getCard().compareTo(bestCard) > 0) {
						bestCard = tableCard.getCard();
						bestPlayer = tableCard.getPlayer();
					}
				}
			}
			currentTable.setTableWinner(bestPlayer);
			currentTable.setTablePoints(points);
			if (currentTable.getTableWinner().getTeam() == 1) {
				game.getMatch().setTeam1Points(game.getMatch().getTeam1Points() + currentTable.getTablePoints());
			} else if (currentTable.getTableWinner().getTeam() == 2) {
				game.getMatch().setTeam2Points(game.getMatch().getTeam2Points() + currentTable.getTablePoints());
			}
			Table table = new Table();
			tables.add(table);
		}
		
		List<Card> playerCards = game.getMatch().getPlayerCards().get(player);
		playerCards.remove(card);
	}

}
