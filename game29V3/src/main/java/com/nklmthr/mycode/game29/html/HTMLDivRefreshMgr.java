package com.nklmthr.mycode.game29.html;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.nklmthr.mycode.game29.exception.RefreshException;
import com.nklmthr.mycode.game29.model.Card;
import com.nklmthr.mycode.game29.model.Match;
import com.nklmthr.mycode.game29.model.Player;
import com.nklmthr.mycode.game29.model.Table;
import com.nklmthr.mycode.game29.model.Card.Rank;
import com.nklmthr.mycode.game29.model.Card.Suit;
import com.nklmthr.mycode.game29.util.HTMLDOMHelper;
import com.nklmthr.mycode.game29.util.XMLHelper;

public final class HTMLDivRefreshMgr {
	private static HTMLDivRefreshMgr instance;

	private HTMLDivRefreshMgr() {

	}

	public static HTMLDivRefreshMgr getInstance() {
		if (instance == null) {
			instance = new HTMLDivRefreshMgr();
		}
		return instance;
	}

	public String getHtmlForPhaseZero(Match match, List<Player> gamePlayers,
			Player requestingPlayer) throws RefreshException {
		try {
			Document doc = XMLHelper.createDomDocument();

			HTMLDOMHelper.addHTMLTable(doc, "2", "100%", "SILVER", 3, 3);
			setGameScoreCard(doc, match);
			Node cellDataNode = doc.getElementById("td_12");
			cellDataNode.appendChild(HTMLDOMHelper.addHtmlImage(doc, "banner",
					"images/jass.gif", "banner"));

			cellDataNode = doc.getElementById("td_21");
			cellDataNode.appendChild(HTMLDOMHelper.addHtmlImage(doc, "banner",
					"images/jass.gif", "banner"));

			cellDataNode = doc.getElementById("td_23");
			cellDataNode.appendChild(HTMLDOMHelper.addHtmlImage(doc, "banner",
					"images/jass.gif", "banner"));

			cellDataNode = doc.getElementById("td_32");
			cellDataNode.appendChild(HTMLDOMHelper.addHtmlImage(doc, "banner",
					"images/jass.gif", "banner"));

			HTMLDOMHelper.addTextNode(doc, null, "td_22",
					"Please wait while other players join the game...");

			return XMLHelper.getStringFromDocument(doc);
		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}
	}

	public String getHtmlForPhaseTen(Match match, List<Player> gamePlayers,
			Player requestingPlayer) throws RefreshException {
		try {
			Document doc = XMLHelper.createDomDocument();

			HTMLDOMHelper.addHTMLTable(doc, "2", "100%", "SILVER", 3, 3);
			setGameScoreCard(doc, match);

			Node cellDataNode = doc.getElementById("td_12");
			cellDataNode.appendChild(HTMLDOMHelper.addHtmlImage(doc, "banner",
					"images/jass.gif", "banner"));

			cellDataNode = doc.getElementById("td_21");
			cellDataNode.appendChild(HTMLDOMHelper.addHtmlImage(doc, "banner",
					"images/jass.gif", "banner"));

			cellDataNode = doc.getElementById("td_23");
			cellDataNode.appendChild(HTMLDOMHelper.addHtmlImage(doc, "banner",
					"images/jass.gif", "banner"));

			cellDataNode = doc.getElementById("td_32");
			cellDataNode.appendChild(HTMLDOMHelper.addHtmlImage(doc, "banner",
					"images/jass.gif", "banner"));
			if (requestingPlayer.equals(match.getDealPlayer())) {
				Node node = doc.getElementById("td_22");
				node.appendChild(HTMLDOMHelper.addHTMLhRef(doc, HTMLDOMHelper
						.addHtmlImage(doc, "StartDeal", "images/dealstart.jpg",
								"Start Deal"), "javascript:doFirstDeal()"));

			} else {
				HTMLDOMHelper.addTextNode(doc, null, "td_22",
						"Please wait for player "
								+ match.getDealPlayer().getPlayerName()
								+ " to deal...");
			}
			return XMLHelper.getStringFromDocument(doc);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RefreshException(ex, ex.getMessage());
		}
	}

