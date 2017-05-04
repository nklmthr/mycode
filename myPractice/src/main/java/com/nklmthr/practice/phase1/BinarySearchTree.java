package com.nklmthr.practice.phase1;


public class BinarySearchTree {
	public static void main(String[] args) {
		
		BST b = new BST();
		b.insert(2);
		b.insert(1);
		b.insert(5);
		b.insert(6);
		b.insert(8);
		b.display(b.root);
	}
}

class MyNode {
	public MyNode(int value2) {
		value = value2;
	}

	int value;
	MyNode left;
	MyNode right;
}

class BST {
	MyNode root = null;

	public void display(MyNode root) {
		if (root != null) {
			System.out.print(root.value);
			display(root.left);
			System.out.println(" ");
			display(root.right);
			System.out.println(" ");
		}
	}

	void insert(int value) {
		MyNode node = new MyNode(value);

		if (root == null) {
			root = node;
			return;
		}

		MyNode current = root;
		MyNode parent = null;
		while (true) {
			parent = current;
			if (value < current.value) {
				current = current.left;
				if (current == null) {
					parent.left = node;
					return;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = node;
					return;
				}
			}
		}
	}
}
