package com.myfiannce.home.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myfiannce.home.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
