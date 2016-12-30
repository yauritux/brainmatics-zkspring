package com.brainmatics;

import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.brainmatics.domain.Employee;
import com.brainmatics.query.service.EmployeeQueryService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EmployeeController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1513916546741919165L;
	
	@Wire
	private Textbox keywordBox;
		
	@Wire
	private Listbox employeeListbox;
	
	@Wire
	private Label idAndNameLabel;
	
	@Wire
	private Label positionLabel;
	
	@Wire
	private Label deptLabel;
	
	@Wire
	private Label departmentLabel;
	
	@Wire
	private Textbox department;
	
	@Wire
	private Label summaryLabel;
	
	@Wire
	private Label pastExperienceDescLabel;
	
	@Wire
	private Textbox summary;
	
	@Wire
	private Image previewImage;
	
	@Wire
	private Button btnEdit;
	
	@Wire
	private Button btnCancel;
	
	@Wire
	private Window winEmployee;
	
	//private EmployeeQueryService employeeQueryService = new EmployeeQueryServiceImpl();
	
	@WireVariable
	private EmployeeQueryService employeeQueryService;
	
	@Listen("onClick = #searchButton")
	public void search() {
		System.out.println("employeeQuerySErvice = " + employeeQueryService);
		String keyword = keywordBox.getValue();
		Set<Employee> result = employeeQueryService.search(keyword);
		employeeListbox.setModel(new ListModelList<Employee>(result));
	}
	
	@Listen("onSelect = #employeeListbox")
	public void showDetail() {
		Employee selected = employeeListbox.getSelectedItem().getValue();
		previewImage.setSrc(selected.getPreview());
		idAndNameLabel.setValue("#00" + selected.getId() + "-" + selected.getFullName().toUpperCase());
		positionLabel.setValue(selected.getPosition());
		
		deptLabel.setVisible(true);
		departmentLabel.setValue(selected.getDepartment());
		departmentLabel.setVisible(true);
		department.setValue(selected.getDepartment());		
		department.setVisible(false);
		
		summaryLabel.setVisible(true);
		pastExperienceDescLabel.setValue(selected.getPastExperienceDesc());
		pastExperienceDescLabel.setVisible(true);
		summary.setValue(selected.getPastExperienceDesc());
		summary.setVisible(false);
		
		btnEdit.setAttribute("empid", selected.getId());
		btnEdit.setVisible(true);		
	}
	
	@Listen("onClick = #btnNew")
	public void showEmployeeForm(Event e) {
		Window window = (Window) Executions.createComponents("/master/employee_form.zul", winEmployee, null);
		window.doModal();
	}
	
	@Listen("onClick = #btnEdit")
	public void editEmployee() {
		System.out.println("Employee ID : " + btnEdit.getAttribute("empid"));
		if (btnEdit.getLabel().equalsIgnoreCase("Edit")) {
			department.setDisabled(false);
			department.setVisible(true);
			departmentLabel.setVisible(false);
			summary.setDisabled(false);
			summary.setVisible(true);
			pastExperienceDescLabel.setVisible(false);
			btnEdit.setLabel("Save");
			btnCancel.setVisible(true);
		} else if (btnEdit.getLabel().equalsIgnoreCase("Save")) {
			summary.setVisible(false);
			department.setVisible(false);
		}
	}
	
	@Listen("onClick = #btnCancel")
	public void cancelEdit() {
		setComponent(true);
		//btnCancel.setVisible(false);
		btnEdit.setLabel("Edit");
		//department.setDisabled(true);
		//department.setVisible(false);
		departmentLabel.setVisible(true);
		summary.setDisabled(true);
		summary.setVisible(false);
		pastExperienceDescLabel.setVisible(true);
	}
	
	private void setComponent(boolean flag) {
		btnCancel.setVisible(!flag);
		department.setDisabled(flag);
		departmentLabel.setVisible(flag);
	}
}