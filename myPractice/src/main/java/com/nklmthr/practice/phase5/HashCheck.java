package com.nklmthr.practice.phase5;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HashCheck {

	public static void main(String[] args) {
		Hash h1 = new Hash("h1");
		Hash h2 = new Hash("h2");
		Hash h3 = new Hash("h1");
		Hash h4 = new Hash("h4");
		Set set = new HashSet();
		set.add(h1);
		set.add(h2);
		set.add(h3);
		set.add(h4);
		System.out.println(set.size());
		
		String s1 = new String("Aa");
		String s2 = new String("BB");
		String s3 = "Aa";
		System.out.println(s1.hashCode()+"::::"+s2.hashCode()+":::"+s3.hashCode());
		System.out.println(s1==s3);
		System.out.println();
	}

}

class Hash{
	public String number;

	public Hash(String string) {
		number = string;
	}

	@Override
	public int hashCode() {
		return new Random().nextInt(10000); 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hash other = (Hash) obj;
		if (number != other.number)
			return false;
		return true;
	}
	
}
