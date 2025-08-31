package com.select;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeSelect {

	public static void main(String[] args) {

		// 0) 필요한 변수들 먼저 세팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rest = null;
		
		String sql = """
					SELECT
					       EMP_ID
					     , EMP_NAME
					     , EMP_NO
					     , EMAIL
					     , PHONE
					     , DEPT_CODE
					     , JOB_CODE
					     , SAL_LEVEL
					     , SALARY
					     , BONUS
					     , MANAGER_ID
					     , HIRE_DATE
					     , ENT_DATE
					     , ENT_YN
					  FROM
						   EMPLOYEE
				     ORDER
				        BY 
				           EMP_ID DESC
				      """;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 연결 성공");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02", "KHS021234");
			System.out.println("DB 연결 성공");
			
			stmt = conn.createStatement();
			System.out.println("Statement객체 생성!");
			
			rest = stmt.executeQuery(sql);
			
			while(rest.next()) {
				String empId = rest.getString("EMP_ID");
				String empName = rest.getString("EMP_NAME");
				String empNo = rest.getString("EMP_NO");
				String email = rest.getString("EMAIL");
				String phone = rest.getString("PHONE");
				String deptCode = rest.getString("DEPT_CODE");
				String jobCode = rest.getString("JOB_CODE");
				String salLevel = rest.getString("SAL_LEVEL");
				int salary = rest.getInt("SALARY");
				double bonus = rest.getDouble("BONUS");
				String managerId = rest.getString("MANAGER_ID");
				Date hireDate = rest.getDate("HIRE_DATE");
				Date entDate = rest.getDate("ENT_DATE");
				String entYn = rest.getString("ENT_YN");
				
				System.out.println("사원번호 : " + empId + ", 사원이름 : " + empName + ", 주민번호 : " + empNo
						   + ", 이메일 : " + email
						   + ", 전화번호 : " + phone
						   + ", 부서코드 : " + deptCode
						   + ", 직업코드 : " + jobCode
						   + ", 급여등급 : " + salLevel
						   + ", 급여 : " + salary
						   + ", 보너스 : " + bonus
						   + ", 관리자사번 : "+ managerId
						   + ", 입사일 : " + hireDate
						   + ", 퇴사일 : " + entDate
						   + ", 재직여부 : " + entYn
						   );
			}
		} catch (ClassNotFoundException e) {
			System.out.println("oracle.jdbc.driver.OracleDriver오타 없나요?");
		} catch (SQLException e) {
			System.out.println("jdbc:oracle:thin:@115.90.212.20:10000:XE 오타없나요?");
			System.out.println("사용자 계정명 / 비밀번호가 올바른가요 ?");
		} finally {
			try {
				if (rest != null && !rest.isClosed()) {
					rest.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
