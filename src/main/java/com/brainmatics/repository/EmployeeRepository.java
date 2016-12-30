package com.brainmatics.repository;

import java.util.Set;

import com.brainmatics.domain.Employee;

public interface EmployeeRepository {

	public Set<Employee> findAll();
	public Set<Employee> search(String keyword);
	
	public Employee findOne(String id);	
	
	public void insert(Employee emp);
	
	public Integer getLastId();
}
