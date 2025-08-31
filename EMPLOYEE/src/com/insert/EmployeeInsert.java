package com.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeInsert {

	public static void main(String[] args) {

		// 0) 필요한 변수들 먼저 세팅
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("사번을 입력해주세요 > ");
		int empId = sc.nextInt();
		sc.nextLine();
		
		System.out.print("사원명을 입력해주세요 > ");
		String empName = sc.nextLine();
		
		System.out.print("주민번호를 입력해주세요 > ");
		String empNo = sc.nextLine();
		
		System.out.print("이메일을 입력해주세요 > ");
		String email = sc.nextLine();
		
		System.out.print("전화번호를 입력해주세요 > ");
		String phone = sc.nextLine();
		
		System.out.print("부서코드를 입력해주세요 > ");
		String deptCode = sc.nextLine();
		
		System.out.print("직급번호를 입력해주세요 > ");
		String jobCode = sc.nextLine();
		
		System.out.print("급여등급을 입력해주세요 > ");
		String salLevel = sc.nextLine();
		
		System.out.print("급여를 입력해주세요 > ");
		int salary = sc.nextInt();
		
		System.out.print("보너스를 입력해주세요 > ");
		double bonus = sc.nextDouble();
		
		System.out.print("매니저번호를 입력해주세요 > ");
		int managerId = sc.nextInt();
		sc.nextLine();
		
		
		
		// 입사일 HIRE_DATE = SYSDATE
		String sql = "INSERT INTO EMPLOYEE "
		           + "VALUES('" + empId
		           + "', '" + empName + "'"
		           + ", '" + empNo + "'"
		           + ", '" + email + "'"
		           + ", '" + phone + "'"
		           + ", '" + deptCode + "'"
		           + ", '" + jobCode + "'"
		           + ", '" + salLevel + "'"
		           + ", " + salary
		           + ", " + bonus
		           + ", '" + managerId + "'"
		           + ", SYSDATE"
		           + ", NULL"
		           + ", 'N')";


		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이브 연결 성공");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02", "KHS021234");
			System.out.println("DB 연결 성공!");
			
			stmt = conn.createStatement();
			System.out.println("Statement객체 생성!");
			
			conn.setAutoCommit(false);
			
			result = stmt.executeUpdate(sql);
			System.out.println("SQL문 실행 성공!");
			
			
			if(result > 0) {
				conn.commit();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
