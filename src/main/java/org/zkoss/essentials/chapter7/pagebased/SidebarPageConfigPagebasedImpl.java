/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter7.pagebased;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.zkoss.essentials.services.SidebarPage;
import org.zkoss.essentials.services.SidebarPageConfig;

public class SidebarPageConfigPagebasedImpl implements SidebarPageConfig{
	
	HashMap<String,SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
	public SidebarPageConfigPagebasedImpl(){		
		pageMap.put("fn1", new SidebarPage("Home", "Home", "images/basic-home.ico", "http://localhost:8080/essentials"));
		pageMap.put("fn2", new SidebarPage("master", "Master - Employee", "images/employee.png", "/master/index_employee.zul"));
		pageMap.put("fn3", new SidebarPage("master", "Transaction - Payroll", "images/employee.png", "/master/index_employee.zul"));
		pageMap.put("fn4", new SidebarPage("master", "Utility - Export/Import", "images/employee.png", "/master/index_employee.zul"));
	}
	
	public List<SidebarPage> getPages(){
		return new ArrayList<SidebarPage>(pageMap.values());
	}
	
	public SidebarPage getPage(String name){
		return pageMap.get(name);
	}
	
}