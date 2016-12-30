package com.brainmatics.command.service.impl;

import com.brainmatics.command.service.EmployeeCommandService;
import com.brainmatics.domain.Employee;
import com.brainmatics.repository.EmployeeInMemoryRepository;
import com.brainmatics.repository.EmployeeRepository;

public class EmployeeCommandServiceImpl implements EmployeeCommandService {
	
	private static EmployeeRepository repository;
	
	public EmployeeCommandServiceImpl() {
		if (repository == null) {
			repository = new EmployeeInMemoryRepository();
		}
	}

	@Override
	public void insert(Employee emp) {
		repository.insert(emp);
	}
}
