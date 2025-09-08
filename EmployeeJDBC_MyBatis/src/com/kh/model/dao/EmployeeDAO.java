package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.JDBCTemplate;
import com.kh.model.dto.EmployeeDTO;
import com.kh.model.vo.Employee;

public class EmployeeDAO {

	public static List<Employee> findAll(SqlSession session) {
		
		return session.selectList("employeeMapper.findAll");
	}

	public List<Employee> findByDept(SqlSession session, String searchDept) {
		return session.selectList("employeeMapper.findByDept", searchDept);
	}

	public List<Employee> findByJob(SqlSession session, String searchJob) {
		return session.selectList("employeeMapper.findByJob", searchJob);
	}

	public Employee findByEmpId(SqlSession session, String empId) {
		return session.selectOne("employeeMapper.findByEmpId", empId);
	}

	public List<Employee> costTop(SqlSession session) {
		return session.selectList("employeeMapper.costTop");
	}

	public List<Employee> costBottom(SqlSession session) {
		return session.selectList("employeeMapper.costBottom");
	}

	public int save(SqlSession session, Employee employee) {
		return session.insert("employeeMapper.save", employee);
	}

	public int update(SqlSession session, EmployeeDTO dto) {
		return session.update("employeeMapper.update", dto);
	}

	public int delete(SqlSession session, String empId) {
		return session.delete("employeeMapper.delete", empId);
	}
	
	

	
}