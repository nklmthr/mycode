package com.nklmthr.games.game29.model;

public class Challenge {
	private int challengePoints;
	private Player challengePlayer;
	private Player challengePrimaryPlayer;
	private Player challengeSecondaryPlayer;
	
	
	public int getChallengePoints() {
		return challengePoints;
	}
	public void setChallengePoints(int challengePoints) {
		this.challengePoints = challengePoints;
	}
	public Player getChallengePrimaryPlayer() {
		return challengePrimaryPlayer;
	}
	public void setChallengePrimaryPlayer(Player challengePrimaryPlayer) {
		this.challengePrimaryPlayer = challengePrimaryPlayer;
	}
	public Player getChallengeSecondaryPlayer() {
		return challengeSecondaryPlayer;
	}
	public void setChallengeSecondaryPlayer(Player challengeSecondaryPlayer) {
		this.challengeSecondaryPlayer = challengeSecondaryPlayer;
	}
	public Player getChallengePlayer() {
		return challengePlayer;
	}
	public void setChallengePlayer(Player challengePlayer) {
		this.challengePlayer = challengePlayer;
	}

}
