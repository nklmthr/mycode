package com.nklmthr.practice.phase2;

import java.util.HashSet;
import java.util.Set;

public class Hashing {

	public static void main(String[] args) {
		Employee e1 = new Employee("one");
		Employee e2 = new Employee("two");
		Employee e3 = new Employee("three");
		Employee e4 = new Employee("four");
		Employee e5 = new Employee("five");
		Employee e6 = new Employee("six");
		
		Set<Employee> set = new HashSet<Employee>();
		set.add(e1);
		set.add(e2);
		System.out.println(set.contains(e3));
		set.add(e3);
		set.add(e4);
		set.add(e5);
		set.add(e6);
		System.out.println(set.size());
	}
}

class Employee{
	String name;

	public Employee(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	
}
