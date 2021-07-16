package com.nklmthr.practice.snakenladders;

import java.util.Random;
import java.util.Scanner;

public class SnakeLadder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Enter the number of players: ");
		Scanner sc = new Scanner(System.in);
		int numPlayers = sc.nextInt();
		sc.nextLine();
		Player[] players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			System.out.print("Enter name of player " + i + " :");

			String name = sc.nextLine();
			int position = 0;
			Player p = new Player(name, position);
			players[i] = p;
		}
		int currentPlayer = 0;
		while (!sc.nextLine().equalsIgnoreCase("q")) {
			int rand = throwDice();
			System.out.println("Rand " + rand);
			//int curPos = players[currentPlayer].getCurrentPosition();
			//players[currentPlayer].setCurrentPosition(curPos + rand);
			players[currentPlayer].move(rand);
			//players[currentPlayer].currentPosition += rand;
			currentPlayer += 1;
			if (currentPlayer == players.length) {
				currentPlayer = 0;
			}
			printCurrentPlayerPosition(players);
		}

	}

	private static void printCurrentPlayerPosition(Player[] players) {
		for (int i = 0; i < players.length; i++) {
			System.out.print("Player " + players[i].getName() + " :" + players[i].getCurrentPosition() + " ;");
		}
		System.out.println();
	}

	static int throwDice() {
		Random r = new Random();
		return (r.nextInt(6)) + 1;
	}

}

class Player {
	public Player(String name, int position) {
		this.name = name;
		this.currentPosition = position;
	}

	private String name;
	private int currentPosition;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
	public void move (int num) {
		currentPosition = currentPosition + num;
	}

}