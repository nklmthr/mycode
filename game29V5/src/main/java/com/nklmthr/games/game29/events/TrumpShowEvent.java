package com.nklmthr.games.game29.events;

import com.nklmthr.games.game29.model.Event;
import com.nklmthr.games.game29.model.Player;

public class TrumpShowEvent implements Event {
	private Player player;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
