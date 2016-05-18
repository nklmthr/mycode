package com.nklmthr.games.game29.events;

import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.Player;

public class TrumpSetEvent implements Event {
	private Player player;
	private int trump;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getTrump() {
		return trump;
	}

	public void setTrump(int trump) {
		this.trump = trump;
	}

}
