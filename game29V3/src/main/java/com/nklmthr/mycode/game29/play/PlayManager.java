package com.nklmthr.mycode.game29.play;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nklmthr.mycode.game29.exception.PlayerException;
import com.nklmthr.mycode.game29.exception.RefreshException;
import com.nklmthr.mycode.game29.html.HTMLDivRefreshMgr;
import com.nklmthr.mycode.game29.model.Card;
import com.nklmthr.mycode.game29.model.MATCH_PHASE;
import com.nklmthr.mycode.game29.model.Match;
import com.nklmthr.mycode.game29.model.Player;
import com.nklmthr.mycode.game29.model.Table;
import com.nklmthr.mycode.game29.model.Card.Rank;

public final class PlayManager {

	private static PlayManager instance;

	public static PlayManager getInstance() {
		if (instance == null) {
			instance = new PlayManager();
			instance.gamePlayers.add(null);
			instance.gamePlayers.add(null);
			instance.gamePlayers.add(null);
			instance.gamePlayers.add(null);
		}
		return instance;
	}

	private List<Player> gamePlayers = new ArrayList<Player>();
	Match match = new Match();

	private PlayManager() {
	}

	public synchronized void addGamePlayerFromLogin(Player player)
			throws PlayerException {
		if (player.getPlayerId() < 1 || player.getPlayerId() > 4) {
			throw new PlayerException(
					"This request is not a valid Player request. Please retry...");
		} else if (player.getPlayerName() == null
				|| player.getPlayerName().length() < 1) {
			throw new PlayerException(
					"Please Enter a name for yourself in the name field...");
		} else if (gamePlayers.get(player.getPlayerId() - 1) != null) {
			if (gamePlayers.get(player.getPlayerId() - 1).getPlayerName()
					.equalsIgnoreCase(player.getPlayerName())) {
				if (gamePlayers.get(player.getPlayerId() - 1)
						.getPlayerPassword()
						.equalsIgnoreCase(player.getPlayerPassword())) {
				} else {
					throw new PlayerException(
							"User name and password do not match for this match... Please retry");
				}
			} else {
				throw new PlayerException(
						"This player has already registered. Please choose a different player");
			}
		}

		gamePlayers.set(player.getPlayerId() - 1, player);
		boolean check = true;
		Iterator<Player> itr = gamePlayers.iterator();
		while (itr.hasNext()) {
			Player gPlayer = itr.next();
			if (gPlayer == null) {
				check = false;
			}
		}

		if (check && match.getMatchPhase() == MATCH_PHASE.WAIT_FOR_LOGIN_ALL) {
			match.setDealPlayer(gamePlayers.get(0));
			// GameConstants.GAME_PHASE_BEFORE_FIRST_DEAL
			match.setMatchPhase(MATCH_PHASE.WAIT_FOR_DEAL);
		}
	}

	public Player getValidGamePlayer(int playerId, String playerName,
			String playerPassword) throws PlayerException {

		Player player;
		try {
			player = gamePlayers.get(playerId - 1);
			if (!player.getPlayerPassword().equalsIgnoreCase(playerPassword)
					&& player.getPlayerName().equalsIgnoreCase(playerName)) {
				throw new PlayerException("Passwords Do not match..");
			}
		} catch (Exception ex) {
			throw new PlayerException("Failed due to " + ex.getMessage());
		}
		return player;
	}

