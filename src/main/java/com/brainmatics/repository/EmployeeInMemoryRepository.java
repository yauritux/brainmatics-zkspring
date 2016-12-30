package com.brainmatics.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.brainmatics.domain.Employee;

@Service("employeeRepository")
public class EmployeeInMemoryRepository implements EmployeeRepository {
	
	private static int id = 1;

	// data model
	private static Set<Employee> employeeList = new HashSet<>();
	
	public EmployeeInMemoryRepository() {
		employeeList.add(new Employee(id++, "Mr.John", "Software Architect", "IT", "/images/no-photo.png", 
				"Has experienced as Software Architect for more than one decade. Formerly worked in Microsoft and Oracle for the same position"));
		employeeList.add(new Employee(id++, "Miss.Kimberly", "Recruitment Specialist", "HR", "/images/no-photo.png", 
				"Formerly worked for Oracle as Senior Marketing."));
	}

	@Override
	public Set<Employee> findAll() {
		return employeeList;
	}

	@Override
	public Set<Employee> search(String keyword) {
		return null;
	}

	@Override
	public Employee findOne(String id) {
		return null;
	}
	
	@Override
	public void insert(Employee employee) {
		employeeList.add(employee);
	}
	
	@Override
	public Integer getLastId() {
		int id = 0;
		for (Employee emp : this.findAll()) {
			id = emp.getId();
		}
		return id;
	}

}
