package org.personal.model;

public class Player {
	private int playerId;
	private String playerName;
	private String playerPassword;
	private int team;

	public Player(int playerId, String playerName, String playerPassword) {
		setPlayerId(playerId);
		setPlayerName(playerName);
		setPlayerPassword(playerPassword);
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

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
		if (playerId % 2 != 0) {
			this.setTeam(1);
		} else {
			this.setTeam(2);
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

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}

	public int getTeam() {
		return team;
	}

	private void setTeam(int team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Player ID: " + this.getPlayerId() + " Player Name: "
				+ this.getPlayerName();
	}

}