	public String getHtmlForPhaseTwenty(Match match, List<Player> gamePlayers,
			Player requestingPlayer) throws RefreshException {
		try {
			Document doc = XMLHelper.createDomDocument();

			HTMLDOMHelper.addHTMLTable(doc, "2", "100%", "SILVER", 3, 3);
			setGameScoreCard(doc, match);
			// does not have turn
			listDisplayedPlayerCards(doc, match, gamePlayers, requestingPlayer,
					false);
			listAllNonDisplayedPlayerCards(doc, match, gamePlayers,
					requestingPlayer, true);
			addTrumpChallengeWithPoints(doc, match, gamePlayers,
					requestingPlayer);
			return XMLHelper.getStringFromDocument(doc);
		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}
	}

	public String getHtmlForPhaseTwentyFive(Match match,
			List<Player> gamePlayers, Player requestingPlayer)
			throws RefreshException {
		try {
			Document doc = XMLHelper.createDomDocument();

			HTMLDOMHelper.addHTMLTable(doc, "2", "100%", "SILVER", 3, 3);
			setGameScoreCard(doc, match);
			// does not have turn
			listDisplayedPlayerCards(doc, match, gamePlayers, requestingPlayer,
					false);
			listAllNonDisplayedPlayerCards(doc, match, gamePlayers,
					requestingPlayer, true);
			addReDoubleChallenge(doc, match, gamePlayers, requestingPlayer);
			return XMLHelper.getStringFromDocument(doc);

		} catch (Exception ex) {
			throw new RefreshException(ex,

			ex.getMessage());
		}
	}

	public String getHtmlForPhaseThirty(Match match, List<Player> gamePlayers,
			Player requestingPlayer) throws RefreshException {
		try {
			Document doc = XMLHelper.createDomDocument();

			HTMLDOMHelper.addHTMLTable(doc, "2", "100%", "SILVER", 3, 3);
			setGameScoreCard(doc, match);
			// does not have turn
			listDisplayedPlayerCards(doc, match, gamePlayers, requestingPlayer,
					false);
			listAllNonDisplayedPlayerCards(doc, match, gamePlayers,
					requestingPlayer, true);
			// MatchBestChallengePlayer()
			if (requestingPlayer.equals(match.getMatchChallengePlayer())) {
				addTrumpForPlayer(doc, match, gamePlayers, requestingPlayer);
			} else {
				HTMLDOMHelper.addTextNode(doc, null, "td_31",
						"Waiting for Trump to be set by "
								+ match.getMatchChallengePlayer()
										.getPlayerName());
			}
			return XMLHelper.getStringFromDocument(doc);
		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}
	}

	public String getHtmlForPhaseForty(Match match, List<Player> gamePlayers,
			Player requestingPlayer) throws RefreshException {
		try {
			Document doc = XMLHelper.createDomDocument();

			HTMLDOMHelper.addHTMLTable(doc, "2", "100%", "SILVER", 3, 3);
			setGameScoreCard(doc, match);
			// MatchPlayerWhoHasTurn()
			if (match.getPlayerToMakeMove().equals(requestingPlayer)) {
				// true for has turn
				listDisplayedPlayerCards(doc, match, gamePlayers,
						requestingPlayer, true);
			} else {
				// does not have turn
				listDisplayedPlayerCards(doc, match, gamePlayers,
						requestingPlayer, false);
			}
			showMatchStatstics(doc, match, gamePlayers);
			listAllNonDisplayedPlayerCards(doc, match, gamePlayers,
					requestingPlayer, true);

			showTrumpStats(doc, match, requestingPlayer);
			listDeckCards(doc, match, gamePlayers, requestingPlayer);
			return XMLHelper.getStringFromDocument(doc);
		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}
	}

