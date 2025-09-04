package com.kh.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.function.Function;

import com.kh.model.DAO.EmployeeDAO;
import com.kh.model.vo.Employee;

public class EmployeeService {

	private Connection conn = null;

	public EmployeeService() {
		super();
		this.conn = getConnection();
	}
	
	private <T> T executeQuery(Function<Connection, T> daoFunction) {
		Connection conn = null;
		T result = null;
		
		conn = getConnection();
		result = daoFunction.apply(conn);
		
		close(conn);
		
		return result;
	}
	
	public List<Employee> findAll() {
		
		return executeQuery(new EmployeeDAO()::findAll);
		
	}

	public List<Employee> findByDept(String searchDept) {
		
		
		List<Employee> employees = new EmployeeDAO().findByDept(conn, searchDept);
		
		close(conn);
		
		return employees;
	}
	
	public List<Employee> findByJob(String searchJob) {
		
		List<Employee> employees = new EmployeeDAO().findByJob(conn, searchJob);
		
		close(conn);
		
		return employees;
		
	}

	public Employee findByEmpId(String empId) {
		
		Employee employee = new EmployeeDAO().findByEmpId(conn, empId);
		
		close(conn);
		
		return employee;
	}

	public List<Employee> costTop() {
		
		List<Employee> employees = new EmployeeDAO().costTop(conn);
		
		return employees;
	}
}
