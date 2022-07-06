package com.nklmthr.snakesladder;

import java.util.Random;

public class Board {

	private int complexity;
	int[][] snakes;
	int[][] ladders;

	public Board(int complexity) {
		Random ran = new Random();
		this.complexity = complexity;
		snakes = new int[complexity][2];
		ladders = new int[complexity][2];
		for (int i = 0; i < complexity; i++) {
			snakes[i][0] = ran.nextInt(21, 97);
			snakes[i][1] = ran.nextInt(5, snakes[i][0] - 1);

			ladders[i][0] = ran.nextInt(1, 85);
			ladders[i][1] = ran.nextInt(ladders[i][0] + 1, 95);
		}
	}

	public void checkSnakeOrLadder(Player p) {
		for (int i = 0; i < snakes.length; i++) {
			if (snakes[i][0] == p.getPosition()) {
				p.setPosition(snakes[i][1]);
			}
		}
		for (int i = 0; i < ladders.length; i++) {
			if (ladders[i][0] == p.getPosition()) {
				p.setPosition(ladders[i][1]);
			}
		}
	}

	public int[][] getSnakes() {
		return snakes;
	}

	public int[][] getLadders() {
		return ladders;
	}

}
