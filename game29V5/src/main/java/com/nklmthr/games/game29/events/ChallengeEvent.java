package com.nklmthr.games.game29.events;

import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.Player;

public class ChallengeEvent implements Event {
	private int challengePoints;
	private Player player;
	private boolean pass;
	private boolean doubleChallenge;
	
	public int getChallengePoints() {
		return challengePoints;
	}
	public void setChallengePoints(int challengePoints) {
		this.challengePoints = challengePoints;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	public boolean isDoubleChallenge() {
		return doubleChallenge;
	}
	public void setDoubleChallenge(boolean doubleChallenge) {
		this.doubleChallenge = doubleChallenge;
	}
	
	
}
