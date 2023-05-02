package com.nklmthe.practice.dp;

import java.util.ArrayList;
import java.util.List;

public class LeetCode68 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = new String[] { "This", "is", "an", "example", "of", "text", "justification." };
		int maxWidth = 16;
		List<String> result = fullJustify(words, maxWidth);
		System.out.println(result);
	}

	public static List<String> fullJustify(String[] words, int maxWidth) {
		List<String> result = new ArrayList<>();
		int currentLength = 0;
		int startIndex = 0, endIndex = 0;
		for (int index = 0; index < words.length; index++) {
			if (currentLength == 0) {
				if (words[index].length() > maxWidth) {
					return null;
				} else {
					startIndex = index;
					currentLength = words[index].length();
				}
			} else if (currentLength + 1 + words[index].length() > maxWidth) {
				result.add(constractString(words, startIndex, index - 1, currentLength, maxWidth));
				startIndex = index;
				currentLength = words[index].length();
				endIndex = index - 1;
			} else {
				currentLength += (1 + words[index].length());
			}
		}
		result.add(constractString(words, startIndex, words.length - 1, currentLength, maxWidth));
		return result;
	}

	private static String constractString(String[] words, int start, int end, int width, int maxWidth) {
		// the last line
		if (end - start == 0 || end == words.length - 1) {
			StringBuffer result = new StringBuffer(words[start]);
			for (int index = start + 1; index <= end; index++) {
				result.append(" ").append(words[index]);
			}
			for (int length = result.length(); length < maxWidth; length++) {
				result.append(" ");
			}
			return result.toString();
		}
		int evenSpace = (maxWidth - width) / (end - start);
		int extraSpaces = (maxWidth - width) % (end - start);
		StringBuffer result = new StringBuffer(words[start]);
		for (int index = start + 1; index <= end; index++) {
			// add the must be space at first
			result.append(" ");
			for (int space = 0; space < evenSpace; space++) {

				result.append(" ");
			}
			if (extraSpaces > 0) {
				result.append(" ");
				extraSpaces--;
			}
			result.append(words[index]);
		}
		return result.toString();
	}
}