	public String getHtmlForPhaseFifty(Match match, List<Player> gamePlayers,
			Player requestingPlayer) throws RefreshException {
		try {
			Document doc = XMLHelper.createDomDocument();

			HTMLDOMHelper.addHTMLTable(doc, "2", "100%", "SILVER", 3, 3);
			setGameScoreCard(doc, match);
			if (!match.isAbandonMatch()) {
				if (match.getPlayerToMakeMove().equals(requestingPlayer)) {
					// true for has turn
					listDisplayedPlayerCards(doc, match, gamePlayers,
							requestingPlayer, true);
				} else {
					// does not have turn
					listDisplayedPlayerCards(doc, match, gamePlayers,
							requestingPlayer, false);
				}
				showTrumpStats(doc, match, requestingPlayer);
				showMatchStatstics(doc, match, gamePlayers);
				listAllNonDisplayedPlayerCards(doc, match, gamePlayers,
						requestingPlayer, true);
				if (match.getMatchTables().size() == 8
						&& match.getMatchTables()
								.get(match.getMatchTables().size() - 1)
								.getTableCards().size() == 4) {
					listAllDeckCards(doc, match, gamePlayers, requestingPlayer);
					if (requestingPlayer.equals(match.getDealPlayer())) {
						HTMLDOMHelper.addHTMLSubmitButton(doc, "td_32",
								"Start New Match", "Start New Match",
								"javascript:newGame();");
					}

				} else {
					listDeckCards(doc, match, gamePlayers, requestingPlayer);
				}
			} else {
				listDisplayedPlayerCards(doc, match, gamePlayers,
						requestingPlayer, true);
				showTrumpStats(doc, match, requestingPlayer);
				listAllNonDisplayedPlayerCards(doc, match, gamePlayers,
						requestingPlayer, false);
				if (requestingPlayer.equals(match.getDealPlayer())) {
					HTMLDOMHelper.addHTMLSubmitButton(doc, "td_32",
							"Start New Match", "Start New Match",
							"javascript:newGame();");
				}
			}

			return XMLHelper.getStringFromDocument(doc);
		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}
	}

	private void listAllDeckCards(Document doc, Match match,
			List<Player> gamePlayers, Player requestingPlayer)
			throws RefreshException {
		try {
			Element node = doc.getElementById("td_22");
			for (int i = 0; i < match.getMatchTables().size(); i++) {
				Table deckCards = match.getMatchTables().get(i);
				Iterator<Player> itr = deckCards.getTableCards().keySet()
						.iterator();
				while (itr.hasNext()) {
					Player player = itr.next();
					Card card = deckCards.getTableCards().get(player);
					HTMLDOMHelper.addTextNode(doc, node, "td_22",
							player.getPlayerName());
					node.appendChild(card.toHTMLNode(doc));
				}
				HTMLDOMHelper.addTextNode(doc, node, "td_22", "Set winner = "
						+ deckCards.getBestPlayer().getPlayerName());

				HTMLDOMHelper.addLineBreak(doc, node);
			}
		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}
	}

