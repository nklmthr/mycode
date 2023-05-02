package com.nklmthe.practice.dp;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {

	public int countCharacters(String[] words, String chars) {
		int count = 0;
		for(String word: words) {
			insert(word);
		}
		if(search(chars)==true) {
			
		}
		return 0;
	}

	static TrieNode root;
	private static final int ALPHABET_SIZE = 26;
	TrieNode[] children = new TrieNode[ALPHABET_SIZE];
	boolean isEndOfWord;

	TrieNode() {
		isEndOfWord = false;
		for (int i = 0; i < ALPHABET_SIZE; i++)
			children[i] = null;
	}

	static void insert(String key) {
		int level;
		int length = key.length();
		int index;

		TrieNode pCrawl = root;

		for (level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';
			if (pCrawl.children[index] == null)
				pCrawl.children[index] = new TrieNode();

			pCrawl = pCrawl.children[index];
		}

		// mark last node as leaf
		pCrawl.isEndOfWord = true;
	}

	// Returns true if key presents in trie, else false
	static boolean search(String key) {
		int level;
		int length = key.length();
		int index;
		TrieNode pCrawl = root;

		for (level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';

			if (pCrawl.children[index] == null)
				return false;

			pCrawl = pCrawl.children[index];
		}

		return (pCrawl.isEndOfWord);
	}
}