	public String getHtmlForGame(Player requestingPlayer)
			throws RefreshException {
		try {
			String htmlString = "";
			HTMLDivRefreshMgr htmlMgr = HTMLDivRefreshMgr.getInstance();
			switch (match.getMatchPhase()) {
			// GameConstants.GAME_PHASE_BEFORE_LOGIN:
			case WAIT_FOR_LOGIN_ALL:
				htmlString = htmlMgr.getHtmlForPhaseZero(match, gamePlayers,
						requestingPlayer);
				break;
			// GameConstants.GAME_PHASE_BEFORE_FIRST_DEAL:
			case WAIT_FOR_DEAL:
				htmlString = htmlMgr.getHtmlForPhaseTen(match, gamePlayers,
						requestingPlayer);
				break;
			// GameConstants.GAME_PHASE_BEFORE_FINAL_CHALLENGE:
			case WAIT_FOR_CHALLENGE_OR_DOUBLE:
				htmlString = htmlMgr.getHtmlForPhaseTwenty(match, gamePlayers,
						requestingPlayer);
				break;
			// GameConstants.GAME_PHASE_BEFORE_RE_DOUBLE_CHALLENGE
			case WAIT_FOR_CHALLENGE_REDOUBLE:
				htmlString = htmlMgr.getHtmlForPhaseTwentyFive(match,
						gamePlayers, requestingPlayer);
				break;
			// GameConstants.GAME_PHASE_BEFORE_SET_TRUMP
			case WAIT_FOR_SET_TRUMP:
				htmlString = htmlMgr.getHtmlForPhaseThirty(match, gamePlayers,
						requestingPlayer);
				break;
			// GameConstants.GAME_PHASE_BEFORE_SHOW_TRUMP
			case WAIT_FOR_SHOW_TRUMP:
				htmlString = htmlMgr.getHtmlForPhaseForty(match, gamePlayers,
						requestingPlayer);
				break;
			// GameConstants.GAME_PHASE_BEFORE_MATCH_FINISH
			case WAIT_FOR_MATCH_FINISH:
				htmlString = htmlMgr.getHtmlForPhaseFifty(match, gamePlayers,
						requestingPlayer);
				break;
			}
			return htmlString;
		} catch (Exception ex) {
			throw new RefreshException(ex.getMessage());
		}
	}

	public synchronized String doFirstDeal(Player player)
			throws PlayerException {
		try {
			if (match.getMatchPhase() == MATCH_PHASE.WAIT_FOR_DEAL) {

				Map<Player, List<Card>> playerCards = match.getPlayerCards();
				Card.newDeck();
				for (int i = 0; i < 4; i++) {
					playerCards.put(gamePlayers.get(i), Card.getRandomCards(4));
				}
				System.out.println(playerCards);
				match.setPlayerCards(playerCards);
				match.setMatchPhase(MATCH_PHASE.WAIT_FOR_CHALLENGE_OR_DOUBLE);
				match.setMatchChallengePoints(16);
				match.setMatchChallengePlayer(gamePlayers.get(player
						.getPlayerId() % 4));
				match.setDealPlayer(gamePlayers.get(match.getDealPlayer()
						.getPlayerId() % 4));
				calculatePointLessForPlayers();
				return "First Deal Success";
			} else {
				throw new PlayerException(
						"This action has already been taken...");
			}
		} catch (Exception ex) {
			throw new PlayerException(ex.getMessage());
		}
	}

	public synchronized String doPointsChallenge(Player challengingPlayer,
			int challengePoints) throws PlayerException {
		try {
			if (match.getMatchPhase() == MATCH_PHASE.WAIT_FOR_CHALLENGE_OR_DOUBLE) {
				match.setMatchChallengePoints(challengePoints);
				match.setMatchChallengePlayer(challengingPlayer);
				return "Challenge Success";
			} else {
				throw new PlayerException(
						"This action has already been taken...");
			}
		} catch (Exception ex) {
			throw new PlayerException(ex.getMessage());
		}
	}

