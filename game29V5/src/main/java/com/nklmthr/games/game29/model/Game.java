package com.nklmthr.games.game29.model;

public class Game {
	private Match match;
	private int team1score;
	private int team2score;

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public void setTeamScore(int team, int score) {
		if ((team % 2) == 0) {
			setTeam2score(score);
		} else {
			setTeam1score(score);
		}
	}

	public int getTeamScore(int team) {
		if ((team % 2) == 0) {
			return getTeam2score();
		} else {
			return getTeam1score();
		}
	}

	public void setTeam1score(int team1score) {
		this.team1score = team1score;
	}

	public int getTeam1score() {
		return team1score;
	}

	public int getTeam2score() {
		return team2score;
	}

	public void setTeam2score(int team2score) {
		this.team2score = team2score;
	}

}
