package com.kh.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.Template;
import com.kh.model.dao.EmployeeDAO;
import com.kh.model.dto.EmployeeDTO;
import com.kh.model.vo.Employee;

public class EmployeeService {

	private EmployeeDAO employeeDao = new EmployeeDAO();

	public List<Employee> findAll() {

		SqlSession session = Template.getSqlSeeion();

		List<Employee> employees = EmployeeDAO.findAll(session);

		session.close();

		return employees;
	}

	public List<Employee> findByDept(String searchDept) {

		SqlSession session = Template.getSqlSeeion();

		List<Employee> employees = employeeDao.findByDept(session, searchDept);

		session.close();

		return employees;

	}

	public List<Employee> findByJob(String searchJob) {

		SqlSession session = Template.getSqlSeeion();

		List<Employee> employees = employeeDao.findByJob(session, searchJob);

		session.close();

		return employees;
	}

	public Employee findByEmpId(String empId) {

		SqlSession session = Template.getSqlSeeion();

		Employee employee = employeeDao.findByEmpId(session, empId);

		session.close();

		return employee;
	}

	public List<Employee> costTop() {

		SqlSession session = Template.getSqlSeeion();

		List<Employee> employees = employeeDao.costTop(session);

		session.close();

		return employees;

	}

	public List<Employee> costBottom() {

		SqlSession session = Template.getSqlSeeion();

		List<Employee> employees = employeeDao.costBottom(session);

		session.close();

		return employees;
	}

	public int save(Employee employee) {

		SqlSession session = Template.getSqlSeeion();

		int result = employeeDao.save(session, employee);

		if (result > 0) {
			session.commit();
		}

		session.close();

		return result;

	}

	public int update(EmployeeDTO dto) {
		
		SqlSession session = Template.getSqlSeeion();
		
		int result = employeeDao.update(session, dto);
		
		if(result > 0) {
			session.commit();
		}
		
		session.close();
		
		return result;
		
	}

	public int delete(String empId) {
		
		SqlSession session = Template.getSqlSeeion();
		
		int result = employeeDao.delete(session, empId);
		
		if(result > 0) {
			session.commit();
		}
		
		session.close();
		
		return result;
	}

}
