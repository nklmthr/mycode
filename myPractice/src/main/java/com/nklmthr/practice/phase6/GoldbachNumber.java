package com.nklmthr.practice.phase6;

import java.util.Scanner;

public class GoldbachNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int i, j, n, k, b = 0, c = 0, sum = 0;
		System.out.println("Enter the Number: ");
		// reads an integer from the user
		int num = sc.nextInt();
		// assigning the variable to another variable k
		k = num;
		// defining two arrays with capacity equal to the number
		int arr1[] = new int[num];
		int arr2[] = new int[num];
		// checks if the number is even or not
		if (num % 2 != 0) {
			// prints if the number is not even
			System.out.println("Invalid Input, Number is Odd.");
		}
		// executes if the number is even
		else {
			// loop used for finding prime numbers
			for (i = 1; i <= num; i++) {
				for (j = 1; j <= i; j++) {
					// condition if number is divisible
					if (i % j == 0) {
						// increments the value of c, initially it is 0
						c++;
					}
				}
				// condition for odd Prime numbers
				if ((c == 2) && (i % 2 != 0)) {
					// stores odd prime numbers
					arr1[b] = i;
					// stores odd prime numbers
					arr2[b] = i;
					// increments the value of b by 1
					b++;
				}
				c = 0;
			}
			System.out.println("Odd and Prime Pairs are: ");
			for (i = 0; i < b; i++) {
				for (j = i; j < b; j++) {
					// calculates the sum of two odd prime numbers
					sum = arr1[i] + arr2[j];
					// checks the sum is equal to the given number or not
					if (sum == k) {
						// prints pair of odd prime numbers
						System.out.print(arr1[i] + " , " + arr2[j]);
						System.out.println();
					}
				}
			}
			System.out.println(k + " is a Goldbach number.");
		}
	}

}
