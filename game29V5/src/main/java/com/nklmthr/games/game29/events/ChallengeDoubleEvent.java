package com.nklmthr.games.game29.events;

import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.Player;

public class ChallengeDoubleEvent implements Event{
	private Player player;
	private boolean pass;
	private boolean reDdoubleChallenge;
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
	public boolean isReDdoubleChallenge() {
		return reDdoubleChallenge;
	}
	public void setReDdoubleChallenge(boolean reDdoubleChallenge) {
		this.reDdoubleChallenge = reDdoubleChallenge;
	}
	
	
}
