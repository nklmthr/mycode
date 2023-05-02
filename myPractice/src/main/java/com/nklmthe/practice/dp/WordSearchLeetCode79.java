package com.nklmthe.practice.dp;

import java.util.HashSet;
import java.util.Set;

public class WordSearchLeetCode79 {

	public static void main(String[] args) {
		char[][] board = new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		char[] word = "ABCCED".toCharArray();
		Set<Box> visited = new HashSet<Box>();
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (dfs(board, word, visited, r, c, 0)) {
					System.out.println("Present");
					System.exit(0);
				}
			}
		}
	}

	private static boolean dfs(char[][] board, char[] word, Set<Box> visited, int r, int c, int i) {
		System.out.println("visited=" + visited + ", r=" + r + " ,c=" + c + " ,i=" + i);
		if (i == word.length - 1) {
			return true;
		}
		Box box = new Box(r, c);
		if (r < 0 || c < 0 || r > board.length || c > board[0].length || word[i] != board[r][c]
				|| visited.contains(box)) {
			return false;
		}
		visited.add(box);
		boolean result = dfs(board, word, visited, r + 1, c, i + 1) || dfs(board, word, visited, r - 1, c, i + 1)
				|| dfs(board, word, visited, r, c + 1, i + 1) || dfs(board, word, visited, r, c - 1, i + 1);
		visited.remove(box);
		return result;
	}

}

class Box {
	int row;
	int col;

	public Box(int row, int col) {
		super();
		this.row = row;
		this.col = col;
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

	@Override
	public String toString() {
		return "Box [" + row + ", " + col + "]";
	}

}
