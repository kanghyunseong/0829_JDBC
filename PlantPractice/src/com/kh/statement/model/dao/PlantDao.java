package com.kh.statement.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.statement.model.vo.Plant;

public class PlantDao {

	public int save(Plant plant) {
		int result = 0;
		String sql = "INSERT INTO PLANT VALUES (" + plant.getPlantId() + ", '" + plant.getName() + "', '"
				+ plant.getScientificName() + "', '" + plant.getFamily() + "', '" + plant.getColor() + "', '"
				+ plant.getBloomSeason() + "', " + plant.getHeightCm() + ", '" + plant.getOrigin() + "', '"
				+ plant.getFragrance() + "', '" + plant.getSoilType() + "', '" + plant.getSunlight() + "', '"
				+ plant.getWaterNeeds() + "', '" + plant.getMaintenanceLevel() + "', '" + plant.getDescription()
				+ "', '" + plant.getImageUrl() + "')";

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02",
				"KHS021234"); Statement stmt = conn.createStatement()) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			result = stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Plant> findAll() {
		List<Plant> plants = new ArrayList<>();
		String sql = "SELECT * FROM PLANT ORDER BY PLANT_ID DESC";

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02",
				"KHS021234"); Statement stmt = conn.createStatement(); ResultSet rset = stmt.executeQuery(sql)) {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			while (rset.next()) {
				Plant plant = new Plant(rset.getInt("PLANT_ID"), rset.getString("NAME"),
						rset.getString("SCIENTIFIC_NAME"), rset.getString("FAMILY"), rset.getString("COLOR"),
						rset.getString("BLOOM_SEASON"), rset.getDouble("HEIGHT_CM"), rset.getString("ORIGIN"),
						rset.getString("FRAGRANCE"), rset.getString("SOIL_TYPE"), rset.getString("SUNLIGHT"),
						rset.getString("WATER_NEEDS"), rset.getString("MAINTENANCE_LEVEL"),
						rset.getString("DESCRIPTION"), rset.getString("IMAGE_URL"));
				plants.add(plant);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return plants;
	}

	public Plant findByName(String name) {
		Plant plant = null;
		String sql = "SELECT * FROM PLANT WHERE NAME = '" + name + "'";

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02",
				"KHS021234"); Statement stmt = conn.createStatement(); ResultSet rset = stmt.executeQuery(sql)) {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			if (rset.next()) {
				plant = new Plant(rset.getInt("PLANT_ID"), rset.getString("NAME"), rset.getString("SCIENTIFIC_NAME"),
						rset.getString("FAMILY"), rset.getString("COLOR"), rset.getString("BLOOM_SEASON"),
						rset.getDouble("HEIGHT_CM"), rset.getString("ORIGIN"), rset.getString("FRAGRANCE"),
						rset.getString("SOIL_TYPE"), rset.getString("SUNLIGHT"), rset.getString("WATER_NEEDS"),
						rset.getString("MAINTENANCE_LEVEL"), rset.getString("DESCRIPTION"),
						rset.getString("IMAGE_URL"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return plant;
	}

	// 4) 키워드 조회
	public List<Plant> findByKeyword(String keyword) {
		List<Plant> plants = new ArrayList<>();
		String sql = "SELECT * FROM PLANT WHERE NAME LIKE '%" + keyword + "%' OR DESCRIPTION LIKE '%" + keyword
				+ "%' ORDER BY PLANT_ID DESC";

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02",
				"KHS021234"); Statement stmt = conn.createStatement(); ResultSet rset = stmt.executeQuery(sql)) {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			while (rset.next()) {
				Plant plant = new Plant(rset.getInt("PLANT_ID"), rset.getString("NAME"),
						rset.getString("SCIENTIFIC_NAME"), rset.getString("FAMILY"), rset.getString("COLOR"),
						rset.getString("BLOOM_SEASON"), rset.getDouble("HEIGHT_CM"), rset.getString("ORIGIN"),
						rset.getString("FRAGRANCE"), rset.getString("SOIL_TYPE"), rset.getString("SUNLIGHT"),
						rset.getString("WATER_NEEDS"), rset.getString("MAINTENANCE_LEVEL"),
						rset.getString("DESCRIPTION"), rset.getString("IMAGE_URL"));
				plants.add(plant);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return plants;
	}

}
