package com.brainmatics.domain;

import lombok.Data;
import lombok.Getter;

public @Data class Employee {

	@Getter
	private Integer id;
	@Getter
	private String fullName;
	@Getter
	private String position;
	@Getter
	private String department;
	@Getter
	private String pastExperienceDesc;
	
	private String preview;
	
	public Employee() {}
	
	public Employee(Integer id, String fullName, String position, String department,  String preview, String pastExperienceDesc){
		this.id = id;
		this.fullName = fullName;
		this.position = position;
		this.department = department;
		this.preview = preview;
		this.pastExperienceDesc = pastExperienceDesc;
	}		
}
