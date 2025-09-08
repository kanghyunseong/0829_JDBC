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
		
		return session.selectList("employeeMapper.findByAll");
	}

	public List<Employee> findByDept(SqlSession session) {
		return session.selectList("employeeMapper.findByDept");
	}
	
	

	
}