package com.nklmthr.practice.phase1;

import java.util.Stack;

public class FIndPrimePairThatAddToResult {

	public static void main(String[] args) {
		// findPrimeNumbersThatAddToResult(25);
		// titleToNumber("AZA");
		//gcd(4, 6);
		
		System.out.println(reverseString("((((([{()}[]]{{{[]}}})))))"));
	}
	
	public static String reverseString(String a) {
	    Stack<Character> s = new Stack<Character>();
	    
	    char[] c = a.toCharArray();
	    
	    for(int i=0;i<c.length;i++){
	        s.push(c[i]);
	    }
	    String ret="";
	    for(int i=s.size(); i>0;i--){
	        ret +=s.pop();
	        System.out.println(i+ret+s.size());
	        
	    }
	    return ret;
	}

	public static int gcd(int a, int b) {
		int first = a < b ? a : b;
		int second = a > b ? a : b;
		int rem = second % first;
		if (rem == 0) {
			return first;
		}
		while (rem != 0) {
			second = first;
			first = rem;
			rem = second%first;
		}
		
		return first;

	}

	public static int titleToNumber(String a) {
		int result = 0;
		char[] chars = a.toCharArray();

		for (int i = chars.length - 1; i >= 0; i--) {
			int thisNum = chars[i] - 'A';
			result += Math.pow(26, chars.length - i - 1) * (thisNum + 1);

			System.out.println("i=" + i + " chars.length-i=" + (chars.length - i) + " thisNum=" + thisNum
					+ " Math.pow(26, " + (chars.length - i) + ")=" + Math.pow(26, chars.length - i) + " thisNum"
					+ thisNum + " result=" + result);
		}
		System.out.println(result);
		return result;
	}

	private static void findPrimeNumbersThatAddToResult(int number) {
		int start = 1, end = number - 1;
		// System.out.println("start-" + isPrime(start));
		// System.out.println("end-" + isPrime(end));
		while (start <= end) {
			if (start + end == number && isPrime(start) && isPrime(end)) {
				System.out.println(start + " + " + end + " =" + number);
				return;
			} else if (start + end > number) {
				end--;
			} else {
				start++;
			}
		}

	}

	public static boolean isPrime(int A) {
		if (A == 1)
			return false;
		int upperLimit = (int) (Math.sqrt(A));
		for (int i = 2; i <= upperLimit; i++) {
			if ((i < A) && (A % i == 0))
				return false;
		}
		return true;
	}
}
