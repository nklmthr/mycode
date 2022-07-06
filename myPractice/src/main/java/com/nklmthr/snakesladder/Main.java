package com.nklmthr.snakesladder;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board(15);
		Player p1 = new Player("P1", 0);
		Player p2 = new Player("P2", 0);

		Random rand = new Random();
		System.out.println("Please enter to start play. Enter 'X' to exit");
		Scanner sc = new Scanner(System.in);
		String inp = sc.next();
		int count = 0;
		while (!inp.equalsIgnoreCase("x")) {
			int dice = rand.nextInt(1, 6);
			System.out.println(dice);
			if (count % 2 == 0) {
				p1.play(dice);
				board.checkSnakeOrLadder(p1);
			} else {
				p2.play(dice);
				board.checkSnakeOrLadder(p2);
			}
			showBoard(board, p1, p2);
			System.out.println("Please enter to start play. Enter 'X' to exit");
			inp = sc.next();
			count++;
		}
	}

	private static void showBoard(Board board, Player p1, Player p2) {
		int snakes[][] = board.getSnakes();
		int ladders[][] = board.getLadders();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boolean printPlaneNumber = true;
				if (p1.getPosition() == (i * 10) + j + 1 && p2.getPosition() == (i * 10) + j + 1) {
					System.out.print(String.format("%10s", "-P1-P2-"));
					printPlaneNumber = false;
				} else if (p1.getPosition() == (i * 10) + j + 1) {
					System.out.print(String.format("%10s", "-P1-"));
					printPlaneNumber = false;
				} else if (p2.getPosition() == (i * 10) + j + 1) {
					System.out.print(String.format("%10s", "-P2-"));
					printPlaneNumber = false;
				} else {
					for (int p = 0; p < snakes.length; p++) {
						if (snakes[p][0] == (i * 10 + j)) {
							// System.out.print(String.format("%10s", " " + ((i * 10) + j + 1) + " "));
							System.out.print(String.format("%10s", " S-> " + snakes[p][1] + " "));
							printPlaneNumber = false;
						}
					}
					for (int p = 0; p < ladders.length; p++) {
						if (ladders[p][0] == (i * 10 + j)) {
							// System.out.print(String.format("%10s", " " + ((i * 10) + j + 1) + " "));
							System.out.print(String.format("%10s", " L-> " + ladders[p][1] + " "));
							printPlaneNumber = false;
						}
					}

				}
				if (printPlaneNumber) {
					System.out.print(String.format("%10s", " " + ((i * 10) + j + 1) + " "));
				}

			}
			System.out.println();
		}

	}

}
