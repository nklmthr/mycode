package com.nklmthr.games.game29.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nklmthr.games.game29.events.ChallengeDoubleEvent;
import com.nklmthr.games.game29.events.ChallengeEvent;
import com.nklmthr.games.game29.events.MakeMoveEvent;
import com.nklmthr.games.game29.events.TrumpSetEvent;
import com.nklmthr.games.game29.events.TrumpShowEvent;
import com.nklmthr.games.game29.model.Card;
import com.nklmthr.games.game29.model.FetchEvent;
import com.nklmthr.games.game29.model.Game;
import com.nklmthr.games.game29.model.Match;
import com.nklmthr.games.game29.model.Player;
import com.nklmthr.games.game29.model.Rank;
import com.nklmthr.games.game29.model.Suite;
import com.nklmthr.games.game29.states.ChallengeDoubleState;

public class Game29Service {

	private Logger logger = Logger.getLogger(Game29Service.class);
	@Autowired
	PlayerService playerService;

	private Game game;
	private boolean initialized = false;

	public String getSection12(int playerId) {
		FetchEvent event = new FetchEvent();
		event.setPlayer(playerService.get(playerId));
		return game.getMatch().getState().getSection12(game, event);
	}

	public String getSection21(int playerId) {
		FetchEvent event = new FetchEvent();
		event.setPlayer(playerService.get(playerId));
		return game.getMatch().getState().getSection21(game, event);
	}

	public String getSection23(int playerId) {
		FetchEvent event = new FetchEvent();
		event.setPlayer(playerService.get(playerId));
		return game.getMatch().getState().getSection23(game, event);
	}

	public String getSection32(int playerId) {
		FetchEvent event = new FetchEvent();
		event.setPlayer(playerService.get(playerId));
		return game.getMatch().getState().getSection32(game, event);
	}

	private List<Card> getSchuffledDeck() {
		List<Card> cards = new ArrayList<Card>();
		for (Suite suite : Suite.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suite, rank));
			}
		}
		List<Card> randomCards = new ArrayList<Card>(32);
		randomCards.addAll(cards);
		Collections.shuffle(randomCards);
		return randomCards;
	}

	public synchronized void initialize() {
		if (initialized) {
			return;
		}

		logger.info("Getting into initialize block" + initialized);
		playerService.initialize();
		game = new Game();
		game.setTeam1score(0);
		game.setTeam2score(0);
		newDeal();
		initialized = true;
	}

	public String newDeal() {
		Match match = new Match();
		match.setState(new ChallengeDoubleState());
		match.setDealPlayer(playerService.get(1));
		match.getChallenge().setChallengePlayer(playerService.getOppositionFirstPlayer(match.getDealPlayer()));
		match.getChallenge()
				.setChallengePrimaryPlayer(playerService.getOppositionFirstPlayer(match.getDealPlayer().getPlayerId()));
		match.getChallenge()
				.setChallengeSecondaryPlayer(playerService.getTeamMate(match.getDealPlayer().getPlayerId()));
		match.getChallenge().setChallengePoints(16);
		
		List<Card> cards = getSchuffledDeck();
		Map<Player, List<Card>> playerCards = new HashMap<Player, List<Card>>();
		for (int i = 0; i < 4; i++) {
			playerCards.put(playerService.get(i + 1), cards.subList(i * 8, (i * 8) + 8));
		}
		match.setPlayerCards(playerCards);
		game.setMatch(match);
		return "Success";
	}

	public String getSection11(int playerId) {
		String str = "Team 1: " + game.getTeam1score() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Team 2:" + game.getTeam2score();
		return str;
	}

	public String getSection13(int playerId) {
		FetchEvent event = new FetchEvent();
		event.setPlayer(playerService.get(playerId));
		return game.getMatch().getState().getSection13(game, event);
	}

	public String setNewChallenge(int playerId, int points, boolean isPass, boolean isDouble) {
		ChallengeEvent event = new ChallengeEvent();
		event.setPlayer(playerService.get(playerId));
		event.setChallengePoints(points);
		event.setDoubleChallenge(isDouble);
		event.setPass(isPass);

		game.getMatch().setState(game.getMatch().getState().transition(game, event));
		return "Challenge SUCCESS";
	}

	public String setPlayerPassForChallenge(int playerId, int points) {
		return null;
	}

	public String getSection31(int playerId) {
		FetchEvent event = new FetchEvent();
		event.setPlayer(playerService.get(playerId));
		return game.getMatch().getState().getSection31(game, event);
	}
	
	public String getSection22(int playerId) {
		FetchEvent event = new FetchEvent();
		event.setPlayer(playerService.get(playerId));
		return game.getMatch().getState().getSection22(game, event);
	}

	public Object setReDoubleChallenge(int playerId, boolean isPass, boolean isReDouble) {
		ChallengeDoubleEvent event = new ChallengeDoubleEvent();
		event.setPlayer(playerService.get(playerId));
		event.setReDdoubleChallenge(isReDouble);
		event.setPass(isPass);
		game.getMatch().setState(game.getMatch().getState().transition(game, event));
		return "ReDouble SUCCESS";
	}

	public Object setTrump(int playerId, int trump) {
		TrumpSetEvent event = new TrumpSetEvent();
		event.setPlayer(playerService.get(playerId));
		event.setTrump(trump);
		game.getMatch().setState(game.getMatch().getState().transition(game, event));
		return "Trump Set Success";
	}

	public Object openTrump(int playerId) {
		TrumpShowEvent event = new TrumpShowEvent();
		event.setPlayer(playerService.get(playerId));
		game.getMatch().setState(game.getMatch().getState().transition(game, event));
		return "Show Trump Success";
	}

	public Object makeMove(int playerId, int suite, int rank) {
		MakeMoveEvent makeMoveEvent = new MakeMoveEvent();
		makeMoveEvent.setPlayer(playerService.get(playerId));
		makeMoveEvent.setCard(new Card(Suite.values()[suite], Rank.values()[rank]));
		game.getMatch().setState(game.getMatch().getState().transition(game, makeMoveEvent));
		return "Move Succees";
	}

}
