package com.kh.statement.controller;

import java.util.List;
import com.kh.statement.model.dao.PlantDao;
import com.kh.statement.model.vo.Plant;

public class PlantController {

	private PlantDao dao = new PlantDao();

	public int save(int plantId, String name, String scientificName, String family, String color, String bloomSeason,
			double heightCm, String origin, String fragrance, String soilType, String sunlight, String waterNeeds,
			String maintenanceLevel, String description, String imageUrl) {

		Plant plant = new Plant(plantId, name, scientificName, family, color, bloomSeason, heightCm, origin, fragrance,
				soilType, sunlight, waterNeeds, maintenanceLevel, description, imageUrl);

		return dao.save(plant);
	}

	public List<Plant> findAll() {
		return dao.findAll();
	}

	public Plant findByName(String name) {
		return dao.findByName(name);
	}

	public List<Plant> findByKeyword(String keyword) {
		return dao.findByKeyword(keyword);
	}
}
