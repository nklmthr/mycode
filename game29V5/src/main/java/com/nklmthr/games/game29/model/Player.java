package com.nklmthr.games.game29.model;

import java.util.Date;

public class Player {
	private int playerId;
	private String playerName;
	private String playerPassword;
	private boolean registered;
	private int team;
	private Date lastSeen;

	public Player(int playerId, String playerName, String playerPassword) {
		setPlayerId(playerId);
		setPlayerName(playerName);
		setPlayerPassword(playerPassword);
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
		if (playerId % 2 != 0) {
			this.team = 1;
		} else {
			this.team = 2;
		}
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}

	public int getTeam() {
		return team;
	}

	@Override
	public String toString() {
		return "Player ID: " + this.getPlayerId() + " Player Name: " + this.getPlayerName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerId != other.playerId)
			return false;
		return true;
	}

	public Date getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}

}
