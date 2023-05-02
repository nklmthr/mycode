package com.nklmthr.functional;

public class AddNumberImpl implements AddNumber {

	@Override
	public int add(int aa, int bb) {
		AddNumber an = (a, b) -> a+b;
		return bb;	
	}

}
