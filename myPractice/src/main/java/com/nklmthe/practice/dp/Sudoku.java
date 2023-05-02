package com.nklmthe.practice.dp;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {

	public static void main(String[] args) {
		String[][] board = new String[][] { { "8", "3", ".", ".", "7", ".", ".", ".", "." },
				{ "6", ".", ".", "1", "9", "5", ".", ".", "." }, { ".", "9", "8", ".", ".", ".", ".", "6", "." },
				{ "8", ".", ".", ".", "6", ".", ".", ".", "3" }, { "4", ".", ".", "8", ".", "3", ".", ".", "1" },
				{ "7", ".", ".", ".", "2", ".", ".", ".", "6" }, { ".", "6", ".", ".", ".", ".", "2", "8", "." },
				{ ".", ".", ".", "4", "1", "9", ".", ".", "5" }, { ".", ".", ".", ".", "8", ".", ".", "7", "9" } };
//				{ { "5", "3", ".", ".", "7", ".", ".", ".", "." },
//				{ "6", ".", ".", "1", "9", "5", ".", ".", "." }, { ".", "9", "8", ".", ".", ".", ".", "6", "." },
//				{ "8", ".", ".", ".", "6", ".", ".", ".", "3" }, { "4", ".", ".", "8", ".", "3", ".", ".", "1" },
//				{ "7", ".", ".", ".", "2", ".", ".", ".", "6" }, { ".", "6", ".", ".", ".", ".", "2", "8", "." },
//				{ ".", ".", ".", "4", "1", "9", ".", ".", "5" }, { ".", ".", ".", ".", "8", ".", ".", "7", "9" } };
		Set<String> rowsVisited = new HashSet<String>();
		Set<String> colsVisited = new HashSet<String>();
		Set<String> cubeVisited = new HashSet<String>();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!board[i][j].equals(".")) {
					if (!rowsVisited.add(board[i][j] + " found at row " + i)
							|| !colsVisited.add(board[i][j] + " found at col " + i)
							|| !cubeVisited.add(board[i][j] + " found in cube at row: " + i / 3 + "col: " + j / 3)) {
						System.out.println("Invalid Board....");
					}

				}
			}
		}

	}

}