	private void showTrumpStats(Document doc, Match match,
			Player requestingPlayer) throws RefreshException {
		try {
			Element node = null;
			node = doc.getElementById("td_31");
			HTMLDOMHelper.addLineBreak(doc, node);
			if (match.getMatchChallengePlayer() != null) {
				HTMLDOMHelper.addTextNode(doc, node, "td_31",
						"Trump Set Player: ");
				HTMLDOMHelper.addTextNode(doc, node, "td_31", match
						.getMatchChallengePlayer().getPlayerName());
			}
			HTMLDOMHelper.addLineBreak(doc, node);

			if (match.getMatchChallengePlayer() != null
					&& requestingPlayer.equals(match.getMatchChallengePlayer())
					&& !match.isTrumOpened()) {
				HTMLDOMHelper.addTextNode(doc, node, "td_31",
						"The trump Set by you is:");
				if (match.getTrump() != null) {
					node.appendChild(HTMLDOMHelper.addHtmlImage(doc, match
							.getTrump().toString(),
							"images/" + match.getTrump() + ".jpg", match
									.getTrump().toString()));
				}

				/*
				 * node.appendChild(HTMLDOMHelper.addHtmlImage(doc,
				 * GameConstants .getCardSuiteStr(match.getMatchTrumpId()),
				 * "images/" + GameConstants
				 * .getCardSuiteStr(match.getMatchTrumpId()) + ".jpg",
				 * GameConstants.getCardSuiteStr(match .getMatchTrumpId())));
				 */
			}
			HTMLDOMHelper.addLineBreak(doc, node);

			if (match.getTrumpOpenPlayer() != null) {
				HTMLDOMHelper.addTextNode(doc, node, "td_31",
						"Trump opened by Player: ");
				HTMLDOMHelper.addTextNode(doc, node, "td_31", match
						.getTrumpOpenPlayer().getPlayerName());

				HTMLDOMHelper.addLineBreak(doc, node);
				node.appendChild(HTMLDOMHelper.addHtmlImage(doc, match
						.getTrump().toString(), "images/"
						+ match.getTrump().toString() + ".jpg", match
						.getTrump().toString()));

			}
			HTMLDOMHelper.addLineBreak(doc, node);

			if (!match.isTrumOpened()
					&& requestingPlayer.equals(match.getPlayerToMakeMove())) {
				HTMLDOMHelper.addHTMLSubmitButton(doc, "td_31", "Show Trump",
						"Show Trump", "javascript:makeTrumpShown()");
			}

			HTMLDOMHelper.addLineBreak(doc, node);

			HTMLDOMHelper.addTextNode(doc, node, "td_31",
					"Team 1 :" + match.getTeamCurrentMatchScore(1));

			HTMLDOMHelper.addLineBreak(doc, node);
			HTMLDOMHelper.addTextNode(doc, node, "td_31",
					"Team 2 :" + match.getTeamCurrentMatchScore(2));
		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}

	}

	private void showMatchStatstics(Document doc, Match match,
			List<Player> gamePlayers) throws RefreshException {
		try {
			Element node = doc.getElementById("td_33");
			if (match.isMatchChallengeDoubled()) {
				HTMLDOMHelper.addTextNode(doc, node, "td_33",
						"Match Challenge Doubled ");
				HTMLDOMHelper.addLineBreak(doc, node);
			}
			HTMLDOMHelper.addLineBreak(doc, node);
			if (match.isMatchChallengeRedoubled()) {
				HTMLDOMHelper.addTextNode(doc, node, "td_33",
						"Match Challenge Re - Doubled ");
				HTMLDOMHelper.addLineBreak(doc, node);
				HTMLDOMHelper.addLineBreak(doc, node);
			}

			HTMLDOMHelper.addTextNode(doc, node, "td_33",
					"Players Having JACK: ");
			if (match.getPlayersWithJack() != null) {
				Iterator<Player> itr = match.getPlayersWithJack().iterator();
				while (itr.hasNext()) {
					Player p = itr.next();
					HTMLDOMHelper.addTextNode(doc, node, "td_33",
							p.getPlayerName() + ", ");
				}
			}
			if (match.isKingQueenShow()) {
				HTMLDOMHelper.addLineBreak(doc, node);
				HTMLDOMHelper.addLineBreak(doc, node);
				HTMLDOMHelper.addTextNode(doc, node, "td_33",
						"King Queen Shown by Player: ");
				HTMLDOMHelper.addTextNode(doc, node, "td_33", match
						.getKingQueenShowPlayer().getPlayerName());

			}
			HTMLDOMHelper.addLineBreak(doc, node);

			HTMLDOMHelper.addLineBreak(doc, node);
			HTMLDOMHelper.addTextNode(
					doc,
					node,
					"td_33",
					"Target Score for Match: "
							+ match.getMatchChallengePoints());

			HTMLDOMHelper.addLineBreak(doc, node);
			HTMLDOMHelper.addTextNode(doc, node, "td_33",
					"Target Score set by Player [ "
							+ match.getMatchChallengePlayer().getPlayerName()
							+ " ] of team "
							+ match.getMatchChallengePlayer().getTeam());

			HTMLDOMHelper.addLineBreak(doc, node);
			HTMLDOMHelper.addTextNode(doc, node, "td_33", "DEAL Player [ "
					+ match.getDealPlayer().getPlayerName() + " ]");

		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}

	}

