package com.nklmthr.practice.phase1;


public class WordsInRandomMatrixFromDictionary {
	static String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GO" };

	public static void main(String[] args) {

		char boggle[][] = { { 'G', 'I', 'Z' }, { 'U', 'E', 'K' }, { 'Q', 'S', 'E' } };
		words(boggle);
	}

	static boolean isWord(String str) {
		for (int i = 0; i < dictionary.length; i++) {
			if (str.equals(dictionary[i])) {
				//System.out.println("str = " + str + " dictionary=" + dictionary[i]);
				return true;
			}
		}
		return false;
	}

	static void words(char[][] boggle) {
		boolean visited[][] = new boolean[boggle.length][boggle[0].length];
		String str = "";
		for (int i = 0; i < boggle.length; i++)
			for (int j = 0; j < boggle[0].length; j++)
				findWordsUtil(boggle, visited, i, j, str);

	}

	private static void findWordsUtil(char[][] boggle, boolean[][] visited, int i, int j, String str) {
		visited[i][j] = true;
		str = str + boggle[i][j];
		if (isWord(str)) {
			System.out.println(str);
		}
		for (int row = i - 1; row <= i + 1 && row < boggle.length; row++)
			for (int col = j - 1; col <= j + 1 && col < boggle[0].length; col++)
				if (row >= 0 && col >= 0 && !visited[row][col])
					findWordsUtil(boggle, visited, row, col, str);

		// Erase current character from string and mark visited
		// of current cell as false
		str = str.substring(0, str.length() - 1);
		visited[i][j] = false;
	}

}