	public synchronized String doSetupTrump(Player player, int trumpId)
			throws PlayerException {
		try {
			if (match.getMatchPhase() == MATCH_PHASE.WAIT_FOR_SET_TRUMP) {
				match.setTrump(Card.getSuitFromInt(trumpId));
				// Trump pick player = match challenge player
				match.setMatchChallengePlayer(player);
				// match.setMatchTrumpPicked(true);
				match.setMatchPhase(MATCH_PHASE.WAIT_FOR_SHOW_TRUMP);
				match.setPlayerToMakeMove(player);

				Map<Player, List<Card>> playerCards = match.getPlayerCards();
				for (int i = 0; i < 4; i++) {
					playerCards.get(gamePlayers.get(i)).addAll(
							Card.getRandomCards(4));
				}
				calculateJacksAndTrumpForTeams();

				return "TRUMP SET SUCCESS";
			} else {
				throw new PlayerException(
						"This action has already been taken...");
			}
		} catch (Exception ex) {
			throw new PlayerException(ex.getMessage());
		}
	}

	private void calculatePointLessForPlayers() {
		Map<Player, List<Card>> playerCards = match.getPlayerCards();
		Iterator<Player> pitr = playerCards.keySet().iterator();
		while (pitr.hasNext()) {
			Player pcPlayer = pitr.next();
			List<Card> thisPlayerCards = playerCards.get(pcPlayer);
			Iterator<Card> cardItr = thisPlayerCards.iterator();
			int playerPoints = 0;
			while (cardItr.hasNext()) {
				Card playerCard = cardItr.next();
				playerPoints += playerCard.getPoints();
			}
			if (playerPoints == 0) {
				match.setMatchPhase(MATCH_PHASE.WAIT_FOR_MATCH_FINISH);
				match.setAbandonMatch(true);
			}
		}
	}

	private void calculateJacksAndTrumpForTeams() {
		boolean team1HasJack = false;
		boolean team2hasJack = false;

		boolean team1HasTrump = false;
		boolean team2HasTrump = false;

		Map<Player, List<Card>> playerCards = match.getPlayerCards();
		Iterator<Player> pitr = playerCards.keySet().iterator();
		while (pitr.hasNext()) {
			Player pcPlayer = pitr.next();
			List<Card> thisPlayerCards = playerCards.get(pcPlayer);
			Iterator<Card> cardItr = thisPlayerCards.iterator();
			while (cardItr.hasNext()) {
				Card playerCard = cardItr.next();
				if (playerCard.rank() == Rank.JACK) {
					match.getPlayersWithJack().add(pcPlayer);
				}
				if (pcPlayer.getTeam() == 1) {
					if (playerCard.suit() == match.getTrump()) {
						team1HasTrump = true;
					}
				} else if (pcPlayer.getTeam() == 2) {
					if (playerCard.suit() == match.getTrump()) {
						team2HasTrump = true;
					}
				}
			}
		}

		Iterator<Player> itr = match.getPlayersWithJack().iterator();

		while (itr.hasNext()) {
			Player p = itr.next();
			if (p.getTeam() == 1) {
				team1HasJack = true;
			} else if (p.getTeam() == 2) {
				team2hasJack = true;
			}
		}
		if (!team1HasJack || !team2hasJack || !team1HasTrump || !team2HasTrump) {
			match.setMatchPhase(MATCH_PHASE.WAIT_FOR_MATCH_FINISH);
			match.setAbandonMatch(true);
		}
	}

	public synchronized String finalizeChallenge(
			Player finalizeChallengePlayer, boolean isDouble)
			throws PlayerException {
		try {
			if (match.getMatchPhase() == MATCH_PHASE.WAIT_FOR_CHALLENGE_OR_DOUBLE) {
				match.setMatchChallengeDoubled(isDouble);
				if (match.isMatchChallengeDoubled()) {
					match.setMatchPhase(MATCH_PHASE.WAIT_FOR_CHALLENGE_REDOUBLE);
				} else {
					match.setMatchPhase(MATCH_PHASE.WAIT_FOR_SET_TRUMP);
				}
				return "finalize Challenge Success";
			} else {
				throw new PlayerException(
						"This action has already been taken...");
			}
		} catch (Exception ex) {
			throw new PlayerException(ex.getMessage());
		}
	}

