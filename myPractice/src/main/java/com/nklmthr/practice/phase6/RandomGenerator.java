package com.nklmthr.practice.phase6;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerator {

	public static void main(String[] args) {
		String s = RandomStringUtils.randomAlphanumeric(10);
		System.out.println(s);
	}

}
