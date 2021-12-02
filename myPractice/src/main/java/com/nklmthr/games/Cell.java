package com.nklmthr.games;

public class Cell {
	private int row, col;
	private CellType cellType;

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		this.cellType = CellType.EMPTY;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}

}
