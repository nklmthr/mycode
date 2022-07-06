package com.nklmthr.practice.phase6;

public class ReverseLinkedList {

	public static void main(String[] args) {
		Node A = new Node(1);
		Node n2 = new Node(2);
		A.next = n2;

		Node n3 = new Node(3);
		n2.next = n3;

		Node n4 = new Node(4);
		n3.next = n4;

		Node n5 = new Node(5);
		n4.next = n5;

		Node n6 = new Node(6);
		n5.next = n6;

		print(A);

		Node currentStart = null, previousStart = null;
		Node currentEnd = null, previousEnd = null;

		previousStart = findNodeStartAndEnd(A, 2);
		currentStart = previousStart.next;
		previousEnd = findNodeStartAndEnd(A, 4);
		currentEnd = previousEnd.next;
		System.out.println("currentStart=" + currentStart.value + ",previousStart=" + previousStart.value);
		System.out.println("currentEnd=" + currentEnd.value + ",previousEnd=" + previousEnd.value);

		Node current = currentStart;
		Node previous = previousStart;
		previousStart = currentEnd;
		currentEnd = currentStart;
		while (current != previousEnd) {
			Node temp= current;
			
			current.next = previous;
			current.next.next= temp;
			//current = 
		}
	}

	private static Node findNodeStartAndEnd(Node A, int value) {
		if (A == null) {
			return A;
		}
		if (A.next == null && A.value == value) {
			return A;
		}
		Node current = A;
		Node previous = null;
		while (current != null) {
			previous = current;
			current = current.next;
			if (current.value == value) {
				break;
			}
		}
		return previous;
	}

	public static void print(Node start) {
		if (start == null) {
			System.out.println("Empty List");
		} else {
			Node current = start;
			while (current != null) {
				System.out.print(current.value + " -> ");
				current = current.next;
			}
			System.out.println("xxx");
		}
	}

}

class Node {
	int value;
	Node next;

	public Node(int value) {
		this.value = value;
	}
}