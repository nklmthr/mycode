package com.nklmthr.practice.training.functional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

	private static final List<Employee> SAMPLE_EMPLOYEES =
			Arrays.asList(
			new Employee("Harry", "Hacker", 1, 234567),
			new Employee("Polly", "Programmer", 2, 333333),
			new Employee("Cody", "Coder", 8, 199999),
			new Employee("Devon", "Developer", 11, 175000),
			new Employee("Desiree", "Designer", 14, 212000),
			new Employee("Archie", "Architect", 16, 144444),
			new Employee("Tammy", "Tester", 19, 166777),
			new Employee("Sammy", "Sales", 21, 45000),
			new Employee("Larry", "Lawyer", 22, 33000),
			new Employee("Amy", "Accountant", 25, 85000) );
			public static List<Employee> getSampleEmployees() {
			return(SAMPLE_EMPLOYEES);
			}
	public static void main(String[] args) throws IOException {
		
		List<String> list = new ArrayList<>();
		int[] array = new Random().ints(100000, 1, 999999).toArray();
		IntStream.of(array).forEach(System.out::println);
		
		
////		URI inputFileName = null;
////		List<String> words =
////				Files.lines(Paths.get(inputFileName))
////				.filter(s -> s.length() == 4)
////				.map(String::toUpperCase)
////				.distinct()
////				.sorted()
////				.collect(Collectors.toList());
////				URI outputFileName = null;
////				Files.write(Paths.get(outputFileName), words,
////				Charset.defaultCharset());
//		
//		
////		String s[] = new String[] { "Helloadsas", "World", "How", "are", "u" };
////		TwoStringPredicateImpl<String> t1 = new TwoStringPredicateImpl<String>();
////		System.out.println(t1.check());
////		System.out.println(TwoStringPredicate.myCheck());
////		System.out.println(t1.myCheck());
////		
////		List<String> list = new ArrayList<>();
////		list.add("tes");
////		list.add("test1");
//		//List<String> result = allMatches(list, s -> s.length() < 4);
//		//List<String> result = allMatches(list, s -> s.contains("1"));
////		List<String> result = transformedList(list, s -> s + "!");
////		List<Integer> nums = Arrays.asList(1, 10, 100, 1000, 10000);
////		List<Integer> bigNums = allMatches(nums, n -> n>500);
////		bigNums.forEach(System.out::println);
////		
////		List<String> result = transformedList(list, String::toUpperCase);
////		result.forEach(System.out::println);
////		
//		
////		list.forEach(s1 -> s1=s1.replaceAll("tes","set"));
////		list.forEach(System.out::println);
////		List<Employee> employees = new ArrayList<>();
////		Predicate<Employee> isRich= e -> e.getSalary() > 200000;
////		Predicate<Employee> isEarly= e -> e.getId() <= 10;
//		//allMatches(employees, isRich.and(isEarly));
//		
//		// Arrays.sort(s, (s1, s2) -> {
//		// if (s1.contains("e") && !s2.contains("e")) {
//		// return Integer.MAX_VALUE;
//		// }
//		// if (!s1.contains("e") && s2.contains("e")) {
//		// return Integer.MIN_VALUE;
//		// }
//		// return s1.charAt(0) - s2.charAt(0);
//		// });
//		// Arrays.sort(s, (s1,s2) -> eChecker(s1, s2));
////		String p = betterString((s1, s2) -> s1.length() > s2.length(), s[0],s[1]);
////		System.out.println(p);
////		for (int i = 0; i < s.length; i++) {
////			System.out.println(s[i]);
////		}
////		Collection c;
	}

	public static int eChecker(String s1, String s2) {
		if (s1.contains("e") && !s2.contains("e")) {
			return Integer.MAX_VALUE;
		}
		if (!s1.contains("e") && s2.contains("e")) {
			return Integer.MIN_VALUE;
		}
		return s1.charAt(0) - s2.charAt(0);
	}

	public static <T> T betterString(TwoStringPredicate<T> func, T s1, T s2) {
		return s2;
//		if (func.betterString(s1, s2)) {
//			return s1;
//		} else {
//			return s2;
//		}
	}
	
	public static <T> List<T> allMatches(List<T> list, Predicate<T> predicate){
		List<T> returnList = new ArrayList<>();
		for(T obj : list){
			if(predicate.test(obj)){
				returnList.add(obj);
			}
		}
		return returnList;
	}
	
	public static <T> List<T> transformedList(List<T> list, Function<T, T> function){
		List<T> returnList = new ArrayList<>();
		for(T obj : list){
			returnList.add(function.apply(obj));
		}
		return returnList;
	}
}
