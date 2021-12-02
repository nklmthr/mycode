package com.nklmthr.nokia;

public class Cell {
	private int xPos;
	private int yPos;
	private CellType cellType;
	public Cell(int xPos, int yPos, CellType cellType) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.cellType  = cellType;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public CellType getCellType() {
		return cellType;
	}
	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
	
	
}

enum CellType {
	EMPTY, FOOD, SNAKE
}