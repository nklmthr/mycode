package com.nklmthr.games;

import java.util.LinkedList;

public class Snake {
	private LinkedList<Cell> snakeCellList = new LinkedList<Cell>();
	private Cell head;

	public Snake(Cell init) {
		head = init;
		snakeCellList.add(head);
		head.setCellType(CellType.SNAKE);

	}

	public void grow() {
		snakeCellList.add(head);
	}

	public void move(Cell nextCell) {
		System.out.println("Snake is moving to " + nextCell.getRow() + " " + nextCell.getCol());
		Cell tail = snakeCellList.removeLast();
		tail.setCellType(CellType.EMPTY);

		head = nextCell;
		head.setCellType(CellType.SNAKE);
		snakeCellList.addFirst(head);
	}

	public boolean checkCrash(Cell nextCell) {
		System.out.println("Going to check for Crash");
		for (Cell cell : snakeCellList) {
			if (cell == nextCell) {
				return true;
			}
		}
		return false;
	}

	public LinkedList<Cell> getSnakeCellList() {
		return snakeCellList;
	}

	public void setSnakeCellList(LinkedList<Cell> snakeCellList) {
		this.snakeCellList = snakeCellList;
	}

	public Cell getHead() {
		return head;
	}

	public void setHead(Cell head) {
		this.head = head;
	}

}
