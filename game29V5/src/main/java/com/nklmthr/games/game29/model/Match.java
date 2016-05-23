package com.nklmthr.games.game29.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Match {
	private State state;
	private List<Card> cards;
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	private Player dealPlayer;
	private Player nextMovePlayer;

	private boolean pointLessGame;
	private boolean jackLessGame;
	private boolean trumpLessGame;

	private List<Table> tables = new ArrayList<Table>();

	private Set<Player> playersWithJack;
	private Map<Player, List<Card>> playerCards;

	private Challenge challenge = new Challenge();

	private Suite challengeTrumpSuite;

	private boolean challengeDoubled;
	private boolean challengeRedoubled;

	private Player trumpSetPlayer;
	private Player trumpShowPlayer;

	private boolean KQshown;
	private Player KQShowPlayer;

	private int team1Points;
	private int team2Points;

	public Player getDealPlayer() {
		return dealPlayer;
	}

	public void setDealPlayer(Player dealPlayer) {
		this.dealPlayer = dealPlayer;
	}

	public Player getNextMovePlayer() {
		return nextMovePlayer;
	}

	public void setNextMovePlayer(Player nextMovePlayer) {
		this.nextMovePlayer = nextMovePlayer;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public Set<Player> getPlayersWithJack() {
		return playersWithJack;
	}

	public void setPlayersWithJack(Set<Player> playersWithJack) {
		this.playersWithJack = playersWithJack;
	}

	public Map<Player, List<Card>> getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(Map<Player, List<Card>> playerCards) {
		this.playerCards = playerCards;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public Suite getChallengeTrumpSuite() {
		return challengeTrumpSuite;
	}

	public void setChallengeTrumpSuite(Suite challengeTrumpSuite) {
		this.challengeTrumpSuite = challengeTrumpSuite;
	}

	public boolean isChallengeDoubled() {
		return challengeDoubled;
	}

	public void setChallengeDoubled(boolean challengeDoubled) {
		this.challengeDoubled = challengeDoubled;
	}

	public boolean isChallengeRedoubled() {
		return challengeRedoubled;
	}

	public void setChallengeRedoubled(boolean challengeRedoubled) {
		this.challengeRedoubled = challengeRedoubled;
	}

	public Player getTrumpSetPlayer() {
		return trumpSetPlayer;
	}

	public void setTrumpSetPlayer(Player trumpSetPlayer) {
		this.trumpSetPlayer = trumpSetPlayer;
	}

	public Player getTrumpShowPlayer() {
		return trumpShowPlayer;
	}

	public void setTrumpShowPlayer(Player trumpShowPlayer) {
		this.trumpShowPlayer = trumpShowPlayer;
	}

	public boolean isKQshown() {
		return KQshown;
	}

	public void setKQshown(boolean kQshown) {
		KQshown = kQshown;
	}

	public Player getKQShowPlayer() {
		return KQShowPlayer;
	}

	public void setKQShowPlayer(Player kQShowPlayer) {
		KQShowPlayer = kQShowPlayer;
	}

	public int getTeam1Points() {
		return team1Points;
	}

	public int getPoints(int team) {
		if (team % 2 == 0) {
			return team2Points;
		} else {
			return team1Points;
		}
	}

	public void setPoints(int team, int score) {
		if (team % 2 == 0) {
			setTeam2Points(score);
		} else {
			setTeam1Points(score);
		}
	}

	public void setTeam1Points(int team1Points) {
		this.team1Points = team1Points;
	}

	public int getTeam2Points() {
		return team2Points;
	}

	public void setTeam2Points(int team2Points) {
		this.team2Points = team2Points;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isPointLessGame() {
		return pointLessGame;
	}

	public void setPointLessGame(boolean pointLessGame) {
		this.pointLessGame = pointLessGame;
	}

	public boolean isJackLessGame() {
		return jackLessGame;
	}

	public void setJackLessGame(boolean jackLessGame) {
		this.jackLessGame = jackLessGame;
	}

	public boolean isTrumpLessGame() {
		return trumpLessGame;
	}

	public void setTrumpLessGame(boolean trumpLessGame) {
		this.trumpLessGame = trumpLessGame;
	}

}
