package com.brainmatics.query.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.brainmatics.domain.Employee;
import com.brainmatics.query.service.EmployeeQueryService;
import com.brainmatics.repository.EmployeeRepository;

@Service("employeeQueryService")
//@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EmployeeQueryServiceImpl implements EmployeeQueryService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeQueryServiceImpl() {}
	
	@Autowired
	@Qualifier("employeeRepository")	
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
		System.out.println("employeeRepository = " + this.employeeRepository);
	}

	@Override
	public Set<Employee> findAll() {
		//System.out.println("employeeRepository = " + employeeRepository);
		return employeeRepository.findAll();
	}

	@Override
	public Set<Employee> search(String keyword) {
		Set<Employee> result = new HashSet<>();		
		if (keyword != null && !keyword.trim().equals("")) {
			for (Employee e : this.findAll()) {
				if (e.getFullName().toLowerCase().contains(keyword.toLowerCase())
						|| e.getPosition().toLowerCase().contains(keyword.toLowerCase())) {
					result.add(e);
				}
			}
		} else {
			result = this.findAll();
		}
		return result;
	}

	@Override
	public Integer getLastId() {
		return employeeRepository.getLastId();
	}
}
