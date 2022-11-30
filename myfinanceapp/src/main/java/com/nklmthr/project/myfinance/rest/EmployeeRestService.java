package com.nklmthr.project.myfinance.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nklmthr.project.myfinance.model.Employee;
import com.nklmthr.project.myfinance.service.EmployeeService;

@Controller
public class EmployeeRestService {
	
	@Autowired
	private EmployeeService service;

	@GetMapping("/employee")
    @ResponseBody
    public List<Employee> welcomeUser() {
        return service.getAllEmployees();
	}
}