	private void listDeckCards(Document doc, Match match,
			List<Player> gamePlayers, Player requestingPlayer)
			throws RefreshException {
		try {
			Element node = doc.getElementById("td_22");
			if (requestingPlayer.equals(match.getPlayerToMakeMove())) {
				HTMLDOMHelper.addTextNode(doc, node, "td_22",
						"Please make a move... ");

			} else {
				HTMLDOMHelper.addTextNode(doc, node, "td_22", "Waiting for "
						+ match.getPlayerToMakeMove().getPlayerName()
						+ " to make a move...");

			}
			HTMLDOMHelper.addLineBreak(doc, node);
			int index = match.getMatchTables().size() - 1;
			if (index >= 0) {
				Table deck = match.getMatchTables().get(index);
				Iterator<Player> itr = deck.getTableCards().keySet().iterator();
				while (itr.hasNext()) {
					Player player = itr.next();
					Card card = deck.getTableCards().get(player);
					HTMLDOMHelper.addTextNode(doc, node, "td_22",
							player.getPlayerName());
					node.appendChild(card.toHTMLNode(doc));
				}
			}
		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}
	}

	private void addTrumpForPlayer(Document doc, Match match,
			List<Player> gamePlayers, Player requestingPlayer)
			throws RefreshException {
		Element node = doc.getElementById("td_31");
		HTMLDOMHelper.addTextNode(doc, node, "td_31", "You have to score "
				+ match.getMatchChallengePoints() + ". Please select trump...");

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addLineBreak(doc, node);
		for (Suit suit : Suit.values()) {
			/*
			 * for (int i = 1; i <= 4; i++) {
			 * node.appendChild(HTMLDOMHelper.addHTMLhRef( doc,
			 * HTMLDOMHelper.addHtmlImage(doc, GameConstants.getCardSuiteStr(i),
			 * "images/" + GameConstants.getCardSuiteStr(i) + ".jpg",
			 * GameConstants.getCardSuiteStr(i)), "javascript:doSetTrump(" + i +
			 * ")"));
			 */
			node.appendChild(HTMLDOMHelper.addHTMLhRef(
					doc,
					HTMLDOMHelper.addHtmlImage(doc, suit.toString(), "images/"
							+ suit.toString() + ".jpg", suit.toString()),
					"javascript:doSetTrump(" + suit.ordinal() + 1 + ")"));

			HTMLDOMHelper.addTextNode(doc, node, "td_31", "      ");
			if (suit.ordinal() % 2 == 0) {
				HTMLDOMHelper.addLineBreak(doc, node);
			}
		}

	}

