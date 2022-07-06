package com.nklmthr.snakesladder;

public class Player {
	private String name;
	private int position;

	public Player(String name, int position) {
		this.position = position;
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void play(int dice) {
		position += dice;
	}

}
