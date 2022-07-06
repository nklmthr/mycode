package com.myfiannce.home.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfiannce.home.model.Employee;
import com.myfiannce.home.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	
	public Employee createEmployee(Employee e) {
		Employee retun = repository.save(e);
		return retun;
	}
	
	public List<Employee> getAllEmployees(){
		Iterable<Employee> lsit = repository.findAll();
		List<Employee> targetCollection = new ArrayList<Employee>();
		CollectionUtils.addAll(targetCollection, lsit.iterator());
		return targetCollection;
	}
}
