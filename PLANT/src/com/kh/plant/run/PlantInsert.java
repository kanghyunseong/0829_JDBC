package com.kh.plant.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PlantInsert {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		Scanner sc = new Scanner(System.in);

		try {
			// 사용자 입력
			System.out.print("꽃 번호(ID)를 입력해주세요 > ");
			int flowerId = sc.nextInt();
			sc.nextLine();

			System.out.print("꽃 이름을 입력해주세요 > ");
			String name = sc.nextLine();

			System.out.print("꽃 학명을 입력해주세요 > ");
			String scientificName = sc.nextLine();

			System.out.print("꽃의 과(FAMILY)를 입력해주세요 > ");
			String family = sc.nextLine();

			System.out.print("꽃 색상을 입력해주세요 > ");
			String color = sc.nextLine();

			System.out.print("개화 시기를 입력해주세요 > ");
			String bloomSeason = sc.nextLine();

			System.out.print("평균 키(cm)를 입력해주세요 > ");
			double height = sc.nextDouble();
			sc.nextLine();

			System.out.print("원산지를 입력해주세요 > ");
			String origin = sc.nextLine();

			System.out.print("향기 여부/특징을 입력해주세요 > ");
			String fragrance = sc.nextLine();

			System.out.print("적합한 토양을 입력해주세요 > ");
			String soilType = sc.nextLine();

			System.out.print("햇빛 필요량을 입력해주세요 > ");
			String sunlight = sc.nextLine();

			System.out.print("물 주기 정보를 입력해주세요 > ");
			String waterNeeds = sc.nextLine();

			System.out.print("관리 난이도를 입력해주세요 > ");
			String maintenanceLevel = sc.nextLine();

			System.out.print("설명을 입력해주세요 > ");
			String description = sc.nextLine();

			System.out.print("이미지 URL을 입력해주세요 > ");
			String imageUrl = sc.nextLine();

			String sql = "INSERT INTO PLANT VALUES(" + flowerId + ", '" + name + "', '" + scientificName + "', '"
					+ family + "', '" + color + "', '" + bloomSeason + "', " + height + ", '" + origin + "', '"
					+ fragrance + "', '" + soilType + "', '" + sunlight + "', '" + waterNeeds + "', '"
					+ maintenanceLevel + "', '" + description + "', '" + imageUrl + "')";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver 등록 성공!");

			conn =DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02", "KHS021234");
			System.out.println("DB 연결 성공!");

			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

			if (result > 0) {
				System.out.println("데이터 삽입 성공!");
			} else {
				System.out.println("데이터 삽입 실패!");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 등록 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL 실행 중 오류 발생");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
				sc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
