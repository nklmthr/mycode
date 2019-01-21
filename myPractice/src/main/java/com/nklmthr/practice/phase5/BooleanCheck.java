package com.nklmthr.practice.phase5;

public class BooleanCheck {

	public static void main(String[] args) {
		
		//String s = 2>3 && 3>2 ? "x": 1>2 && 2>1? "y": null;
		
		String s1 = "fattura.xml.xml";
		System.out.println(s1.replace(".xml", ".pdf"));
		
		String s = 2>3 && 3>2 ? "x": 1<2 && 2>1? "y": null;
		System.out.println(s);
		
		
		if(check1() && check2()){
			System.out.println("and check 12");
		}
		System.out.println("******");
		if(check2() && check1()){
			System.out.println("and check 21");
		}
		System.out.println("******");
		if(check1() || check2()){
			System.out.println("or check 12");
		}
		System.out.println("******");
		if(check2() || check1()){
			System.out.println("or check 21");
		}
	}

	static boolean check1(){
		System.out.println("Check 1");
		return true;
	
	
	}
	

	static boolean check2(){
		System.out.println("Check 2");
		return false;
	}

	
}
