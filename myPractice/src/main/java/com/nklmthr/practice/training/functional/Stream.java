package com.nklmthr.practice.training.functional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import javax.imageio.*;
public class Stream {

//	public static void main(String[] args) {
//		List<String> words = Arrays.asList("hi", "hello", "World", "Fantasy");
//		words.forEach(s -> System.out.println("  "+s));
//		System.out.println("***************");
//		words.forEach(System.out::println);
//		List<String> results = words.stream().map(s -> s.concat("!")).collect(Collectors.toList());
//		results.forEach(System.out::println);
//		System.out.println("***************");
//		List<String> results2 = words.stream().filter(s -> s.length() <6).collect(Collectors.toList());
//		results2.forEach(System.out::println);
//		System.out.println("***************");
//		
//		
//		
//		int[] array = new Random().ints(10000000, 1, 999999).toArray();
//		long start = System.currentTimeMillis();
//		int[] result = IntStream.of(array).parallel().sorted().toArray();
//		System.out.println((System.currentTimeMillis() - start)+"msec");
//		start = System.currentTimeMillis();
//		int[] result1 = IntStream.of(array).sorted().toArray();
//		System.out.println((System.currentTimeMillis() - start)+"msec".toUpperCase());
//		System.out.println("***************");
//		String result3 = words.stream().reduce("", (s1,s2) -> s1.toUpperCase()+s2.toUpperCase());
//		System.out.println(result3);
//		System.out.println("***************");
//		
//		String result4 = words.stream().map(s -> s.toUpperCase()).reduce("", (s1,s2) -> s1+" "+s2);
//		System.out.println(result4);
//		System.out.println("***************");
//		
//		String result5 = words.stream().map(s -> s.toUpperCase()+",").reduce("", (s1,s2) -> s1+s2);
//		System.out.println(result5);
//		System.out.println("***************");
//		
//		double[] doubles = new Random().doubles(10000000).toArray();
//		double result6 = DoubleStream.of(doubles).map(d -> Math.sqrt(d)).sum();
//		System.out.println("result6 ="+result6);
//		System.out.println("***************");
//		
//		
//		double result7 = DoubleStream.of(doubles).parallel().map(d -> Math.sqrt(d)).sum();
//		System.out.println("result7 ="+result7);
//		System.out.println("***************");
//	}
	

}
