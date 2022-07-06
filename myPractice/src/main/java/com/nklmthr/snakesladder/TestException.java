package com.nklmthr.snakesladder;

public class TestException {
	public static void main(String[] args) {
		try {
			method1();
		} catch (MyExc1 e) {
			System.out.print("MyExc1");
		} catch (MyExc2 e) {
			System.out.print("MyExc2");
		} catch (Exception e) {
			System.out.print("Exception");
		} finally {
		}
	}

	public static void method1() throws Exception {
		try {
			System.out.print(1);
			method2();
		} catch (Exception e) {
			throw new MyExc2();
		} finally {
			System.out.print(2);
			throw new MyExc1();
		}
	}

	private static void method2() throws Exception {
		try {
			throw new MyExc1();
		} catch (Exception y) {
		} finally {
			System.out.print(3);
			throw new Exception();
		}
	}

	static class MyExc1 extends Exception {
	}

	static class MyExc2 extends Exception {
	}
}