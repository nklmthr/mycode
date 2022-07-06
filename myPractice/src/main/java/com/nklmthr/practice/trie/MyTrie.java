package com.nklmthr.practice.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyTrie {
	private static Node root = new Node();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] dictionary = { "Nikhil", "Srishti", "Piyu", "Ishi", "Iti", "Nikkel", "Sriman", "Srilanka", "Srimati" };
		String searchWord = "Srilanka";
		createTrie(dictionary);
		printTrie();
		System.out.println("Should be true:" + search(searchWord));
		System.out.println("Should be false" + search("Srimati"));
	}

	private static void printTrie() {
		Node current = root;
		Queue<Node> q = new PriorityQueue<Node>();
		q.add(current);
		while (!q.isEmpty()) {
			Node node = q.poll();
			Node[] children = node.getChildren();
			for (Node child : children) {
				if (child != null) {
					q.add(child);
					 
				}
			}
			System.out.println(node.getCharacter());
		}

	}

	private static String search(String search) {
		Node current = root;
		for(int i=0;i<search.length();i++) {
			
		}
		return "";
		
	}

	private static void createTrie(String[] dictionary) {

		for (int i = 0; i < dictionary.length; i++) {
			Node current = root;
			for (int j = 0; j < dictionary[i].length(); j++) {
				char currentChar = dictionary[i].charAt(j);
				int pos = String.valueOf(currentChar).toUpperCase().charAt(0) - 'A';
				if (current.getChildren()[pos] == null) {
					current.getChildren()[pos] = new Node();
				} else if (current.getChildren()[pos] != null && current.getChildren()[pos].getCharacter() == ' ') {
					current.getChildren()[pos].setCharacter(String.valueOf(currentChar).toUpperCase().charAt(0));
				} else if (current.getChildren()[pos] != null
						&& current.getChildren()[pos].getCharacter() == currentChar) {
					current = current.getChildren()[pos];
				}
				if (j == dictionary[i].length() - 1) {
					current.setComplete(true);
				}
			}
		}

	}

}

class Node implements Comparable<Node> {
	char character;
	boolean isComplete;
	Node[] children = new Node[26];

	public Node() {
		super();
		this.character = ' ';
	}

	public Node(char currentChar) {
		character = currentChar;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public Node[] getChildren() {
		return children;
	}

	public void setChildren(Node[] children) {
		this.children = children;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	@Override
	public int compareTo(Node o) {
		if (this.character > o.getCharacter()) {
			return 1;
		} else if (this.character < o.getCharacter()) {
			return -1;
		} else {
			return 0;
		}
	}

}