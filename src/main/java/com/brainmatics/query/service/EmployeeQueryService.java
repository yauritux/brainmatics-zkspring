package com.brainmatics.query.service;

import java.util.Set;

import com.brainmatics.domain.Employee;

public interface EmployeeQueryService {

	/**
	 * Retrieve all employees in the catalog.
	 * 
	 * @return all employees
	 */
	public Set<Employee> findAll();
	
	/**
	 * Search employees according to keyword in full name or position.
	 * 
	 * @param keyword for search
	 * @return list of employee that match the keyword
	 */
	public Set<Employee> search(String keyword);	
	
	public Integer getLastId();
}
