package com.kh.model.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.model.DTO.EmployeeDTO;
import com.kh.model.vo.Employee;

public class EmployeeDAO {

	private Properties prop = new Properties();

	public EmployeeDAO() {
		try {
			prop.loadFromXML(new FileInputStream("resource/member-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> findAll(Connection conn) {

		List<Employee> employees = new ArrayList();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("findAll");

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Employee employee = new Employee(rset.getString("EMP_ID"), rset.getString("EMP_NAME"),
						rset.getInt("SALARY"), rset.getString("DEPT_TITLE"), rset.getString("JOB_NAME"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 사용이 다 끝난 JDBC용 객체 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return employees;
	}

	public List<Employee> findByDept(Connection conn, String searchDept) {

		List<Employee> employees = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("findByDept");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchDept);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Employee employee = new Employee(rset.getString("EMP_ID"), rset.getString("EMP_NAME"),
						rset.getInt("SALARY"), rset.getString("DEPT_TITLE"), rset.getString("JOB_NAME"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return employees;
	}

	public List<Employee> findByJob(Connection conn, String searchJob) {

		List<Employee> employees = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("findByJob");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchJob);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Employee employee = new Employee(rset.getString("EMP_ID"), rset.getString("EMP_NAME"),
						rset.getInt("SALARY"), rset.getString("DEPT_TITLE"), rset.getString("JOB_NAME"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return employees;
	}

	public Employee findByEmpId(Connection conn, String empId) {

		Employee employee = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("empId");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				employee = new Employee(rset.getString("EMP_ID"), rset.getString("EMP_NAME"), rset.getInt("SALARY"),
						rset.getString("DEPT_TITLE"), rset.getString("JOB_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return employee;
	}

	public List<Employee> costTop(Connection conn) {

		List<Employee> employees = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("costTop");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {

				Employee employee = new Employee(rset.getString("EMP_ID"), rset.getString("EMP_NAME"),
						rset.getInt("SALARY"));

				employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public List<Employee> costBottom(Connection conn) {

		List<Employee> employees = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("costBottom");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {

				Employee employee = new Employee(rset.getString("EMP_ID"), rset.getString("EMP_NAME"),
						rset.getInt("SALARY"));

				employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public int save(Connection conn, Employee employee) {

		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("save");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, employee.getEmpId());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setString(3, employee.getEmpNo());
			pstmt.setString(4, employee.getJobCode());
			pstmt.setString(5, employee.getSalLevel());

			result = pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int update(Connection conn, EmployeeDTO dto) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("update");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getSalary());
			pstmt.setString(2, dto.getDeptCode());
			pstmt.setString(3, dto.getJobCode());
			pstmt.setString(4, dto.getEmpId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int delete(Connection conn, String empId) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("delete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}