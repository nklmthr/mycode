package com.nklmthr.practice.phase2;

public class AbstractClassVariable {

	public static void main(String[] args) {
		A a = new B();
		System.out.println(a.check);
		System.out.println(a.isCheck());
		
		B b = new B();
		System.out.println(b.check);
		System.out.println(b.isCheck());
		
	}
}

abstract class A{
	boolean check = true;

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
}

class B extends A{
	boolean check = false;
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
}