package com.kh.plant.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlantSelect {

	public static void main(String[] args) {

		// 0) 필요한 변수들 먼저 세팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rest = null;

		String sql = """
					SELECT
				  		   FLOWER_ID
				                  , NAME
				                  , SCIENTIFIC_NAME
				                  , FAMILY
				                  , COLOR
				                  , BLOOM_SEASON
				                  , HEIGHT_CM
				                  , ORIGIN
				                  , FRAGRANCE
				                  , SOIL_TYPE
				                  , SUNLIGHT
				                  , WATER_NEEDS
				                  , MAINTENANCE_LEVEL
				                  , DESCRIPTION
				                  , IMAGE_URL
				               FROM
				                    PLANT
				              ORDER
				                 BY
				                 FLOWER_ID DESC
				""";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver 등록 성공!");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02", "KHS021234");
			System.out.print("DB 연결 성공!");

			stmt = conn.createStatement();
			System.out.println("Statement객체 생성!");

			rest = stmt.executeQuery(sql);

			while (rest.next()) {
				int flowerId = rest.getInt("FLOWER_ID");
				String name = rest.getString("NAME");
				String scName = rest.getString("SCIENTIFIC_NAME");
				String family = rest.getString("FAMILY");
				String color = rest.getString("COLOR");
				String blSeason = rest.getString("BLOOM_SEASON");
				double height = rest.getDouble("HEIGHT_CM");
				String origin = rest.getString("ORIGIN");
				String fragrance = rest.getString("FRAGRANCE");
				String soilType = rest.getString("SOIL_TYPE");
				String sunLight = rest.getString("SUNLIGHT");
				String water = rest.getString("WATER_NEEDS");
				String maintenance = rest.getString("MAINTENANCE_LEVEL");
				String description = rest.getString("DESCRIPTION");
				String imageUrl = rest.getString("IMAGE_URL");

				System.out.println("🌸 번호: " + flowerId + " | 이름: " + name + " | 학명: " + scName + " | 과: " + family
						+ " | 색상: " + color + " | 개화시기: " + blSeason + " | 키: " + height + "cm" + " | 원산지: " + origin
						+ " | 향기: " + fragrance + " | 흙: " + soilType + " | 햇빛: " + sunLight + " | 물: " + water
						+ " | 관리 난이도: " + maintenance + " | 설명: " + description + " | 이미지: " + imageUrl);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rest != null) {
					rest.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
