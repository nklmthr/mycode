package com.atlassian.election;

public class TestString {
	public static void main(String[] args) {
	String s1 = new String (new byte[] {117, 115, 101, 114, 73, 100}) ;;
	String s2 = new String(new byte[] {28, 32, 117, 0, 115, 0, 101, 0, 114, 0, 73, 0, 100, 0, 29, 32});
	String s3 = new String(new byte[] {-1, -2, 117, 0, 115, 0, 101, 0, 114, 0, 73, 0, 100, 0});
	System.out.println(s1);
	System.out.println(s2);
	System.out.println(s3);
			
	}
}
