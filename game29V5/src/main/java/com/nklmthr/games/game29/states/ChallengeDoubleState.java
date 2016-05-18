package com.nklmthr.games.game29.states;

import com.nklmthr.games.game29.events.ChallengeEvent;
import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.FetchEvent;
import com.nklmthr.games.game29.model.Game;
import com.nklmthr.games.game29.model.Match;
import com.nklmthr.games.game29.model.State;

public class ChallengeDoubleState extends SectionHTML implements State {
	public State transition(Game game, Event event) {
		if (event instanceof ChallengeEvent) {
			ChallengeEvent challenge = (ChallengeEvent) event;
			// If player passes
			if (challenge.isPass()) {
				//if the player is the last player to pass
				// or challenge completed
				// move to next state
				if (challenge.getPlayer().equals(game.getMatch().getDealPlayer()) || game.getMatch().getChallenge()
						.getChallengePlayer().equals(game.getMatch().getDealPlayer())) {
					if (game.getMatch().isChallengeDoubled()) {
						return new ChallengeReDoubleState();
					} else {
						game.getMatch().setTrumpSetPlayer(game.getMatch().getChallenge().getChallengePlayer());
						return new TrumSetState();
					}
				}
				//if the player passing is primary player
				else if (challenge.getPlayer().equals(game.getMatch().getChallenge().getChallengePrimaryPlayer())) {
					game.getMatch().getChallenge()
							.setChallengePrimaryPlayer(game.getMatch().getChallenge().getChallengeSecondaryPlayer());
					game.getMatch().getChallenge().setChallengeSecondaryPlayer(playerService
							.getOppositionFirstPlayer(game.getMatch().getChallenge().getChallengeSecondaryPlayer()));
				}
				//if the player passing is not primary player
				else {
					game.getMatch().getChallenge()
							.setChallengeSecondaryPlayer(playerService.getOppositionFirstPlayer(challenge.getPlayer()));
				}
			}
			if (challenge.isDoubleChallenge()) {
				game.getMatch().setChallengeDoubled(true);
				return new ChallengeReDoubleState();
			}
			if (game.getMatch().getChallenge().getChallengeSecondaryPlayer().equals(challenge.getPlayer())) {
				if (game.getMatch().getChallenge().getChallengePoints() < challenge.getChallengePoints()) {
					game.getMatch().getChallenge().setChallengePoints(challenge.getChallengePoints());
					game.getMatch().getChallenge().setChallengePlayer(challenge.getPlayer());
				}
			} else if (game.getMatch().getChallenge().getChallengePrimaryPlayer().equals(challenge.getPlayer())) {
				if (game.getMatch().getChallenge().getChallengePoints() <= challenge.getChallengePoints()) {
					game.getMatch().getChallenge().setChallengePoints(challenge.getChallengePoints());
					game.getMatch().getChallenge().setChallengePlayer(challenge.getPlayer());
				}
			}
		}
		return new ChallengeDoubleState();
	}

	public String getSection21(Game game, Event event) {
		return getSection21Generic(game, event, 4);
	}

	public String getSection11(Game game, Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSection12(Game game, Event event) {
		return getSection12Generic(game, event, 4);
	}

	public String getSection13(Game game, Event event) {
		return getSection13Generic(game, event);
	}

	public String getSection22(Game game, Event event) {
		return getSection22GenericPlayArena(game, event);
	}

	public String getSection23(Game game, Event event) {
		return getSection23Generic(game, event, 4);
	}

	public String getSection31(Game game, Event event) {
		StringBuilder str = new StringBuilder();
		if (event instanceof FetchEvent) {
			FetchEvent fetch = (FetchEvent) event;
			Match match = game.getMatch();

			int challengePoints = match.getChallenge().getChallengePoints();
			if (match.getChallenge().getChallengePrimaryPlayer().equals(fetch.getPlayer())) {
				for (int i = 16; i < 30; i++) {
					if (i == 23) {
						str.append("<br><br><br>");
					}
					if (i < challengePoints) {
						str.append("<a href=\"#\" class=\"buttonNA\"/>" + i + "</a>&nbsp;&nbsp; ");
					} else if (i == challengePoints
							&& match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
						str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + i
								+ ",false,false)\" class=\"buttonNO\"/>" + i + "</a>&nbsp;&nbsp; ");
					} else {
						str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + i
								+ ",false,false)\" class=\"buttonA\"/>" + i + "</a>&nbsp;&nbsp; ");
					}
				}

				if (!match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
					str.append("<br><br><a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + challengePoints
							+ ",true,false)\" class=\"buttonNO\"/>PASS</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + challengePoints
							+ ",false,true)\" class=\"buttonNO\"/>DOUBLE</a>&nbsp;&nbsp;");
				}

			} else if (match.getChallenge().getChallengeSecondaryPlayer().equals(fetch.getPlayer())) {
				for (int i = 16; i < 30; i++) {
					if (i == 23) {
						str.append("<br><br><br>");
					}
					if (i < challengePoints) {
						str.append("<a href=\"#\" class=\"buttonNA\"/>" + i + "</a>&nbsp;&nbsp; ");
					} else if (i == challengePoints
							&& match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
						str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + i
								+ ",false,false)\" class=\"buttonNO\"/>" + i + "</a>&nbsp;&nbsp; ");
					} else if (i == challengePoints
							&& !match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
						str.append("<a href=\"#\" class=\"buttonNA\"/>" + i + "</a>&nbsp;&nbsp; ");
					} else {
						str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + i
								+ ",false,false)\" class=\"buttonA\"/>" + i + "</a>&nbsp;&nbsp; ");
					}
				}
				if (!match.getChallenge().getChallengePlayer().equals(fetch.getPlayer())) {
					str.append("<br><br><a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + challengePoints
							+ ",true,false)\" class=\"buttonNO\"/>PASS</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					str.append("<a href=\"javascript:void(0);\" onclick=\"throwChallenge(" + challengePoints
							+ ",false,true)\" class=\"buttonNO\"/>DOUBLE</a>&nbsp;&nbsp;");
				}
			} else {
				for (int i = 16; i < 30; i++) {
					if (i == 23) {
						str.append("<br><br><br>");
					}
					if (i < challengePoints) {
						str.append("<a href=\"#\" class=\"buttonNA\"/>" + i + "</a>&nbsp;&nbsp; ");
					} else if (i == challengePoints) {
						str.append("<a href=\"#\" class=\"buttonA\"/>" + i + "</a>&nbsp;&nbsp; ");
					} else {
						str.append("<a href=\"#\" class=\"buttonDB\"/>" + i + "</a>&nbsp;&nbsp; ");
					}
				}
			}
		}
		return str.toString();
	}

	public String getSection32(Game game, Event event) {
		return getSection32Generic(game, event, 4);
	}

	public String getSection33(Game game, Event event) {
		// TODO Auto-generated method stub
		return null;
	}

}
