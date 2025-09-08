package com.kh.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.Template;
import com.kh.model.dao.EmployeeDAO;
import com.kh.model.vo.Employee;

public class EmployeeService {

	private EmployeeDAO employeeDao = new EmployeeDAO();
	
	public List<Employee> findAll() {
		
		SqlSession session = Template.getSqlSeeion();
		
		List<Employee> employees = EmployeeDAO.findAll(session);
		
		session.close();
		
		return employees;
	}
	
	public List<Employee> findByDept() {
		
		SqlSession session = Template.getSqlSeeion();
		
		List<Employee> employees = employeeDao.findByDept(session);
		
		session.close();
		
		return employees;
		
	}
	
}