	public String setReDoubleChallenge(Player player, boolean isReDouble)
			throws PlayerException {
		try {
			if ((match.getMatchPhase().compareTo(
					MATCH_PHASE.WAIT_FOR_CHALLENGE_OR_DOUBLE) > 0)
					&& (match.getMatchPhase().compareTo(
							MATCH_PHASE.WAIT_FOR_SET_TRUMP) > 0)) {
				match.setMatchChallengeRedoubled(isReDouble);
				match.setMatchPhase(MATCH_PHASE.WAIT_FOR_SET_TRUMP);
				return "Redouble Challenge Success";
			} else {
				throw new PlayerException(
						"This action has already been taken...");
			}
		} catch (Exception ex) {
			throw new PlayerException(ex.getMessage());
		}
	}

	public synchronized String startNewAction(Player player)
			throws PlayerException {
		match.resetMatch();
		match.setMatchPhase(MATCH_PHASE.WAIT_FOR_DEAL);
		return "New Game Success";
	}

	public synchronized String makeMove(Player player, int suitNum, int rankNum)
			throws PlayerException {
		try {
			// Game29Cards.getInstance().searchGameCard(playingCardId);
			Card card = Card.getCardFromRankSuit(suitNum, rankNum);

			if (match.getPlayerCards().get(player).contains(card)
					&& player.equals(match.getPlayerToMakeMove())) {

				List<Table> matchDeckList = match.getMatchTables();
				int matchDeckListSize = matchDeckList.size();
				Table deckCards = null;
				if (matchDeckListSize != 0) {
					if (matchDeckList.get(matchDeckListSize - 1)
							.getTableCards().size() == 4) {
						deckCards = new Table();
						deckCards
								.setTableCards(new LinkedHashMap<Player, Card>());
						deckCards.setTableId(matchDeckList.size() + 1);
						match.getMatchTables().add(deckCards);
					}
				} else {
					deckCards = new Table();
					deckCards.setTableCards(new LinkedHashMap<Player, Card>());
					deckCards.setTableId(matchDeckList.size() + 1);
					match.getMatchTables().add(deckCards);
				}
				if (matchDeckList.get(matchDeckList.size() - 1).getTableCards()
						.size() > 0) {
					checkIfMoveIsValid(player, card);
					if (match.isTrumpOpenMove()) {
						checkIfMoveValidOnShowTrump(match, player, card);
					}
				}
				match.getPlayerCards().get(player).remove(card);
				match.getMatchTables().get(match.getMatchTables().size() - 1)
						.getTableCards().put(player, card);

				if (match.getMatchTables()
						.get(match.getMatchTables().size() - 1).getTableCards()
						.size() == 4) {
					calculateMoveMatchImplication();
				} else {
					match.setPlayerToMakeMove(gamePlayers.get(player
							.getPlayerId() % 4));
				}
				return "MAKE MOVE SUCCESS";
			} else {
				throw new PlayerException("You have already made a move...");
			}
		} catch (Exception ex) {
			throw new PlayerException(ex.getMessage());
		}
	}

	private void checkIfMoveValidOnShowTrump(Match match, Player player,
			Card card) throws Exception {
		boolean validOnTrumpShown = true;
		Iterator<Card> itr = match.getPlayerCards().get(player).iterator();
		while (itr.hasNext()) {
			Card pCards = itr.next();
			if (match.isTrumpOpenMove()) {
				if (card.suit() != match.getTrump()) {
					if (pCards.suit() == match.getTrump()) {
						validOnTrumpShown = false;
					}
				}
			}
		}
		if (!validOnTrumpShown && match.isTrumpOpenMove()) {
			throw new Exception(
					"You have a card of Trump Suite.. Please move that..");
		} else {
			match.setTrumpOpenMove(false);
		}
	}

