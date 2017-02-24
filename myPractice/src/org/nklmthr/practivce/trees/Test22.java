package org.nklmthr.practivce.trees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Test22 {
	public static void main(String args[]) throws Exception {
		/*
		 * Read input from stdin and provide input before running Use either of
		 * these methods for input
		 */
		// BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		// int N = Integer.parseInt(line);

		// Scanner
		/*
		 * Scanner s = new Scanner(System.in); int N = s.nextInt();
		 * 
		 * for (int i = 0; i < N; i++) { System.out.println("hello world"); }
		 */

		String str = line;

		int result = 0;
		if (validateExpression(str)) {
			result = evaluate(str);
		}
		System.out.println(result);

	}

	private static int evaluate(String str) {
		char chars[] = str.toCharArray();
		Stack<Integer> values = new Stack<Integer>();
		Stack<Character> operations = new Stack<Character>();

		for (int i = 0; i < chars.length; i++) {
			String num = "";
			if (chars[i] >= '0' && chars[i] <= '9') {
				while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
					num += chars[i];
					i++;
				}
				i--;
				values.push(Integer.parseInt(num));
				if (!operations.isEmpty() && i == chars.length - 1) {
					values.push(doOperation(values.pop(), operations.pop(), values.pop()));

				}
			} else if (chars[i] == '(') {
				operations.push(chars[i]);
			} else if (chars[i] == ')') {
				while (operations.peek() != '(') {
					values.push(doOperation(values.pop(), operations.pop(), values.pop()));
				}
				operations.pop();
				values.push(doOperation(values.pop(), operations.pop(), values.pop()));
			} else if ((chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/' || chars[i] == '%')) {

				while (!operations.isEmpty() && operations.peek() != '('
						&& !hasPrecedence(chars[i], operations.peek())) {
					values.push(doOperation(values.pop(), operations.pop(), values.pop()));

				}
				operations.push(chars[i]);

			}
			//System.out.println("Evaluating " + chars[i] + "values=" + values + " operations=" + operations);
		}
		return values.pop();
	}

	private static boolean hasPrecedence(Character operator1, Character operator2) {
		if (operator1 == '(' || operator2 == ')') {

		} else if (operator1 == '%' && (operator2 == '*' || operator2 == '/' || operator2 == '+' || operator2 == '-')) {
			return true;
		} else if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) {
			return true;
		}
		return false;

	}

	private static int doOperation(Integer value1, char operation, Integer value2) {
		//System.out.println("Evaluating " + value2 + " " + operation + " " + value1);
		if (operation == '+') {
			return (value2 - 0) + (value1 - 0);
		} else if (operation == '-') {
			return (value2 - 0) - (value1 - 0);
		} else if (operation == '*') {
			return (value2 - 0) * (value1 - 0);
		} else if (operation == '/') {
			return (value2 - 0) / (value1 - 0);
		} else if (operation == '%') {
			return (value2 - 0) % (value1 - 0);
		}
		return 0;
	}

	private static boolean validateExpression(String str) {
		char[] braces = { '(', ')' };
		char[] chars = str.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == braces[0]) {
				stack.push(braces[1]);
			}
			if (chars[i] == braces[1]) {
				if (!stack.isEmpty()) {
					stack.pop();
				} else {
					return false;
				}
			}
		}
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}

}
