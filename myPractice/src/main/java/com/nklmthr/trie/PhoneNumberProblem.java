package com.nklmthr.trie;

import java.util.HashMap;
import java.util.Map;

public class PhoneNumberProblem {

}

class Node{
	public Node(Character c) {
		this.c = c;
	}
	Character c;
	Map<Character, Trie> children = new HashMap<Character, Trie>();
	boolean isWord;
	public Character getC() {
		return c;
	}
	public void setC(Character c) {
		this.c = c;
	}
	public Map<Character, Trie> getChildren() {
		return children;
	}
	public void setChildren(Map<Character, Trie> children) {
		this.children = children;
	}
	public boolean isWord() {
		return isWord;
	}
	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}
	
}

class Trie{
	Node root = new Node(null);
	
	public void add(String word) {
		for(Character c: word.toCharArray()) {
			if(root.getChildren().containsKey(c)) {
				
			}
		}
	}
}