	private void listAllNonDisplayedPlayerCards(Document doc, Match match,
			List<Player> gamePlayers, Player requestingPlayer, boolean isHidden)
			throws RefreshException {
		Element node;
		List<Card> cards;
		Iterator<Card> itr;
		Player player;

		node = doc.getElementById("td_23");
		player = gamePlayers.get(requestingPlayer.getPlayerId() % 4);
		HTMLDOMHelper.addTextNode(doc, node, "td_23",
				"Player " + player.getPlayerId());

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addTextNode(doc, node, "td_23",
				"Player Name: " + player.getPlayerName());

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addLineBreak(doc, node);
		cards = match.getPlayerCards().get(player);
		itr = cards.iterator();
		while (itr.hasNext()) {
			if (isHidden) {
				node.appendChild(itr.next().toHiddenHTMLString(doc));
			} else {
				node.appendChild(itr.next().toHTMLNode(doc));
			}
		}

		node = doc.getElementById("td_12");
		player = gamePlayers.get((requestingPlayer.getPlayerId() + 1) % 4);
		HTMLDOMHelper.addTextNode(doc, node, "td_23",
				"Player " + player.getPlayerId());

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addTextNode(doc, node, "td_23",
				"Player Name: " + player.getPlayerName());

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addLineBreak(doc, node);
		cards = match.getPlayerCards().get(player);
		itr = cards.iterator();
		while (itr.hasNext()) {
			if (isHidden) {
				node.appendChild(itr.next().toHiddenHTMLString(doc));
			} else {
				node.appendChild(itr.next().toHTMLNode(doc));
			}
		}

		node = doc.getElementById("td_21");
		player = gamePlayers.get((requestingPlayer.getPlayerId() + 2) % 4);
		HTMLDOMHelper.addTextNode(doc, node, "td_23",
				"Player " + player.getPlayerId());

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addTextNode(doc, node, "td_23",
				"Player Name: " + player.getPlayerName());

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addLineBreak(doc, node);
		cards = match.getPlayerCards().get(player);
		itr = cards.iterator();
		while (itr.hasNext()) {
			if (isHidden) {
				node.appendChild(itr.next().toHiddenHTMLString(doc));
			} else {
				node.appendChild(itr.next().toHTMLNode(doc));
			}
		}

	}

	private void addTrumpChallengeWithPoints(Document doc, Match match,
			List<Player> gamePlayers, Player requestingPlayer)
			throws RefreshException {

		Element node = doc.getElementById("td_31");
		HTMLDOMHelper.addTextNode(doc, node, "td_11", "Challenge Player: ");

		HTMLDOMHelper.addTextNode(doc, node, "td_31", match
				.getMatchChallengePlayer().getPlayerName());

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addTextNode(doc, node, "td_31", "Challenge Points: ");

		HTMLDOMHelper.addLineBreak(doc, node);

		for (int i = match.getMatchChallengePoints() + 1; i <= 29; i++) {

			Element cNode = doc.createElement("input");
			cNode.setAttribute("type", "radio");
			cNode.setAttribute("value", Integer.toString(i));
			cNode.setAttribute("name", "challengePoint");
			cNode.setAttribute("id", "challengePoint" + i);
			cNode.setAttribute("onChange",
					"javascript:doSetNewChallengePoints(" + Integer.toString(i)
							+ ")");
			node.appendChild(cNode);
			HTMLDOMHelper.addTextNode(doc, node, "td_31", Integer.toString(i));
			HTMLDOMHelper.addLineBreak(doc, node);
			if (i == 24) {

			}

		}

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addLineBreak(doc, node);
		if (requestingPlayer.getTeam() != match.getMatchChallengePlayer()
				.getTeam()) {

			node.appendChild(HTMLDOMHelper.addHTMLhRef(doc, HTMLDOMHelper
					.addHtmlImage(doc, "CHALLENGE", "images/challenge.jpg",
							"CHALLENGE"), "javascript:setChallenge(false)"));
			HTMLDOMHelper.addTextNode(doc, node, "td_31", "      ");
			node.appendChild(HTMLDOMHelper.addHTMLhRef(doc,
					HTMLDOMHelper.addHtmlImage(doc, "DOUBLE",
							"images/double.jpg", "DOUBLE"),
					"javascript:setChallenge(true)"));
		} else {
			HTMLDOMHelper.addTextNode(doc, node, "td_31",
					"The other Team only can finalize this challenge");

		}

	}

