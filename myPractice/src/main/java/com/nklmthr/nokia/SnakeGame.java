package com.nklmthr.nokia;

public class SnakeGame {
	private Board board = null;

	public SnakeGame(Board board) {
		this.board = board;
	}

	public static void main(String[] args) {
		Board board = new Board();
		SnakeGame game = new SnakeGame(board);

		
	}

}
