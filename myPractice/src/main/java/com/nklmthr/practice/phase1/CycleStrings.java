package com.nklmthr.practice.phase1;

import java.util.ArrayList;
import java.util.List;

public class CycleStrings {

	public static void main(String[] args) {
		String a = "0011";
		String b = "1011";

		List<Character> al = new CircularList<Character>();
		for (int i = 0; i < a.length(); i++) {
			al.add(i, a.charAt(i));
		}

		List<Character> bl = new CircularList<Character>();
		for (int i = 0; i < a.length(); i++) {
			bl.add(i, b.charAt(i));
		}
		List<Character> pos = new CircularList<Character>();
		for (int i = 0; i < al.size(); i++) {
			pos.add(i, '0');
		}
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) != bl.get(i)) {
				pos.set(i, '1');
			}
		}
		print(al, bl, pos);

		int start = 3;
		while (!allSet(al, bl, pos)) {
			System.out.print(start + "= ");
			print(al, bl, pos);
			if (pos.get(start) != 0) {
				transform(al, start);
			}
			start++;
			
		}

	}

	private static void print(List<Character> al, List<Character> bl, List<Character> pos) {
		System.out.print("a=");
		for (int i = 0; i < pos.size(); i++) {
			System.out.print(al.get(i));
		}

		System.out.print(" b=");
		for (int i = 0; i < pos.size(); i++) {
			System.out.print(bl.get(i));
		}
		System.out.print(" pos=");
		for (int i = 0; i < pos.size(); i++) {
			System.out.print(pos.get(i));
		}

		System.out.println();
	}

	private static void transform(List<Character> al, int start) {
		if (al.get(start) == '1')
			al.set(start, '0');
		else
			al.set(start, '1');
		if (al.get(start + 1) == '1')
			al.set(start + 1, '0');
		else
			al.set(start + 1, '1');
		if (al.get(start + 2) == '1')
			al.set(start + 2, '0');
		else
			al.set(start + 2, '1');
	}

	private static boolean allSet(List<Character> al, List<Character> bl, List<Character> pos) {
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) != bl.get(i)) {
				pos.set(i, '1');
			}else{
				pos.set(i, '0');
			}
		}
		for (int i = 0; i < pos.size(); i++) {
			if (pos.get(i) == '1')
				return false;
		}
		return true;
	}

}

class CircularList<E> extends ArrayList<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public E set(int index, E element) {
		if (index >= size()) {
			return super.set(index % size(), element);
		}
		return super.set(index, element);
	}

	@Override
	public E get(int index) {
		if (index >= size()) {
			return super.get(index % size());
		}
		return super.get(index);
	}
}