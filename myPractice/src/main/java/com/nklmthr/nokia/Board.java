package com.nklmthr.nokia;

public class Board {
	public int BOARD_X = 20, BOARD_Y = 20;
	Cell[][] board = new Cell[BOARD_X][BOARD_Y];
	
	public Board() {
		for (int i = 0; i < BOARD_X; i++) {
			for (int j = 0; j < BOARD_Y; j++) {
				Cell cell = new Cell(i, j, CellType.EMPTY);
				board[i][j] = cell;				
			}
		}

	}

}