	private void checkIfMoveIsValid(Player player, Card card) throws Exception {
		boolean valid = true;

		Object cardArr[] = new Card[3];
		Map<Player, Card> deck = match.getMatchTables()
				.get(match.getMatchTables().size() - 1).getTableCards();
		cardArr = deck.values().toArray();
		Card cardArr0 = (Card) cardArr[0];
		Iterator<Card> itr = match.getPlayerCards().get(player).iterator();
		while (itr.hasNext()) {
			Card pCards = itr.next();
			if ((cardArr0.suit() == pCards.suit() && cardArr0.suit() != card
					.suit())) {
				valid = false;
			}
		}
		if (!valid) {
			throw new Exception(
					"You have a card of this suite... Please move that card...");
		}

	}

	public synchronized String setTrumpShown(Player requestingPlayer)
			throws PlayerException {
		String message = "";
		try {
			// GameConstants.GAME_PHASE_BEFORE_SHOW_TRUMP
			if (match.getMatchPhase() == MATCH_PHASE.WAIT_FOR_SHOW_TRUMP) {

				boolean canOpenTrump = false;

				boolean checkFirstCardOfDeck = false;
				Player firstDeckMoveplayer = null;
				Card firstDeckMoveCard = null;

				int index = match.getMatchTables().size() - 1;

				if (index >= 0) {
					Table deck = match.getMatchTables().get(index);
					if (deck.getTableCards().size() > 3 && index < 8) {
						throw new PlayerException(
								"There is no point in opening trump right now..");
					}
					Iterator<Player> deckItr = deck.getTableCards().keySet()
							.iterator();
					while (deckItr.hasNext()) {
						if (!checkFirstCardOfDeck) {
							firstDeckMoveplayer = deckItr.next();
							firstDeckMoveCard = deck.getTableCards().get(
									firstDeckMoveplayer);
							checkFirstCardOfDeck = true;
						} else {
							deckItr.next();
						}
					}
				}
				if (firstDeckMoveCard != null) {
					List<Card> cards = match
							.getPlayerCards()
							.get(gamePlayers.get(requestingPlayer.getPlayerId() - 1));
					Iterator<Card> itr = cards.iterator();
					while (itr.hasNext()) {
						Card card = itr.next();

						if (card.suit() == firstDeckMoveCard.suit()) {
							canOpenTrump = false;
							break;
						} else {
							canOpenTrump = true;
						}
					}
				}
				if (canOpenTrump) {
					match.setTrumOpened(true);
					match.setTrumpOpenMove(true);
					match.setTrumpOpenPlayer(requestingPlayer);
					// GameConstants.GAME_PHASE_BEFORE_MATCH_FINISH
					match.setMatchPhase(MATCH_PHASE.WAIT_FOR_MATCH_FINISH);
					message = "OPEN TRUMP SUCCESS";
				} else {
					message = "You can not open trump at this time...";
				}
			} else {
				message = "You have already made this action...";
				throw new PlayerException(message);
			}
		} catch (Exception ex) {
			throw new PlayerException(ex.getMessage());
		}
		return message;
	}