	private void addReDoubleChallenge(Document doc, Match match,
			List<Player> gamePlayers, Player requestingPlayer) throws Exception {
		Element node = doc.getElementById("td_31");
		HTMLDOMHelper.addTextNode(doc, node, "td_31", "Challenge Player: ");

		HTMLDOMHelper.addTextNode(doc, node, "td_31", match
				.getMatchChallengePlayer().getPlayerName());

		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addTextNode(doc, node, "td_31", "Challenge Points: ");

		HTMLDOMHelper.addTextNode(doc, node, "td_31",
				match.getMatchChallengePoints() + "");

		HTMLDOMHelper.addLineBreak(doc, node);
		if (requestingPlayer.getTeam() == match.getMatchChallengePlayer()
				.getTeam()) {
			node.appendChild(HTMLDOMHelper.addHTMLhRef(doc, HTMLDOMHelper
					.addHtmlImage(doc, "PASS", "images/pass.jpg", "PASS"),
					"javascript:setReDouble(false)"));
			HTMLDOMHelper.addTextNode(doc, node, "td_31", "       ");
			node.appendChild(HTMLDOMHelper.addHTMLhRef(doc, HTMLDOMHelper
					.addHtmlImage(doc, "RE DOUBLE CHALLENGE",
							"images/redouble.jpg", "RE DOUBLE CHALLENGE"),
					"javascript:setReDouble(true)"));

		} else {
			HTMLDOMHelper.addTextNode(doc, node, "td_31",
					"Waiting if the other team wants to Re Double Challenge ");
		}
	}

	protected void listDisplayedPlayerCards(Document doc, Match match,
			List<Player> gamePlayers, Player requestingPlayer, boolean hasTurn)
			throws RefreshException {
		Element node = doc.getElementById("td_32");
		List<Card> cards = match.getPlayerCards().get(
				gamePlayers.get(requestingPlayer.getPlayerId() - 1));
		Collections.sort(cards);
		Iterator<Card> itr = cards.iterator();
		while (itr.hasNext()) {
			Card card = itr.next();
			if (hasTurn) {
				System.out.println();
				Node imgNode = HTMLDOMHelper.addHtmlImage(doc, (card.suit()
						.ordinal() + 1) + "_" + (card.rank().ordinal() + 2),
						"images/" + (card.suit().ordinal() + 1) + "_"
								+ (card.rank().ordinal() + 2) + ".jpg",
						"images/" + (card.suit().ordinal() + 1) + "_"
								+ (card.rank().ordinal() + 2) + ".jpg");
				node.appendChild(HTMLDOMHelper.addHTMLhRef(doc, imgNode,
						"javascript:makeMove(" + (card.suit().ordinal() + 1)
								+ "," + (card.rank().ordinal() + 2) + ")"));
			} else {
				node.appendChild(card.toHTMLNode(doc));
			}
		}
	}

	private void setGameScoreCard(Document doc, Match match)
			throws RefreshException {
		Element node;
		node = doc.getElementById("td_13");
		node.appendChild(doc.createTextNode("GAME SCORE CARD"));
		HTMLDOMHelper.addLineBreak(doc, node);
		node.appendChild(doc.createTextNode("TEAM 1: "));
		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addLineBreak(doc, node);
		String img = "images/score/score_team1_"
				+ match.getTeam_1_CurrentMatchScore() + ".jpg";
		node.appendChild(HTMLDOMHelper.addHtmlImage(doc, img, img, img));

		node = doc.getElementById("td_11");
		node.appendChild(doc.createTextNode("GAME SCORE CARD"));
		HTMLDOMHelper.addLineBreak(doc, node);
		node.appendChild(doc.createTextNode("TEAM 2: "));
		HTMLDOMHelper.addLineBreak(doc, node);
		HTMLDOMHelper.addLineBreak(doc, node);
		img = "images/score/score_team2_" + match.getTeam_2_CurrentMatchScore()
				+ ".jpg";
		node.appendChild(HTMLDOMHelper.addHtmlImage(doc, img, img, img));

	}
}
