package com.brainmatics.repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.brainmatics.domain.Employee;

@Service("employeeRESTRepository")
public class EmployeeRESTRepository implements EmployeeRepository {

	@Override
	public Set<Employee> findAll() {
		Set<Employee> employees = new HashSet<>();
		
		try {

		
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/posts/1");
		
			HttpResponse response = client.execute(request);
			String json = EntityUtils.toString(response.getEntity());
			JSONObject jsonObj = new JSONObject(json);
			Employee employee = new Employee(jsonObj.getInt("userId"), 
					"Yauri Attamimi", jsonObj.getString("title"), "IT", "/images/no-photo.png", 
					jsonObj.getString("body"));
			employees.add(employee);
			
		} catch (IOException ioe) {			
			System.err.println(ioe);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return employees;
	}

	@Override
	public Set<Employee> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Employee emp) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}

}
