package com.kh.statement.model.vo;

import java.util.Objects;

public class Plant {

	private int plantId;
	private String name; 
	private String scientificName; 
	private String family; 
	private String color; 
	private String bloomSeason; 
	private double heightCm; 
	private String origin; 
	private String fragrance; 
	private String soilType; 
	private String sunlight; 
	private String waterNeeds; 
	private String maintenanceLevel;
	private String description;
	private String imageUrl;

	public Plant() {
	}

	public Plant(int plantId, String name, String scientificName, String family, String color, String bloomSeason,
			double heightCm, String origin, String fragrance, String soilType, String sunlight, String waterNeeds,
			String maintenanceLevel, String description, String imageUrl) {
		this.plantId = plantId;
		this.name = name;
		this.scientificName = scientificName;
		this.family = family;
		this.color = color;
		this.bloomSeason = bloomSeason;
		this.heightCm = heightCm;
		this.origin = origin;
		this.fragrance = fragrance;
		this.soilType = soilType;
		this.sunlight = sunlight;
		this.waterNeeds = waterNeeds;
		this.maintenanceLevel = maintenanceLevel;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public int getPlantId() {
		return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBloomSeason() {
		return bloomSeason;
	}

	public void setBloomSeason(String bloomSeason) {
		this.bloomSeason = bloomSeason;
	}

	public double getHeightCm() {
		return heightCm;
	}

	public void setHeightCm(double heightCm) {
		this.heightCm = heightCm;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getFragrance() {
		return fragrance;
	}

	public void setFragrance(String fragrance) {
		this.fragrance = fragrance;
	}

	public String getSoilType() {
		return soilType;
	}

	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}

	public String getSunlight() {
		return sunlight;
	}

	public void setSunlight(String sunlight) {
		this.sunlight = sunlight;
	}

	public String getWaterNeeds() {
		return waterNeeds;
	}

	public void setWaterNeeds(String waterNeeds) {
		this.waterNeeds = waterNeeds;
	}

	public String getMaintenanceLevel() {
		return maintenanceLevel;
	}

	public void setMaintenanceLevel(String maintenanceLevel) {
		this.maintenanceLevel = maintenanceLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(plantId, name, scientificName, family, color, bloomSeason, heightCm, origin, fragrance,
				soilType, sunlight, waterNeeds, maintenanceLevel, description, imageUrl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Plant))
			return false;
		Plant other = (Plant) obj;
		return plantId == other.plantId && Double.compare(other.heightCm, heightCm) == 0
				&& Objects.equals(name, other.name) && Objects.equals(scientificName, other.scientificName)
				&& Objects.equals(family, other.family) && Objects.equals(color, other.color)
				&& Objects.equals(bloomSeason, other.bloomSeason) && Objects.equals(origin, other.origin)
				&& Objects.equals(fragrance, other.fragrance) && Objects.equals(soilType, other.soilType)
				&& Objects.equals(sunlight, other.sunlight) && Objects.equals(waterNeeds, other.waterNeeds)
				&& Objects.equals(maintenanceLevel, other.maintenanceLevel)
				&& Objects.equals(description, other.description) && Objects.equals(imageUrl, other.imageUrl);
	}

	@Override
	public String toString() {
		return "Plant [plantId=" + plantId + ", name=" + name + ", scientificName=" + scientificName + ", family="
				+ family + ", color=" + color + ", bloomSeason=" + bloomSeason + ", heightCm=" + heightCm + ", origin="
				+ origin + ", fragrance=" + fragrance + ", soilType=" + soilType + ", sunlight=" + sunlight
				+ ", waterNeeds=" + waterNeeds + ", maintenanceLevel=" + maintenanceLevel + ", description="
				+ description + ", imageUrl=" + imageUrl + "]";
	}
}