	private void calculateMoveMatchImplication() {
		Table deck = match.getMatchTables().get(
				match.getMatchTables().size() - 1);
		Map<Player, Card> map = deck.getTableCards();

		Iterator<Player> itr = map.keySet().iterator();
		Player bestPlayer = null;
		Card bestDeckCard = null;
		while (itr.hasNext()) {
			Player player = itr.next();
			Card card = map.get(player);
			if (bestPlayer == null && bestDeckCard == null) {
				bestPlayer = player;
				bestDeckCard = card;
			} else {
				if (match.isTrumOpened()) {
					if (card.suit() == match.getTrump()) {
						if (bestDeckCard.suit() == match.getTrump()) {
							if (card.getPriority() > bestDeckCard.getPriority()) {
								bestPlayer = player;
								bestDeckCard = card;
							}
						} else {
							bestPlayer = player;
							bestDeckCard = card;
						}
					} else {
						if (card.suit() == bestDeckCard.suit()) {
							if (card.getPriority() > bestDeckCard.getPriority()) {
								bestPlayer = player;
								bestDeckCard = card;
							}
						}
					}
				} else {
					if (card.suit() == bestDeckCard.suit()) {
						if (card.getPriority() > bestDeckCard.getPriority()) {
							bestPlayer = player;
							bestDeckCard = card;
						}
					}
				}
			}
		}
		deck.setBestPlayer(bestPlayer);
		deck.setBestTableCard(bestDeckCard);
		itr = map.keySet().iterator();
		while (itr.hasNext()) {
			Player player = itr.next();
			Card card = map.get(player);
			if (bestPlayer.getTeam() == 1) {
				match.setTeam_1_CurrentMatchScore(match
						.getTeam_1_CurrentMatchScore() + card.getPoints());
			} else if (bestPlayer.getTeam() == 2) {
				match.setTeam_2_CurrentMatchScore(match
						.getTeam_1_CurrentMatchScore() + card.getPoints());
			}
		}
		if (match.isTrumOpened() && !match.isKingQueenShow()) {
			Map<Player, List<Card>> playerCards = match.getPlayerCards();
			Iterator<Player> pitr = playerCards.keySet().iterator();
			while (pitr.hasNext()) {
				Player pcPlayer = pitr.next();
				List<Card> thisPlayerCards = playerCards.get(pcPlayer);
				if (match.getTeamCurrentMatchScore(pcPlayer.getTeam()) > 0) {
					/*
					 * if (thisPlayerCards .contains(Game29Cards .getInstance()
					 * .searchGameCardBySuiteDenom( match.getMatchTrumpId(),
					 * GameConstants
					 * .getCardDenominationInt(GameConstants.CARD_NAME_KING)))
					 * && thisPlayerCards .contains(Game29Cards .getInstance()
					 * .searchGameCardBySuiteDenom( match.getMatchTrumpId(),
					 * GameConstants
					 * .getCardDenominationInt(GameConstants.CARD_NAME_QUEEN))))
					 */
					if (thisPlayerCards.contains(Card.getCardFromRankSuit(
							Rank.KING.ordinal(), match.getTrump().ordinal()))
							&& thisPlayerCards.contains(Card
									.getCardFromRankSuit(Rank.QUEEN.ordinal(),
											match.getTrump().ordinal()))) {
						match.setKingQueenShow(true);
						match.setKingQueenShowSet(true);
						match.setKingQueenShowPlayer(pcPlayer);
					}
				}
			}
		}
		/*
		 * Do changes in challenge points if king queen is elligible shown.
		 */
		if (match.isKingQueenShowSet()) {
			if (match.getMatchChallengePlayer().getTeam() == match
					.getKingQueenShowPlayer().getTeam()) {
				int targetScore = match.getMatchChallengePoints();
				if (targetScore > 20) {
					targetScore -= 4;
				} else {
					targetScore = 16;
				}
				match.setMatchChallengePoints(targetScore);
			} else {
				if (match.getMatchChallengePoints() <= 25) {
					match.setMatchChallengePoints(match
							.getMatchChallengePoints() + 4);
				} else {
					match.setMatchChallengePoints(29);
				}
			}
			match.setKingQueenShowSet(false);
		}

		/*
		 * if this is the last move of the match
		 */
		if (match.getMatchTables().size() == 8
				&& match.getMatchTables()
						.get(match.getMatchTables().size() - 1).getTableCards()
						.size() == 4) {

			/*
			 * Add one point to the team making the last hand.
			 */
			if (gamePlayers.get(bestPlayer.getPlayerId() - 1).getTeam() == 1) {
				match.setTeam_1_CurrentMatchScore(match
						.getTeamCurrentMatchScore(1) + 1);
			} else {
				match.setTeam_2_CurrentMatchScore(match
						.getTeam_2_CurrentMatchScore() + 1);
			}
			/*
			 * Calculate if some one is elligible for showing king queen...
			 * First check if trump is opened and then to avoid relooping again
			 * and again into this condiotion check if we have not already
			 * calculated this.
			 */
			/*
			 * Calculate who one the match and set the game score If the
			 * challenge points are greater than match challenger player's team
			 * points then increase game score by one else decrease the same to
			 * negative by one.
			 */

			/*
			 * if (match.getTeamMatchPoints(match.getMatchBestChallengePlayer()
			 * .getTeam()) < match.getMatchChallengePoints()) { // Match Loose
			 * if (match.getTeamMatchPoints(match
			 * .getMatchBestChallengePlayer().getTeam()) < Math
			 * .ceil(match.getMatchChallengePoints() / 2.0)) { // Match Loose
			 * less than half if (match.isMatchDoubleScore()) { // Match Loose
			 * less than half doubled if (match.isMatchReDoubleScore()) { //
			 * Match Loose less than half re-doubled
			 * match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(),
			 * match.getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) - 6); } else { // Match Loose Less than half Not
			 * redoubled (doubled // only) match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(),
			 * match.getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) - 4); }
			 * 
			 * } else { // Match Loose less than half Not doubled
			 * match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(), match
			 * .getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) - 2); } } else { // Match Loose not less than half if
			 * (match.isMatchDoubleScore()) { // Match Loose not less than half
			 * doubled if (match.isMatchReDoubleScore()) { // Match Loose not
			 * less than half redoubled match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(),
			 * match.getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) - 4); } else { // Match Loose not less than half not
			 * redoubled // (doubled) match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(),
			 * match.getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) - 2); } } else { // Match Loose not less than half
			 * not doubled match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(), match
			 * .getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) - 1); } } } else { if (match.getTeamMatchPoints(match
			 * .getMatchBestChallengePlayer().getTeam()) == 29) { // Match Win
			 * All Hands if (match.isMatchDoubleScore()) { // Match Win All
			 * Hands doubled if (match.isMatchReDoubleScore()) { // Match Win
			 * All Hands redoubled match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(),
			 * match.getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) + 6); } else { // Match Win All Hands not redoubled
			 * (doubled) match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(),
			 * match.getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) + 4); } } else { // Match Win All Hands not doubled
			 * match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(), match
			 * .getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) + 2); } } else { // Match Win Not all Hands if
			 * (match.isMatchDoubleScore()) { // Match Win Not all Hands doubled
			 * if (match.isMatchReDoubleScore()) { // Match Win Not all Hands
			 * redoubled match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(),
			 * match.getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) + 4); } else { // Match Win Not all Hands not
			 * redoubled (doubled) match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(),
			 * match.getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) + 2); } } else { // Match Win Not all Hands not
			 * doubled match.setTeamGamePoints(match
			 * .getMatchBestChallengePlayer().getTeam(), match
			 * .getTeamGamePoints(match .getMatchBestChallengePlayer()
			 * .getTeam()) + 1); } } } } logger.log(Level.TRACE,
			 * "setting Next turn player = " + bestPlayer);
			 */
		}
		match.setPlayerToMakeMove(bestPlayer);
	}

	/*
	 * private List<PlayingCard> getFourRandomCards() { List<PlayingCard>
	 * fourRandomCards = new ArrayList<PlayingCard>(); List<PlayingCard>
	 * unassignedCards = Game29Cards.getInstance() .getUnAssignedCardsList();
	 * Collections.shuffle(unassignedCards, randGen); for (int i = 3; i >= 0;
	 * i--) { PlayingCard card = unassignedCards.get(i);
	 * Game29Cards.getInstance().assignCard(card); fourRandomCards.add(card); }
	 * return fourRandomCards; }
	 */

}
