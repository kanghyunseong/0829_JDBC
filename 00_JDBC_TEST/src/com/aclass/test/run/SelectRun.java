package com.aclass.test.run;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectRun {

	public static void main(String[] args) {

		// 현재 작업중인 DB서버에서 각자 자신이 가지고있는 TB_STUDENT테이블의 모든 행 조회 Console에 출력
		// SELECT문
		// ResultSet(조회된 행들의 집합)
		// => ResultSet타입의 데이터를 받아서 뽑기

		// 0) 필요한 변수들 먼저 세팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rest = null;
		
		
		// 0단계 끝!
		// 요청할 SQL문도 미리 완성형태로 준비해드기
		/*
		 * String sql = "SELECT" + "STUDENT_ID" +", STUDENT_NAME" +", ENROLL_DATE"
		 * +"FROM" + "TB_STUDENT" +"ORDER" + "BY" +"ENROLL_DATE DESC";
		 */

		String sql = """
				     SELECT
				            STUDENT_ID
				          , STUDENT_NAME
				          , ENROLL_DATE
				       FROM
							TB_STUDENT
					  ORDER
					     BY
					        ENROLL_DATE DESC
				""";
		// 1. JDBC Driver등록
		// 리플렉션을 이용한 드라이버 클래스 로딩
		// 실행시점에 필요한 데이터베이스 드라이버를 동적으로 로드
		// 코드의 변경 없이 데이터베이스를 연결할 수 있도록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 연결 성공!");

			// 2. Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02", "KHS021234");
			System.out.println("DB 연결 성공");

			// 3. Statement객체 생성
			// 새 SQL편집기 실행
			stmt = conn.createStatement();
			System.out.println("Statement객체 생성!");

			// 4. SQL문 실행 후 결과 받기
			// 실행할 SQL문이 SELECT문인경우
			// stmt.executeQuery(sql) : ResultSet
			rest = stmt.executeQuery(sql);

			// ResultSet반환됨
			// 커서를 조작

			while (rest.next()) {
				// 커서를 조작했을 때 행이 존재한다면 true / 행이 존재하지 않는다 false
				int studentId = rest.getInt("STUDENT_ID");
				String studentName = rest.getString("STUDENT_NAME");
				Date enrollDate = rest.getDate("ENROLL_DATE");

				System.out.println(
						"번호 : " + studentId + " | " + "이름 : " + studentName + " | " + "등록일 : " + enrollDate + " | ");
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
