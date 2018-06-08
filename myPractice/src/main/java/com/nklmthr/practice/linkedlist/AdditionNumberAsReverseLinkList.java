package com.nklmthr.practice.linkedlist;

public class AdditionNumberAsReverseLinkList extends LinkList {
	public static void main(String[] args) {
		LinkList<Integer> l1 = new LinkList<Integer>();
		LinkList<Integer> l2 = new LinkList<Integer>();

		l1.add(4);
		l1.add(8);
		l1.add(8);
		l1.add(8);
		l1.add(8);
		l1.printLinkList();
		l2.add(4);
		l2.add(9);
		l2.add(4);
		l2.add(9);
		l2.add(4);
		l2.add(9);
		l2.printLinkList();
		addNumber(l1, l2);

	}

	private static void addNumber(LinkList<Integer> l1, LinkList<Integer> l2) {
		LinkList<Integer> result = new LinkList<Integer>();
		Node l1Start = l1.getStart();
		Node l2Start = l2.getStart();
		int carry = 0;
		while (l1Start != null && l2Start != null) {
			int sum = Integer.valueOf(l1Start.obj.toString()) + Integer.valueOf(l2Start.obj.toString()) + carry;
			carry = sum / 10;
			sum = sum % 10;
			result.add(sum);
			l1Start = l1Start.next;
			l2Start = l2Start.next;
		}
		while(l1Start!=null){
			int sum = Integer.valueOf(l1Start.obj.toString())+carry;
			carry = sum / 10;
			sum = sum % 10;
			result.add(sum);
			l1Start = l1Start.next;
		}
		while(l2Start!=null){
			int sum = Integer.valueOf(l2Start.obj.toString())+carry;
			carry = sum / 10;
			sum = sum % 10;
			result.add(sum);
			l2Start = l2Start.next;
		}
		if(carry >0){
			result.add(carry);
		}
		result.printLinkList();
	}


}
