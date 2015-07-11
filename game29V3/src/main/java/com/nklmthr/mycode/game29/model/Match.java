package com.nklmthr.mycode.game29.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nklmthr.mycode.game29.model.Card.Suit;

public class Match {
	private int team_1_CurrentMatchScore;
	private int team_2_CurrentMatchScore;
	private MATCH_PHASE matchPhase;
	private Player dealPlayer;
	private int matchChallengePoints;
	private Player matchChallengePlayer;
	private boolean isMatchChallengeDoubled;
	private boolean isMatchChallengeRedoubled;
	private Suit trump;
	private boolean isTrumOpened;
	private Player trumpOpenPlayer;
	private boolean isTrumpOpenMove;
	private boolean isMoveValidOnTrumpOpen;
	private Player playerToMakeMove;
	private boolean isKingQueenShow;
	private boolean isKingQueenShowSet;
	private Player kingQueenShowPlayer;
	private Set<Player> playersWithJack;
	private List<Table> matchTables;
	private Map<Player, List<Card>> playerCards;
	private boolean abandonMatch;

	public Match() {
		resetMatch();
	}

	public void resetMatch() {
		abandonMatch = false;
		this.team_1_CurrentMatchScore = 0;
		this.team_2_CurrentMatchScore = 0;
		this.matchPhase = MATCH_PHASE.WAIT_FOR_LOGIN_ALL;
		this.dealPlayer = null;
		this.matchChallengePoints = 0;
		this.matchChallengePlayer = null;
		this.isMatchChallengeDoubled = false;
		this.isMatchChallengeRedoubled = false;
		this.trump = null;
		this.isTrumOpened = false;
		this.trumpOpenPlayer = null;
		this.isTrumpOpenMove = false;
		this.isMoveValidOnTrumpOpen = false;
		this.playerToMakeMove = null;
		this.isKingQueenShow = false;
		this.isKingQueenShowSet = false;
		this.kingQueenShowPlayer = null;
		this.playersWithJack = new HashSet<Player>();
		this.matchTables = new ArrayList<Table>();
		this.playerCards = new LinkedHashMap<Player, List<Card>>();
	}

	public int getTeamCurrentMatchScore(int team) {
		if (team == 1) {
			return getTeam_1_CurrentMatchScore();
		} else if (team == 2) {
			return getTeam_2_CurrentMatchScore();
		}
		return -1;
	}

	public boolean isKingQueenShowSet() {
		return isKingQueenShowSet;
	}

	public void setKingQueenShowSet(boolean isKingQueenShowSet) {
		this.isKingQueenShowSet = isKingQueenShowSet;
	}

	public int getTeam_1_CurrentMatchScore() {
		return team_1_CurrentMatchScore;
	}

	public void setTeam_1_CurrentMatchScore(int team_1_CurrentMatchScore) {
		this.team_1_CurrentMatchScore = team_1_CurrentMatchScore;
	}

	public int getTeam_2_CurrentMatchScore() {
		return team_2_CurrentMatchScore;
	}

	public boolean isTrumpOpenMove() {
		return isTrumpOpenMove;
	}

	public void setTrumpOpenMove(boolean isTrumpOpenMove) {
		this.isTrumpOpenMove = isTrumpOpenMove;
	}

	public void setTeam_2_CurrentMatchScore(int team_2_CurrentMatchScore) {
		this.team_2_CurrentMatchScore = team_2_CurrentMatchScore;
	}

	public MATCH_PHASE getMatchPhase() {
		return matchPhase;
	}

	public void setMatchPhase(MATCH_PHASE matchPhase) {
		this.matchPhase = matchPhase;
	}

	public Player getDealPlayer() {
		return dealPlayer;
	}

	public void setDealPlayer(Player dealPlayer) {
		this.dealPlayer = dealPlayer;
	}

	public int getMatchChallengePoints() {
		return matchChallengePoints;
	}

	public void setMatchChallengePoints(int matchChallengePoints) {
		this.matchChallengePoints = matchChallengePoints;
	}

	public Player getMatchChallengePlayer() {
		return matchChallengePlayer;
	}

	public void setMatchChallengePlayer(Player matchChallengePlayer) {
		this.matchChallengePlayer = matchChallengePlayer;
	}

	public boolean isMatchChallengeDoubled() {
		return isMatchChallengeDoubled;
	}

	public void setMatchChallengeDoubled(boolean isMatchChallengeDoubled) {
		this.isMatchChallengeDoubled = isMatchChallengeDoubled;
	}

	public boolean isMatchChallengeRedoubled() {
		return isMatchChallengeRedoubled;
	}

	public void setMatchChallengeRedoubled(boolean isMatchChallengeRedoubled) {
		this.isMatchChallengeRedoubled = isMatchChallengeRedoubled;
	}

	public Suit getTrump() {
		return trump;
	}

	public void setTrump(Suit trump) {
		this.trump = trump;
	}

	public boolean isTrumOpened() {
		return isTrumOpened;
	}

	public void setTrumOpened(boolean isTrumOpened) {
		this.isTrumOpened = isTrumOpened;
	}

	public Player getTrumpOpenPlayer() {
		return trumpOpenPlayer;
	}

	public void setTrumpOpenPlayer(Player trumpOpenPlayer) {
		this.trumpOpenPlayer = trumpOpenPlayer;
	}

	public boolean isMoveValidOnTrumpOpen() {
		return isMoveValidOnTrumpOpen;
	}

	public void setMoveValidOnTrumpOpen(boolean isMoveValidOnTrumpOpen) {
		this.isMoveValidOnTrumpOpen = isMoveValidOnTrumpOpen;
	}

	public Player getPlayerToMakeMove() {
		return playerToMakeMove;
	}

	public void setPlayerToMakeMove(Player playerToMakeMove) {
		this.playerToMakeMove = playerToMakeMove;
	}

	public boolean isKingQueenShow() {
		return isKingQueenShow;
	}

	public void setKingQueenShow(boolean isKingQueenShow) {
		this.isKingQueenShow = isKingQueenShow;
	}

	public Player getKingQueenShowPlayer() {
		return kingQueenShowPlayer;
	}

	public void setKingQueenShowPlayer(Player kingQueenShowPlayer) {
		this.kingQueenShowPlayer = kingQueenShowPlayer;
	}

	public Set<Player> getPlayersWithJack() {
		return playersWithJack;
	}

	public void setPlayersWithJack(Set<Player> playersWithJack) {
		this.playersWithJack = playersWithJack;
	}

	public List<Table> getMatchTables() {
		return matchTables;
	}

	public void setMatchTables(List<Table> matchTables) {
		this.matchTables = matchTables;
	}

	public Map<Player, List<Card>> getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(Map<Player, List<Card>> playerCards) {
		this.playerCards = playerCards;
	}

	public boolean isAbandonMatch() {
		return abandonMatch;
	}

	public void setAbandonMatch(boolean abandonMatch) {
		this.abandonMatch = abandonMatch;
	}

}
