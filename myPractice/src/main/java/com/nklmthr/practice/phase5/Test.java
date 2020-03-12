package com.nklmthr.practice.phase5;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main1(String[] args) {
		int MAXIMUM_CAPACITY = 1 << 31;
		System.out.println(Integer.MAX_VALUE);
		int i = Integer.MAX_VALUE - 1;
		int count = 0;
		while (i > 0) {
			i = i / 2;
			System.out.println(i);
			count++;
		}
		System.out.println(count);
	}
	
	public static void main(String args[]){
		String str = "developer.ariba.com ,	buyer.sap.com 	,		supplier.sap.com";
		str="";
		String[] s = str.split("\\s*,\\s*");
		List<String> list = Arrays.asList(s);
		System.out.println(list.contains("developer.ariba.com"));
		for(String sr: s){
			System.out.println("**"+sr+"**");
		}
	}
}
