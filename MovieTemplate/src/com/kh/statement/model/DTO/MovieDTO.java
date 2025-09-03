package com.kh.statement.model.DTO;

import java.sql.Date;

public class MovieDTO {

	private String title;
	private String newTitle;

	public MovieDTO() {
		super();
	}

	public MovieDTO(String title, String newTitle) {
		super();
		this.title = title;
		this.newTitle = newTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNewTitle() {
		return newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}

}
