package com.nklmthr.practice.y2019;

public class Node<T extends Comparable<?>> {
	Node<T> left, right;
	T data;

	public Node(T data) {
		this.data = data;
	}
}
