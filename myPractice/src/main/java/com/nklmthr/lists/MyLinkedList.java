package com.nklmthr.lists;

import java.util.LinkedList;

public class MyLinkedList {

	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		Node n1 = new Node(10);
		Node n2 = new Node(20);
		Node n3 = new Node(25);
		Node n4 = new Node(40);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		// iterateList(n1);
		addNodeAtEnd(n1);
		n1 = list.addNodeAtStart(n1);
		LinkedList e;
		iterateList(n1);
	}

	private Node addNodeAtStart(Node head) {
		//System.out.println("1*****4");
		//iterateList(head);
		Node n = new Node(60);
		n.next = head;
		head = n;	
		//System.out.println("2*****");
		//iterateList(head);
		return head;
	}

	private static void addNodeAtEnd(Node head) {
		Node temp = head;		
		while (temp.next != null) {
			// System.out.println(temp.data);
			temp = temp.next;
		}
		temp.next = new Node(50);
	}

	private static void iterateList(Node head) {
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
}

