package com.kh.controller;

import java.util.List;

import com.kh.model.dto.EmployeeDTO;
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

	public List<Employee> costBottom() {

		List<Employee> employees = new EmployeeService().costBottom();

		return employees;
	}

	public int save(String empId, String empName, String empNo, String jobCode, String salLevel) {

		Employee employee = new Employee(empId, empName, empNo, jobCode, salLevel);

		int result = new EmployeeService().save(employee);

		return result;
	}

	public int update(String empId, String deptCode, String jobCode, int salary) {

		EmployeeDTO dto = new EmployeeDTO(empId, deptCode, jobCode, salary);
		int result = new EmployeeService().update(dto);
		return result;
	}

	public int delete(String empId) {
		
		int result = new EmployeeService().delete(empId);
		
		return result;
	}
}
