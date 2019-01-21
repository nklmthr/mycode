package com.nklmthr.practice.linkedlist;

import java.io.LineNumberReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class Node {
	Object obj;
	Node next;
}

public class LinkList<T> implements List<T> {
	private Node start;
	private int size = 0;

	public Node getStart() {
		return start;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object o) {
		Node current = start;
		while (current != null) {
			if (current.obj.equals(o)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public boolean add(T e) {
		Node current = start;
		while (current != null && current.next != null) {
			current = current.next;
		}
		if (size == 0) {
			current = new Node();
			current.obj = e;
			start = current;
		} else {
			current.next = new Node();
			current.next.obj = e;
		}
		size++;

		return true;
	}

	public boolean remove(Object o) {
		Node previous = null;
		Node current = start;
		if (current.obj.equals(o)) {
			start = current.next;
			current = start;
			size--;
			return true;
		}
		while (current != null) {
			if (current.obj.equals(o)) {
				previous.next = current.next;
				current.next = null;
				size--;
				return true;
			}
			previous = current;
			current = current.next;
		}
		return false;
	}

	public void printLinkList() {
		Node current = start;
		int count = 0;
		while (current != null) {
			System.out.print(current.obj + " -> ");
			current = current.next;
			if (count > 10) {
				System.out.println();
			}
		}
		System.out.println();
	}

	public Iterator<T> iterator() {
		return new Iterator() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Object next() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	public Object[] toArray() {
		LineNumberReader l;
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(int index, T element) {
		// TODO Auto-generated method stub

	}

	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
