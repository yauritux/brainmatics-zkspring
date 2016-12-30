package com.brainmatics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import com.brainmatics.command.service.EmployeeCommandService;
import com.brainmatics.command.service.impl.EmployeeCommandServiceImpl;
import com.brainmatics.domain.Employee;
import com.brainmatics.query.service.EmployeeQueryService;
import com.brainmatics.query.service.impl.EmployeeQueryServiceImpl;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EmployeeFormController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 8884391639683645721L;
	
	@Wire
	private Textbox fullName;
	
	@Wire
	private Textbox department;
	
	@Wire
	private Textbox position;
	
	@Wire
	private Textbox summary;
	
	@WireVariable("employeeQueryService")
	private EmployeeQueryService empQueryService;
	
    //private EmployeeQueryService empQueryService = new EmployeeQueryServiceImpl();
	private EmployeeCommandService empCommandService = new EmployeeCommandServiceImpl();
	
	/*
	@Autowired
	void setEmployeeQueryService(EmployeeQueryService empQueryService) {
		this.empQueryService = empQueryService;
		System.out.println("EmployeeFormController::setEmployeeQueryService,empQueryService=" + this.empQueryService);
	}
	*/

	@Listen("onClick = #btnSubmit")
	public void submit() {
		System.out.println("saving...");
		System.out.println("empQueryService = " + empQueryService);
		Employee emp = new Employee(empQueryService.getLastId().intValue() + 1, 
				fullName.getValue().trim(), 
				position.getValue().trim(), 
				department.getValue().trim(), "/images/no-photo.png", 
				summary.getValue().trim());

		Textbox txtKeyword = null;
		Component keywordComponent = this.getSelf().getParent().query("#keywordBox");
		if (keywordComponent instanceof Textbox) {
			txtKeyword = (Textbox) keywordComponent;
		}
		txtKeyword.setValue(fullName.getValue().trim());

		empCommandService.insert(emp);
	}	
}
