package com.nklmthr.games.game29.states;

import com.nklmthr.games.game29.events.MakeMoveEvent;
import com.nklmthr.games.game29.events.TrumpShowEvent;
import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.FetchEvent;
import com.nklmthr.games.game29.model.Game;
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
			TableCard tableCard = new TableCard();
			tableCard.setPlayer(makeMoveEvent.getPlayer());
			tableCard.setCard(makeMoveEvent.getCard());
			if (game.getMatch().getTables().size() == 0) {
				Table table = new Table();
				game.getMatch().getTables().add(table);
			}
			game.getMatch().getTables().get(game.getMatch().getTables().size() - 1).getTableCards().add(tableCard);
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
		return getSection13Generic(game, event);
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
			if (fetch.getPlayer().equals(game.getMatch().getTrumpSetPlayer())) {
				str.append("Trump:&nbsp;&nbsp;<img src='images/" + game.getMatch().getChallengeTrumpSuite()
						+ ".jpg' border=\"1\"/>");
			} else {
				str.append(
						"Open Trump:&nbsp;&nbsp;<a href=\"javascript:openTrump();\"><img src='images/deck.jpeg' border=\"1\"/></a>");
			}
		}
		return str.toString();
	}

	public String getSection32(Game game, Event event) {
		return getSection32Generic(game, event, 8);
	}

	public String getSection33(Game game, Event event) {
		// TODO Auto-generated method stub
		return null;
	}

}
