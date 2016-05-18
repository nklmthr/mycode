package com.nklmthr.games.game29.model;

public class Game {
	private Match match = new Match();
	private int team1score;
	private int team2score;

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public void setTeam1score(int team1score) {
		this.team1score = team1score;
	}

	public String getTeam2score() {
		return "<img src='images/score/score_team2_" + team2score + ".jpg'>";
	}

	public String getTeam1score() {
		return "<img src='images/score/score_team1_" + team1score + ".jpg'>";
	}

	public void setTeam2score(int team2score) {
		this.team2score = team2score;
	}

}
