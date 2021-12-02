package com.nklmthr.games;

public class Board {
	private int ROW_COUNT, COL_COUNT;
	private Cell[][] cells;

	public Board(int rowCount, int columnCount) {
		ROW_COUNT = rowCount;
		COL_COUNT = columnCount;

		cells = new Cell[ROW_COUNT][COL_COUNT];
		for (int row = 0; row < ROW_COUNT; row++) {
			for (int column = 0; column < COL_COUNT; column++) {
				cells[row][column] = new Cell(row, column);
			}
		}
	}

	public void generateFood() {
		System.out.println("Going to generate food");
		while (true) {
			int row = (int) (Math.random() * ROW_COUNT);
			int column = (int) (Math.random() * COL_COUNT);
			if (cells[row][column].getCellType() != CellType.SNAKE) {
				cells[row][column].setCellType(CellType.FOOD);
				System.out.println("Food is generated at: " + row + " " + column);
				break;
			}
		}
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

}
