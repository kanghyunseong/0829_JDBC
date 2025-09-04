package com.kh.controller;

import java.util.List;

import com.kh.model.service.EmployeeService;
import com.kh.model.vo.Employee;

public class EmployeeController {

	public List<Employee> findAll() {

		List<Employee> employees = new EmployeeService().findAll();

		return employees;
	}

	public List<Employee> findByDept(String searchDept) {
		
		List<Employee> employees = new EmployeeService().findByDept(searchDept);
		
		return employees;
	}
	
	public List<Employee> findByJob(String searchJob) {
		
		List<Employee> employees = new EmployeeService().findByJob(searchJob);
		
		return employees;
	}

	public Employee findByEmpId(String empId) {
		
		Employee employee = new EmployeeService().findByEmpId(empId);
		
		return employee;
	}

	public List<Employee> costTop() {
		
		List<Employee> employees = new EmployeeService().costTop();
		
		return employees;
		
	}
}
