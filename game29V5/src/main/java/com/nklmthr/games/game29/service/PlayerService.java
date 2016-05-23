package com.nklmthr.games.game29.service;

import java.util.ArrayList;
import java.util.List;

import com.nklmthr.games.game29.model.Player;

public class PlayerService {
	private List<Player> players = new ArrayList<Player>();

	public void initialize() {
		players.add(new Player(1, "One", "Nikhil"));
		players.add(new Player(2, "Two", "Nikhil"));
		players.add(new Player(3, "Three", "Nikhil"));
		players.add(new Player(4, "Four", "Nikhil"));
	}


	public Player get(int index) {
		return players.get(index - 1);
	}

	public Player getTeamMate(int playerId) {
		int teamMateId = (playerId + 2) < 5 ? (playerId + 2) : (playerId + 2) % 4;// 1-3,2-4,3-1,4-2
		return players.get(teamMateId - 1);
	}

	public Player getTeamMate(Player player) {
		int teamMateId = (player.getPlayerId() + 2) < 5 ? (player.getPlayerId() + 2) : (player.getPlayerId() + 2) % 4;// 1-3,2-4,3-1,4-2
		return players.get(teamMateId - 1);
	}

	public Player getOppositionSecondPlayer(int playerId) {
		int oppositionSecondId = (playerId + 3) < 5 ? (playerId + 3) : (playerId + 3) % 4;// 1-4,2-1,3-2,4-3
		return players.get(oppositionSecondId - 1);
	}

	public Player getOppositionSecondPlayer(Player player) {
		int oppositionSecondId = (player.getPlayerId() + 3) < 5 ? (player.getPlayerId() + 3)
				: (player.getPlayerId() + 3) % 4;// 1-4,2-1,3-2,4-3
		return players.get(oppositionSecondId - 1);
	}

	public Player getOppositionFirstPlayer(int playerId) {
		int oppositionFirstId = (playerId + 1) < 5 ? (playerId + 1) : (playerId + 1) % 4;// 1-2,2-3,3-4,4-1
		return players.get(oppositionFirstId - 1);
	}

	public Player getOppositionFirstPlayer(Player player) {
		int oppositionFirstId = (player.getPlayerId() + 1) < 5 ? (player.getPlayerId() + 1)
				: (player.getPlayerId() + 1) % 4;// 1-2,2-3,3-4,4-1
		return players.get(oppositionFirstId - 1);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
